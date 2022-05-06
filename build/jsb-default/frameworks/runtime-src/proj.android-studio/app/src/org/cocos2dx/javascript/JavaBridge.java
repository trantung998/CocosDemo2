package org.cocos2dx.javascript;

import android.util.Log;

import org.cocos2dx.lib.Cocos2dxHelper;
import org.cocos2dx.lib.Cocos2dxJavascriptJavaBridge;

public class JavaBridge {
    public interface AdsCallback {
        boolean onSuccess();
    }

    public static void TryToShowInAppReview() {
        AppReviewService.getInstance().RequestReviewInfo();
    }

    public static void LoadBottomBanner() {
        AdsService.getInstance().LoadBottomBanner();
    }

    public static void LoadRectBanner() {
        AdsService.getInstance().LoadRectBanner();
    }


    public static void ShowVideoAds() {
        AdsService.getInstance().ShowVideoReward();
    }

    public static void ShowInterAds() {
        AdsService.getInstance().ShowInterstitial();
    }

    public static void BuyIAP(String productId) {
        IAPService.getInstance().PurchaseProduct("com.rofi.sortmaster.removeads");

        Cocos2dxHelper.runOnGLThread(new Runnable() {
            @Override
            public void run() {
                String token = "tokennnnnnnnnnnnn------99999";
                Log.d("TAG", "flag 1" + token);
                Cocos2dxJavascriptJavaBridge.evalString("cc.NativeEventHandler.eventOnPurchase_Android('" + token + "')");
                Log.d("TAG", "flag 2" + token);
            }
        });
    }

    public static boolean IsRewardedVideoAvailable() {
        return AdsService.getInstance().IsRewardedVideoAvailable();
    }

    public static boolean IsInterstitialReady() {
        return AdsService.getInstance().IsInterstitialReady();
    }

    public static void OnVideoAdsResult(boolean isOk) {
        Cocos2dxHelper.runOnGLThread(new Runnable() {
            @Override
            public void run() {
                Cocos2dxJavascriptJavaBridge.evalString("cc.NativeEventHandler.eventReceiver('\" + eventName + \"')");
            }
        });
    }
}
