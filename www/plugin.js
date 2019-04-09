var exec = require('cordova/exec');

var PLUGIN_NAME = 'ExternalApp';

var ExternalApp = {
    openYoutube: function(videoId, options, success, error) {
        exec(success, error, PLUGIN_NAME, 'openYoutube', [videoId, options || {}]);
    },
    getAppStartTime: function(success, error) {
        exec(success, error, PLUGIN_NAME, 'getAppStartTime', {});
    }
};

module.exports = ExternalApp;
