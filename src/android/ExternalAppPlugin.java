package com.hogangnono.cordova.plugin;


import java.io.IOException;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;

import org.json.JSONArray;
import org.json.JSONException;


import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;


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
        if("openYoutube".equals(action))
        {
            return openYoutube(callbackContext);
        }

        return false;
    }

    private boolean openYoutube(final CallbackContext callbackContext) {

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                // Intent 로 유튜브 앱 오픈
                // 오픈 안되었다면 Error. 열었다면 Success 보냄
            }
        });
        return true;
    }
}
