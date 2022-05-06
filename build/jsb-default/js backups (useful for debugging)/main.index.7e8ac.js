window.__require = function e(t, o, n) {
function r(i, a) {
if (!o[i]) {
if (!t[i]) {
var l = i.split("/");
l = l[l.length - 1];
if (!t[l]) {
var v = "function" == typeof __require && __require;
if (!a && v) return v(l, !0);
if (c) return c(l, !0);
throw new Error("Cannot find module '" + i + "'");
}
i = l;
}
var p = o[i] = {
exports: {}
};
t[i][0].call(p.exports, function(e) {
return r(t[i][1][e] || e);
}, p, p.exports, e, t, o, n);
}
return o[i].exports;
}
for (var c = "function" == typeof __require && __require, i = 0; i < n.length; i++) r(n[i]);
return r;
}({
AdsService: [ function(e, t) {
"use strict";
cc._RF.push(t, "15f35kQ7t1ITaILp2/a8Jxh", "AdsService");
cc._RF.pop();
}, {} ],
Helloworld: [ function(e, t, o) {
"use strict";
cc._RF.push(t, "e1b90/rohdEk4SdmmEZANaD", "Helloworld");
var n, r = this && this.__extends || (n = function(e, t) {
return (n = Object.setPrototypeOf || {
__proto__: []
} instanceof Array && function(e, t) {
e.__proto__ = t;
} || function(e, t) {
for (var o in t) Object.prototype.hasOwnProperty.call(t, o) && (e[o] = t[o]);
})(e, t);
}, function(e, t) {
n(e, t);
function o() {
this.constructor = e;
}
e.prototype = null === t ? Object.create(t) : (o.prototype = t.prototype, new o());
}), c = this && this.__decorate || function(e, t, o, n) {
var r, c = arguments.length, i = c < 3 ? t : null === n ? n = Object.getOwnPropertyDescriptor(t, o) : n;
if ("object" == typeof Reflect && "function" == typeof Reflect.decorate) i = Reflect.decorate(e, t, o, n); else for (var a = e.length - 1; a >= 0; a--) (r = e[a]) && (i = (c < 3 ? r(i) : c > 3 ? r(t, o, i) : r(t, o)) || i);
return c > 3 && i && Object.defineProperty(t, o, i), i;
};
Object.defineProperty(o, "__esModule", {
value: !0
});
var i = cc._decorator, a = i.ccclass, l = i.property, v = function(e) {
r(t, e);
function t() {
var t = null !== e && e.apply(this, arguments) || this;
t.label = null;
t.text = "hello";
return t;
}
t.prototype.start = function() {
this.label.string = this.text;
};
t.prototype.showVideoAds = function() {
cc.log("Show video Ads");
jsb.reflection.callStaticMethod("org/cocos2dx/javascript/JavaBridge", "LoadBottomBanner", "()V");
};
t.prototype.showReview = function() {
jsb.reflection.callStaticMethod("org/cocos2dx/javascript/JavaBridge", "TryToShowInAppReview", "()V");
};
t.prototype.showinterAds = function() {
cc.log("Show inter Ads");
jsb.reflection.callStaticMethod("org/cocos2dx/javascript/JavaBridge", "LoadRectBanner", "()V");
};
c([ l(cc.Label) ], t.prototype, "label", void 0);
c([ l ], t.prototype, "text", void 0);
return c([ a ], t);
}(cc.Component);
o.default = v;
cc._RF.pop();
}, {} ],
NativeEventHandler: [ function(e, t) {
"use strict";
cc._RF.push(t, "5375dZA8pFF9bTTZogojOuE", "NativeEventHandler");
cc.NativeEventHandler = cc.NativeEventHandler || {};
cc.NativeEventHandler.eventTarget = new cc.EventTarget();
cc.NativeEventHandler.eventReceiver = function(e, t) {
console.log("Native Event: ");
cc.NativeEventHandler.emitEvent(e, t);
};
cc.NativeEventHandler.emitEvent = function(e, t) {
cc.NativeEventHandler.eventTarget.emit(e, t);
};
cc.NativeEventHandler.onEvent = function(e, t, o) {
cc.NativeEventHandler.eventTarget.on(e, t, o);
};
t.exports = cc.NativeEventHandler;
cc._RF.pop();
}, {} ]
}, {}, [ "AdsService", "Helloworld", "NativeEventHandler" ]);