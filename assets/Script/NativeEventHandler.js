cc.NativeEventHandler = cc.NativeEventHandler || {};

cc.NativeEventHandler.eventTarget = new cc.EventTarget();

cc.NativeEventHandler.eventReceiver = function (eventName, code) {
    console.log("Native Event: ");
    cc.NativeEventHandler.emitEvent(eventName, code);
};

cc.NativeEventHandler.eventOnPurchase_Android = function (token) {
    console.log("eventOnPurchase_Android: " + token);
};

cc.NativeEventHandler.emitEvent = function (eventName, code) {
    cc.NativeEventHandler.eventTarget.emit(eventName, code);
};

cc.NativeEventHandler.onEvent = function (eventName, callback, target) {
    cc.NativeEventHandler.eventTarget.on(eventName, callback, target);
};

module.exports = cc.NativeEventHandler;