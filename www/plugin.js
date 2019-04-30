var exec = require('cordova/exec');

var PLUGIN_NAME = 'ExternalAppPlugin';

var ExternalApp = {
    openYoutube: function(videoId, options, success, error) {
        exec(success, error, PLUGIN_NAME, 'openYoutube', [videoId, options || {}]);
    },
    getAppStartTime: function(success, error) {
        // [workaround] 파라메터가 없더라도 {} 가 아닌 [] 의 리스트 형태여야 Android 에서도 동일한 메소드로 동일 동작함
        exec(success, error, PLUGIN_NAME, 'getAppStartTime', []);
    }
};

module.exports = ExternalApp;
