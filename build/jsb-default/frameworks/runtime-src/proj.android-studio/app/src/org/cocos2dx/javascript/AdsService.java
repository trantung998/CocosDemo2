package org.cocos2dx.javascript;

import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;

//import com.herofi.game.R;

import com.ironsource.mediationsdk.ISBannerSize;
import com.ironsource.mediationsdk.IronSource;
import com.ironsource.mediationsdk.IronSourceBannerLayout;
import com.ironsource.mediationsdk.integration.IntegrationHelper;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.model.Placement;
import com.ironsource.mediationsdk.sdk.BannerListener;
import com.ironsource.mediationsdk.sdk.InterstitialListener;
import com.ironsource.mediationsdk.sdk.RewardedVideoListener;

import org.cocos2dx.lib.Cocos2dxActivity;

public class AdsService {

    private final String APP_KEY = "14972e701";
    private final String HOME_BANNER = "Home_Screen";
    private final String STARTUP_BANNER = "Startup";
    private final String TAG = "AdsService";

    private static AdsService mInstace = null;

    private Cocos2dxActivity mActivity;

    //rect banner
    private FrameLayout mRectBannerParentLayout;
    private IronSourceBannerLayout mIronSourceRectBannerLayout;

    //bottom banner
    private FrameLayout mBottomBannerParentLayout;
    private IronSourceBannerLayout mIronSourceBottomBannerLayout;

    private String mCurrentBanner;

    public static AdsService getInstance() {
        if (null == mInstace) {
            mInstace = new AdsService();
        }
        return mInstace;
    }

    public void Init(Cocos2dxActivity activity) {
        mActivity = activity;


        //The integrationHelper is used to validate the integration. Remove the integrationHelper before going live!
        IntegrationHelper.validateIntegration(activity);

        InitVideoReward(APP_KEY);
        InitInterstitial(APP_KEY);

        InitBanner(APP_KEY);
    }

    private void InitBanner(String appId) {
        IronSource.init(mActivity, appId, IronSource.AD_UNIT.BANNER);
    }

    private void CreateAndLoadRectBanner() {
        BannerListener bannerListener = new BannerListener() {
            @Override
            public void onBannerAdLoaded() {
                // Called after a banner ad has been successfully loaded
                // since banner container was "gone" by default, we need to make it visible as soon as the banner is ready
                mRectBannerParentLayout.setVisibility(View.VISIBLE);
            }

            @Override
            public void onBannerAdLoadFailed(IronSourceError error) {
                // Called after a banner has attempted to load an ad but failed.
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mRectBannerParentLayout.removeAllViews();
                    }
                });
            }

            @Override
            public void onBannerAdClicked() {

            }

            @Override
            public void onBannerAdScreenPresented() {

            }

            @Override
            public void onBannerAdScreenDismissed() {

            }

            @Override
            public void onBannerAdLeftApplication() {

            }
        };
        mRectBannerParentLayout = new FrameLayout(mActivity.getApplicationContext());
        mRectBannerParentLayout.setPaddingRelative(0, 70, 0, 0);
        mRectBannerParentLayout.setVisibility(View.INVISIBLE);

        mIronSourceRectBannerLayout = IronSource.createBanner(mActivity, ISBannerSize.RECTANGLE);
        mIronSourceRectBannerLayout.setBannerListener(bannerListener);

        mRectBannerParentLayout.addView(mIronSourceRectBannerLayout, 0, new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.WRAP_CONTENT));

        mActivity.addContentView(mRectBannerParentLayout, new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.WRAP_CONTENT,
                Gravity.NO_GRAVITY));

        mCurrentBanner = HOME_BANNER;
        IronSource.loadBanner(mIronSourceRectBannerLayout, mCurrentBanner);

    }

    private void CreateAndLoadBottomBanner() {
        BannerListener bannerListener = new BannerListener() {
            @Override
            public void onBannerAdLoaded() {
                // Called after a banner ad has been successfully loaded
                // since banner container was "gone" by default, we need to make it visible as soon as the banner is ready
                mBottomBannerParentLayout.setVisibility(View.VISIBLE);
            }

            @Override
            public void onBannerAdLoadFailed(IronSourceError error) {
                // Called after a banner has attempted to load an ad but failed.
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mBottomBannerParentLayout.removeAllViews();
                    }
                });
            }

            @Override
            public void onBannerAdClicked() {

            }

            @Override
            public void onBannerAdScreenPresented() {

            }

            @Override
            public void onBannerAdScreenDismissed() {

            }

            @Override
            public void onBannerAdLeftApplication() {

            }
        };
        mBottomBannerParentLayout = new FrameLayout(mActivity.getApplicationContext());
        mBottomBannerParentLayout.setVisibility(View.INVISIBLE);

        mIronSourceBottomBannerLayout = IronSource.createBanner(mActivity, ISBannerSize.BANNER);
        mIronSourceBottomBannerLayout.setBannerListener(bannerListener);

        mBottomBannerParentLayout.addView(mIronSourceBottomBannerLayout, 0, new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.WRAP_CONTENT));

        mActivity.addContentView(mBottomBannerParentLayout, new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.WRAP_CONTENT,
                Gravity.BOTTOM));

        mCurrentBanner = STARTUP_BANNER;
        IronSource.loadBanner(mIronSourceBottomBannerLayout, mCurrentBanner);
    }

    private void InitVideoReward(String appId) {
        // Be sure to set a listener to each product that is being initiated
        // set the IronSource rewarded video listener
        IronSource.setRewardedVideoListener(new RewardedVideoListener() {
            @Override
            public void onRewardedVideoAdOpened() {
                // called when the video is opened
                Log.d(TAG, "onRewardedVideoAdOpened");
            }

            @Override
            public void onRewardedVideoAdClosed() {
                // called when the video is closed
                Log.d(TAG, "onRewardedVideoAdClosed");
                // here we show a dialog to the user if he was rewarded
            }

            @Override
            public void onRewardedVideoAvailabilityChanged(boolean b) {
                // called when the video availbility has changed
                Log.d(TAG, "onRewardedVideoAvailabilityChanged" + " " + b);
            }

            @Override
            public void onRewardedVideoAdStarted() {
                // called when the video has started
                Log.d(TAG, "onRewardedVideoAdStarted");
            }

            @Override
            public void onRewardedVideoAdEnded() {
                // called when the video has ended
                Log.d(TAG, "onRewardedVideoAdEnded");
            }

            @Override
            public void onRewardedVideoAdRewarded(Placement placement) {
                // called when the video has been rewarded and a reward can be given to the user
                Log.d(TAG, "onRewardedVideoAdRewarded" + " " + placement);
            }

            @Override
            public void onRewardedVideoAdShowFailed(IronSourceError ironSourceError) {
                // called when the video has failed to show
                // you can get the error data by accessing the IronSourceError object
                // IronSourceError.getErrorCode();
                // IronSourceError.getErrorMessage();
                Log.d(TAG, "onRewardedVideoAdShowFailed" + " " + ironSourceError);
            }

            @Override
            public void onRewardedVideoAdClicked(Placement placement) {

            }
        });

        IronSource.shouldTrackNetworkState(mActivity, true);
        // init the IronSource SDK
        IronSource.init(mActivity, appId, IronSource.AD_UNIT.REWARDED_VIDEO);
    }

    private void InitInterstitial(String appId) {
        IronSource.setInterstitialListener(new InterstitialListener() {
            /**
             * Invoked when Interstitial Ad is ready to be shown after load function was called.
             */
            @Override
            public void onInterstitialAdReady() {
            }

            /**
             * invoked when there is no Interstitial Ad available after calling load function.
             */
            @Override
            public void onInterstitialAdLoadFailed(IronSourceError error) {
            }

            /**
             * Invoked when the Interstitial Ad Unit is opened
             */
            @Override
            public void onInterstitialAdOpened() {
            }

            /*
             * Invoked when the ad is closed and the user is about to return to the application.
             */
            @Override
            public void onInterstitialAdClosed() {
                IronSource.loadInterstitial();
            }

            /**
             * Invoked when Interstitial ad failed to show.
             * @param error - An object which represents the reason of showInterstitial failure.
             */
            @Override
            public void onInterstitialAdShowFailed(IronSourceError error) {
            }

            /*
             * Invoked when the end user clicked on the interstitial ad, for supported networks only.
             */
            @Override
            public void onInterstitialAdClicked() {
            }

            /** Invoked right before the Interstitial screen is about to open.
             *  NOTE - This event is available only for some of the networks.
             *  You should NOT treat this event as an interstitial impression, but rather use InterstitialAdOpenedEvent
             */
            @Override
            public void onInterstitialAdShowSucceeded() {
            }
        });

        // init the IronSource SDK
        IronSource.init(mActivity, appId, IronSource.AD_UNIT.INTERSTITIAL);
        //
        IronSource.loadInterstitial();

    }

    public void LoadRectBanner() {

        // Get a handler that can be used to post to the main thread
        Handler mainHandler = new Handler(mActivity.getMainLooper());

        Runnable myRunnable = new Runnable() {
            @Override
            public void run() {
                if (mIronSourceBottomBannerLayout != null) IronSource.destroyBanner(mIronSourceBottomBannerLayout);
                CreateAndLoadRectBanner();
            }
        };
        mainHandler.post(myRunnable);
    }

    public void LoadBottomBanner() {

        // Get a handler that can be used to post to the main thread
        Handler mainHandler = new Handler(mActivity.getMainLooper());

        Runnable myRunnable = new Runnable() {
            @Override
            public void run() {

                if (mIronSourceRectBannerLayout != null) IronSource.destroyBanner(mIronSourceRectBannerLayout);
                CreateAndLoadBottomBanner();
            }
        };
        mainHandler.post(myRunnable);
    }

    public void ShowVideoReward() {
        if (IronSource.isRewardedVideoAvailable()) {
            IronSource.showRewardedVideo();
        }
    }

    public void ShowInterstitial() {
        if (IronSource.isInterstitialReady()) {
            IronSource.showInterstitial();
        } else {
            Log.d(TAG, "ShowInterstitial: failed");
        }
    }

    public boolean IsRewardedVideoAvailable() {
        return IronSource.isRewardedVideoAvailable();
    }

    public boolean IsInterstitialReady() {
        return IronSource.isInterstitialReady();
    }

    public void OnResume() {
        IronSource.onResume(mActivity);
    }

    public void OnPause() {
        IronSource.onPause(mActivity);
    }
}
