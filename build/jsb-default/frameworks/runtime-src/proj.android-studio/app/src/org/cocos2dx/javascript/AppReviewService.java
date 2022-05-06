package org.cocos2dx.javascript;

import android.app.Activity;
import android.util.Log;

import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.google.android.play.core.review.model.ReviewErrorCode;
import com.google.android.play.core.tasks.Task;

public class AppReviewService {
    private final String TAG = "AppReviewService";

    private static AppReviewService mInstace = null;

    public static AppReviewService getInstance() {
        if (null == mInstace) {
            mInstace = new AppReviewService();
        }
        return mInstace;
    }

    private Activity mActivity;

    public void Init(Activity activity) {
        mActivity = activity;
    }

    public void RequestReviewInfo() {
        ReviewManager manager = ReviewManagerFactory.create(mActivity.getApplicationContext());

        Task<ReviewInfo> request = manager.requestReviewFlow();

        request.addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                // We can get the ReviewInfo object
                ReviewInfo reviewInfo = task.getResult();
                Task<Void> flow = manager.launchReviewFlow(mActivity, reviewInfo);
            } else {
                // There was some problem, log or handle the error code.
                Log.d(TAG, "ShowInAppReviewsFlow Error: " + task.getException().getMessage());
            }
        });
    }
}
