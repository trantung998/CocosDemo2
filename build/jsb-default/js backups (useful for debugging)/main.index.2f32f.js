window.__require = function e(t, n, r) {
function o(i, a) {
if (!n[i]) {
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
var p = n[i] = {
exports: {}
};
t[i][0].call(p.exports, function(e) {
return o(t[i][1][e] || e);
}, p, p.exports, e, t, n, r);
}
return n[i].exports;
}
for (var c = "function" == typeof __require && __require, i = 0; i < r.length; i++) o(r[i]);
return o;
}({
AdsService: [ function(e, t) {
"use strict";
cc._RF.push(t, "15f35kQ7t1ITaILp2/a8Jxh", "AdsService");
cc._RF.pop();
}, {} ],
Helloworld: [ function(e, t, n) {
"use strict";
cc._RF.push(t, "e1b90/rohdEk4SdmmEZANaD", "Helloworld");
var r, o = this && this.__extends || (r = function(e, t) {
return (r = Object.setPrototypeOf || {
__proto__: []
} instanceof Array && function(e, t) {
e.__proto__ = t;
} || function(e, t) {
for (var n in t) Object.prototype.hasOwnProperty.call(t, n) && (e[n] = t[n]);
})(e, t);
}, function(e, t) {
r(e, t);
function n() {
this.constructor = e;
}
e.prototype = null === t ? Object.create(t) : (n.prototype = t.prototype, new n());
}), c = this && this.__decorate || function(e, t, n, r) {
var o, c = arguments.length, i = c < 3 ? t : null === r ? r = Object.getOwnPropertyDescriptor(t, n) : r;
if ("object" == typeof Reflect && "function" == typeof Reflect.decorate) i = Reflect.decorate(e, t, n, r); else for (var a = e.length - 1; a >= 0; a--) (o = e[a]) && (i = (c < 3 ? o(i) : c > 3 ? o(t, n, i) : o(t, n)) || i);
return c > 3 && i && Object.defineProperty(t, n, i), i;
};
Object.defineProperty(n, "__esModule", {
value: !0
});
var i = cc._decorator, a = i.ccclass, l = i.property, v = function(e) {
o(t, e);
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
jsb.reflection.callStaticMethod("org/cocos2dx/javascript/AdsServiceHelper", "LoadBottomBanner", "()V");
};
t.prototype.showinterAds = function() {
cc.log("Show inter Ads");
jsb.reflection.callStaticMethod("org/cocos2dx/javascript/AdsServiceHelper", "LoadRectBanner", "()V");
};
c([ l(cc.Label) ], t.prototype, "label", void 0);
c([ l ], t.prototype, "text", void 0);
return c([ a ], t);
}(cc.Component);
n.default = v;
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
cc.NativeEventHandler.onEvent = function(e, t, n) {
cc.NativeEventHandler.eventTarget.on(e, t, n);
};
t.exports = cc.NativeEventHandler;
cc._RF.pop();
}, {} ]
}, {}, [ "AdsService", "Helloworld", "NativeEventHandler" ]);