//
//  ExternalApp.m
//
//  Created by Cayden on 2018. 12. 19..
//
//
#import "ExternalApp.h"
#import <Cordova/CDVPlugin.h>
#import "AppDelegate+ExternalAppPlugin.h"

@implementation ExternalApp

- (void)openYoutube:(CDVInvokedUrlCommand *)command
{
    NSString *vid = [command.arguments objectAtIndex:0];
    NSDictionary *options = [command.arguments objectAtIndex:1];

    NSURL *youtubeAppURL = [NSURL URLWithString:[NSString stringWithFormat:@"youtube://watch?v=%@", vid]];
    CDVPluginResult *result;
    if ([[UIApplication sharedApplication] canOpenURL:youtubeAppURL]) {
        [[UIApplication sharedApplication] openURL:youtubeAppURL];
        result = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
    } else {
        result = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR];
    }

    [self.commandDelegate sendPluginResult:result callbackId:command.callbackId];
}

- (void)getAppStartTime:(CDVInvokedUrlCommand *)command
{
    AppDelegate *delegate = (AppDelegate *)[[UIApplication sharedApplication] delegate];
    NSDate *startDate = delegate.applicationStartDate;
    NSDateFormatter* df = [[NSDateFormatter alloc]init];
    [df setDateFormat:@"yyyy-MM-dd'T'HH:mm:ss.SSS"];
    NSString *dateString = [df stringFromDate:startDate];
    CDVPluginResult *result = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:dateString];
    [self.commandDelegate sendPluginResult:result callbackId:command.callbackId];
}
@end
