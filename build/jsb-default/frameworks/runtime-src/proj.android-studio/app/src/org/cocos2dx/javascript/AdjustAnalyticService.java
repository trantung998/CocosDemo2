package org.cocos2dx.javascript;

import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.adjust.sdk.Adjust;
import com.adjust.sdk.AdjustConfig;
import com.adjust.sdk.AdjustEvent;
import com.adjust.sdk.LogLevel;

public class AdjustAnalyticService implements IAnalyticService {
    private final String APP_TOKEN = "91av8kg9z7r4";

    private final String APP_OPEN_EVENT_TOKEN = "eiwyma";
    private final String EMAIL_LOGIN_EVENT_TOKEN = "gh1mry";
    private final String GUEST_LOGIN_EVENT_TOKEN = "sc0hqi";
    private final String APP_HIDE_EVENT_TOKEN = "ifv7pw";
    private final String SHOW_ADS_INTER_EVENT_TOKEN = "7zr1dd";
    private final String LINK_WALLET_EVENT_TOKEN = "xfkrdh";
    private final String PAY_FINISH_EVENT_TOKEN = "vfq0lt";
    private final String SHOW_ADS_VIDEO_EVENT_TOKEN = "lvakla";

    @Override
    public void OnCreateService(Activity activity) {
        String appToken = APP_TOKEN;
        //todo: change to this line
//        String environment = AdjustConfig.ENVIRONMENT_PRODUCTION;

        String environment = AdjustConfig.ENVIRONMENT_SANDBOX;
        AdjustConfig config = new AdjustConfig(activity.getApplicationContext(), appToken, environment);

        //todo: set to SUPRESS when release
        config.setLogLevel(LogLevel.WARN);

        Adjust.onCreate(config);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            activity.registerActivityLifecycleCallbacks(new AdjustLifecycleCallbacks());
        }
    }

    @Override
    public void LogEvent(String eventName) {
        AdjustEvent adjustEvent = new AdjustEvent(eventName);
        Adjust.trackEvent(adjustEvent);
    }

    @Override
    public void LogOpenAppEvent() {
        AdjustEvent adjustEvent = new AdjustEvent(APP_OPEN_EVENT_TOKEN);
        Adjust.trackEvent(adjustEvent);
    }

    @Override
    public void LoginEvent(int type) {
        AdjustEvent adjustEvent;
        switch (type) {
            case 0:
                adjustEvent = new AdjustEvent(GUEST_LOGIN_EVENT_TOKEN);
                Adjust.trackEvent(adjustEvent);
                break;
            case 1:
                adjustEvent = new AdjustEvent(EMAIL_LOGIN_EVENT_TOKEN);
                Adjust.trackEvent(adjustEvent);
                break;
            case 2:
                adjustEvent = new AdjustEvent(LINK_WALLET_EVENT_TOKEN);
                Adjust.trackEvent(adjustEvent);
                break;
        }
    }

    @Override
    public void HideAppEvent() {
        AdjustEvent adjustEvent = new AdjustEvent(APP_HIDE_EVENT_TOKEN);
        Adjust.trackEvent(adjustEvent);
    }

    @Override
    public void PayEvent() {

    }

    @Override
    public void EarnToken(int tokenValue) {

    }

    @Override
    public void ShowAds(int type) {

    }

    @Override
    public void UseItem(int itemId, int level) {

    }

    @Override
    public void StartLevel(int level) {

    }

    @Override
    public void FinishLevel(int level) {

    }

    private static final class AdjustLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {
        @Override
        public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle bundle) {

        }

        @Override
        public void onActivityStarted(@NonNull Activity activity) {

        }

        @Override
        public void onActivityResumed(Activity activity) {
            Adjust.onResume();
        }

        @Override
        public void onActivityPaused(Activity activity) {
            Adjust.onPause();
        }

        @Override
        public void onActivityStopped(@NonNull Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {

        }

        @Override
        public void onActivityDestroyed(@NonNull Activity activity) {

        }
    }
}
