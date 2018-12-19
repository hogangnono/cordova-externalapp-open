//
//  ExternalApp.h
//
//  Created by Cayden on 2018. 12. 19..
//
//

#import <UIKit/UIKit.h>
#import <Cordova/CDVPlugin.h>

@interface ExternalApp : CDVPlugin

- (void)openYoutube:(CDVInvokedUrlCommand *)command;
@end

