#import "AppDelegate+ExternalAppPlugin.h"
#import <objc/runtime.h>

#define kStartDateKey @"startDate"

@implementation AppDelegate (ExternalAppPlugin)

- (void)setApplicationStartDate:(NSDate *)applicationStartDate {
    objc_setAssociatedObject(self, kStartDateKey, applicationStartDate, OBJC_ASSOCIATION_RETAIN_NONATOMIC);
}

- (NSDate *)applicationStartDate {
    return objc_getAssociatedObject(self, kStartDateKey);
}

- (BOOL)application:(UIApplication*)application didFinishLaunchingWithOptions:(NSDictionary*)launchOptions
{
    self.applicationStartDate = [NSDate new];
    return [super application:application didFinishLaunchingWithOptions:launchOptions];
}

@end
