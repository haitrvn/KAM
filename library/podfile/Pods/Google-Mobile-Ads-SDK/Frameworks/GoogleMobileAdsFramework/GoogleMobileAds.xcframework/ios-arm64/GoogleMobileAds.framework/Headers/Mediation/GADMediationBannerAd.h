//
//  GADMediationBannerAd.h
//  Google Mobile Ads SDK
//
//  Copyright 2018 Google LLC. All rights reserved.
//

#import "../GADAdSize.h"
#import "GADMediationAd.h"
#import "GADMediationAdConfiguration.h"
#import "GADMediationAdEventDelegate.h"
#import <UIKit/UIKit.h>

/// Rendered banner ad. Provides a single subview to add to the banner view's view hierarchy.
@protocol GADMediationBannerAd <GADMediationAd>

/// The banner ad view.
@property(nonatomic, readonly, nonnull) UIView *view;

@optional

/// Tells the ad to resize the banner. Implement if banner content is resizable.
- (void)changeAdSizeTo:(GADAdSize)adSize;
@end

/// Rendered interscroller ad.
@protocol GADMediationInterscrollerAd <GADMediationBannerAd>

/// Indicates whether the interscroller rendering effect should be delegated to the Google Mobile
/// Ads SDK.
@property(nonatomic, assign) BOOL delegateInterscrollerEffect;

@end

/// Banner ad configuration.
@interface GADMediationBannerAdConfiguration : GADMediationAdConfiguration

/// Banner ad size requested of the adapter.
@property(nonatomic, readonly) GADAdSize adSize;

@end
