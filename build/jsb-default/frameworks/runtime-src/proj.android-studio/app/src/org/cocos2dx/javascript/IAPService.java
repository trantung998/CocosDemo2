package org.cocos2dx.javascript;

import android.app.Activity;
import android.util.Log;

import androidx.annotation.NonNull;

import com.android.billingclient.api.AcknowledgePurchaseParams;
import com.android.billingclient.api.AcknowledgePurchaseResponseListener;
import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.billingclient.api.SkuDetails;
import com.android.billingclient.api.SkuDetailsParams;
import com.android.billingclient.api.SkuDetailsResponseListener;

import org.cocos2dx.lib.Cocos2dxHelper;
import org.cocos2dx.lib.Cocos2dxJavascriptJavaBridge;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class IAPService {
    private static IAPService mInstace = null;
    public static IAPService getInstance() {
        if (null == mInstace) {
            mInstace = new IAPService();
        }
        return mInstace;
    }

    private List<String> productIds;

    private final String TAG = "IAPService";

    private Activity mActivity;
    private BillingClient billingClient;
    private boolean isConnected;
    private PurchasesUpdatedListener purchasesUpdatedListener = (billingResult, purchases) -> {
        // To be implemented in a later section.
        if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK && purchases != null) {
            Log.d(TAG, "purchase ok");
            for (Purchase purchase : purchases) {
                handlePurchase(purchase);
            }
        } else if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.USER_CANCELED) {
            // Handle an error caused by a user cancelling the purchase flow.
            Log.d(TAG, "USER_CANCELED");
        } else {
            // Handle any other error codes.
        }
    };

    AcknowledgePurchaseResponseListener acknowledgePurchaseResponseListener = new AcknowledgePurchaseResponseListener() {
        @Override
        public void onAcknowledgePurchaseResponse(@NonNull BillingResult billingResult) {
            Log.d(TAG, "onAcknowledgePurchaseResponse: " + billingResult.getResponseCode());
        }
    };



    Hashtable<String, SkuDetails> productIdToSkuDetail;

    public IAPService(){
        productIds = new ArrayList<>();
        //Todo: add product id
        productIds.add("com.rofi.sortmaster.removeads");

        productIdToSkuDetail = new Hashtable<String, SkuDetails>();
    }

    public void Init(Activity activity){

        mActivity = activity;
        billingClient = BillingClient.newBuilder(activity.getApplicationContext())
                .setListener(purchasesUpdatedListener)
                .enablePendingPurchases()
                .build();

        CheckConnectionToGGPlay();
    }

    public boolean CheckConnectionToGGPlay(){
        if(!isConnected){
            billingClient.startConnection(new BillingClientStateListener() {
                @Override
                public void onBillingSetupFinished(BillingResult billingResult) {
                    if (billingResult.getResponseCode() ==  BillingClient.BillingResponseCode.OK) {
                        // The BillingClient is ready. You can query purchases here.
                        isConnected = true;

                        queryProductsDetail();
                    }
                }
                @Override
                public void onBillingServiceDisconnected() {
                    // Try to restart the connection on the next request to
                    // Google Play by calling the startConnection() method.
                    isConnected = false;
                }
            });
        }

        return isConnected;
    }

    private void queryProductsDetail() {
        SkuDetailsParams skuDetailsParams = SkuDetailsParams.newBuilder()
                .setSkusList(productIds)
                .setType(BillingClient.SkuType.INAPP)
                .build();

        // TODO: Thực hiện query
        billingClient.querySkuDetailsAsync(skuDetailsParams,
                new SkuDetailsResponseListener() {
                    @Override
                    public void onSkuDetailsResponse(BillingResult billingResult,
                                                     List<SkuDetails> skuDetailsList) {
                        productIdToSkuDetail.clear();

                        // Process the result.
                        for (SkuDetails skuDetails: skuDetailsList) {
                            Log.d(TAG,"sku Title: " +  skuDetails.getTitle());
                            productIdToSkuDetail.put(skuDetails.getSku(), skuDetails);
                        }
                    }
                });
    }

    public void PurchaseProduct(String productID){

        // Retrieve a value for "skuDetails" by calling querySkuDetailsAsync().
        SkuDetails skuDetails = productIdToSkuDetail.get(productID);

        BillingFlowParams billingFlowParams = BillingFlowParams.newBuilder()
                .setSkuDetails(skuDetails)
                .build();
        int responseCode = billingClient.launchBillingFlow(mActivity, billingFlowParams).getResponseCode();

        Log.d(TAG, "PurchaseProduct: " + responseCode);

    }

    void handlePurchase(Purchase purchase) {
        if (purchase.getPurchaseState() == Purchase.PurchaseState.PURCHASED) {
            if (!purchase.isAcknowledged()) {
                AcknowledgePurchaseParams acknowledgePurchaseParams =
                        AcknowledgePurchaseParams.newBuilder()
                                .setPurchaseToken(purchase.getPurchaseToken())
                                .build();

                billingClient.acknowledgePurchase(acknowledgePurchaseParams, acknowledgePurchaseResponseListener);

                //todo: send to server to verify iap
                Log.d(TAG, "handlePurchase: getPurchaseToken: " + purchase.getPurchaseToken());
            }
        }
    }

    public void OnResume(){
        if(isConnected && billingClient != null){
            billingClient.queryPurchasesAsync(BillingClient.SkuType.INAPP, (billingResult, list) -> {
                if(billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK){
                    for (Purchase purchase : list) {
                        handlePurchase(purchase);
                    }
                }
            });
        }
    }

}
