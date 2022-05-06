package org.cocos2dx.javascript;

import android.app.Activity;

public interface IAnalyticService {
    void OnCreateService(Activity activity);

    void LogEvent(String eventName);

    void LogOpenAppEvent();

    //type: guest = 0,email = 1,linkwallet = 2
    void LoginEvent(int type);

    void HideAppEvent();

    void PayEvent();

    void EarnToken(int tokenValue);

    //VideoReward = 0, Inter = 1, RectBanner = 2
    void ShowAds(int type);

    //0 - backMove
    //1 - hint
    //2 - shuffle color
    //3 - add tube
    void UseItem(int itemId, int level);

    //level
    void StartLevel(int level);
    void FinishLevel(int level);
}
