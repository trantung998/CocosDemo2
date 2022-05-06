const { ccclass, property } = cc._decorator;

@ccclass
export default class Helloworld extends cc.Component {

    @property(cc.Label)
    label: cc.Label = null;

    @property
    text: string = 'hello';

    start() {
        // init logic
        this.label.string = this.text;
    }

    showVideoAds() {
        cc.log("Show video Ads")

        jsb.reflection.callStaticMethod("org/cocos2dx/javascript/JavaBridge", "LoadBottomBanner", "()V");
        // "org/cocos2dx/javascript/AppActivity": class bên native
        // "loadAdMob": function ở trong class bên trên
        // ()V: function kiểu void
    }

    showReview() {
        jsb.reflection.callStaticMethod("org/cocos2dx/javascript/JavaBridge", "TryToShowInAppReview", "()V");
        // "org/cocos2dx/javascript/AppActivity": class bên native
        // "loadAdMob": function ở trong class bên trên
        // ()V: function kiểu void
    }

    showinterAds() {
        cc.log("Show inter Ads")

        jsb.reflection.callStaticMethod("org/cocos2dx/javascript/JavaBridge", "LoadRectBanner", "()V");
        // "org/cocos2dx/javascript/AppActivity": class bên native
        // "loadAdMob": function ở trong class bên trên
        // ()V: function kiểu void
    }

    requestBuyIAP() {
        cc.log("Buy IAP")

        jsb.reflection.callStaticMethod("org/cocos2dx/javascript/JavaBridge", "BuyIAP", "(Ljava/lang/String;)V", "com.herofi.game.removeads");
    }

    LogEvent() {

    }
}
