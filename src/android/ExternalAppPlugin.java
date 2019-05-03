package com.hogangnono.cordova.plugin;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;

import org.json.JSONArray;
import org.json.JSONException;


import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.hogangnono.hogangnono.HogangnonoApplication;


public class ExternalAppPlugin extends CordovaPlugin {

    private static final String TAG = "ExternalAppPlugin";
    private Context context;

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);

        this.context = this.cordova.getActivity().getApplicationContext();
    }

    @Override
    public boolean execute(final String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        Log.d(TAG, "Executing...");
        if("openYoutube".equals(action)) {
            return openYoutube(callbackContext, args);
        } else if ("getAppStartTime".equals(action)) {
            return getAppStartTime(callbackContext, args);
        }

        return false;
    }

    private boolean openYoutube(final CallbackContext callbackContext, final JSONArray args) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                // Intent 로 유튜브 앱 오픈
                // 오픈 안되었다면 Error. 열었다면 Success 보냄
                try {
                    String videoId = args.getString(0);
                    if (videoId == null || videoId.isEmpty()) {
                        callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, "video id is empty"));
                        return;
                    }
                    Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + videoId));
                    context.startActivity(appIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                } catch (ActivityNotFoundException ex) {
                    callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, "not installed youtubeapp"));
                } catch (JSONException e) {
                    callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, "argument parsing error"));
                }

                callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, ""));
            }
        });
        return true;
    }

    private boolean getAppStartTime(final CallbackContext callbackContext, final JSONArray args) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                // 시작시 저장해놓은 시간을 그대로 리턴
                Date appStartTime = HogangnonoApplication.getAppStartTime();
                if (appStartTime != null) {
                    HogangnonoApplication.resetAppStartTime();
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
                    String appStartTimeString = format.format(appStartTime);
                    callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, appStartTimeString));
                } else {
                    callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, "is not cold started."));
                }

            }
        });
        return true;
    }
}
