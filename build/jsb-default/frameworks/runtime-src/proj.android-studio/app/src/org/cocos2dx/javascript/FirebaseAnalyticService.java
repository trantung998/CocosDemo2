package org.cocos2dx.javascript;

import android.app.Activity;
import android.os.Bundle;

import com.google.firebase.analytics.FirebaseAnalytics;

public class FirebaseAnalyticService implements IAnalyticService{
    private FirebaseAnalytics mFirebaseAnalytics;
    private Activity mActivity;
    private final String HIDE_APP_EVENT = "app_hide";
    @Override
    public void OnCreateService(Activity activity) {
        mActivity = activity;
        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(mActivity.getApplicationContext());
    }

    public void LogEvent(String eventName){
        Bundle bundle = new Bundle();
//        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.APP_OPEN, bundle);
        mFirebaseAnalytics.logEvent(eventName, bundle);
    }

    @Override
    public void LogOpenAppEvent() {
        Bundle bundle = new Bundle();
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.APP_OPEN, bundle);
    }

    @Override
    public void LoginEvent(int type) {
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.LOGIN, bundle);
    }

    @Override
    public void HideAppEvent() {
        Bundle bundle = new Bundle();
        mFirebaseAnalytics.logEvent(HIDE_APP_EVENT, bundle);
    }

    @Override
    public void PayEvent() {
        Bundle bundle = new Bundle();
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.PURCHASE, bundle);
    }

    @Override
    public void EarnToken(int tokenValue) {
        Bundle bundle = new Bundle();
        bundle.putInt("value", tokenValue);
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.EARN_VIRTUAL_CURRENCY, bundle);
    }

    @Override
    public void ShowAds(int type) {
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.AD_IMPRESSION, bundle);
    }

    @Override
    public void UseItem(int itemId, int level) {
        Bundle bundle = new Bundle();
        bundle.putInt("item", itemId);
        bundle.putInt("level", level);
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_ITEM, bundle);
    }

    @Override
    public void StartLevel(int level) {
        Bundle bundle = new Bundle();
        bundle.putInt("level", level);
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.LEVEL_START, bundle);
    }

    @Override
    public void FinishLevel(int level) {
        Bundle bundle = new Bundle();
        bundle.putInt("level", level);
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.LEVEL_END, bundle);
    }
}
