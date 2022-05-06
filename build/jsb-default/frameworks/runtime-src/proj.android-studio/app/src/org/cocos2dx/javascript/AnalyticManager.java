package org.cocos2dx.javascript;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

public class AnalyticManager {
    private static AnalyticManager mInstace = null;

    public static AnalyticManager getInstance() {
        if (null == mInstace) {
            mInstace = new AnalyticManager();
        }
        return mInstace;
    }

    private List<IAnalyticService> services;

    public void Init(Activity activity) {
        for (IAnalyticService service : services) {
            service.OnCreateService(activity);
        }
    }

    private AnalyticManager() {
        services = new ArrayList<>();
        services.add(new FirebaseAnalyticService());
        services.add(new AdjustAnalyticService());
    }

    //
    public void LogAppOpenEvent() {
        for (IAnalyticService service : services) {
            service.LogOpenAppEvent();
        }
    }
}
