window.__require = function t(e, o, r) {
function n(i, l) {
if (!o[i]) {
if (!e[i]) {
var p = i.split("/");
p = p[p.length - 1];
if (!e[p]) {
var u = "function" == typeof __require && __require;
if (!l && u) return u(p, !0);
if (c) return c(p, !0);
throw new Error("Cannot find module '" + i + "'");
}
i = p;
}
var f = o[i] = {
exports: {}
};
e[i][0].call(f.exports, function(t) {
return n(e[i][1][t] || t);
}, f, f.exports, t, e, o, r);
}
return o[i].exports;
}
for (var c = "function" == typeof __require && __require, i = 0; i < r.length; i++) n(r[i]);
return n;
}({
Helloworld: [ function(t, e, o) {
"use strict";
cc._RF.push(e, "e1b90/rohdEk4SdmmEZANaD", "Helloworld");
var r, n = this && this.__extends || (r = function(t, e) {
return (r = Object.setPrototypeOf || {
__proto__: []
} instanceof Array && function(t, e) {
t.__proto__ = e;
} || function(t, e) {
for (var o in e) Object.prototype.hasOwnProperty.call(e, o) && (t[o] = e[o]);
})(t, e);
}, function(t, e) {
r(t, e);
function o() {
this.constructor = t;
}
t.prototype = null === e ? Object.create(e) : (o.prototype = e.prototype, new o());
}), c = this && this.__decorate || function(t, e, o, r) {
var n, c = arguments.length, i = c < 3 ? e : null === r ? r = Object.getOwnPropertyDescriptor(e, o) : r;
if ("object" == typeof Reflect && "function" == typeof Reflect.decorate) i = Reflect.decorate(t, e, o, r); else for (var l = t.length - 1; l >= 0; l--) (n = t[l]) && (i = (c < 3 ? n(i) : c > 3 ? n(e, o, i) : n(e, o)) || i);
return c > 3 && i && Object.defineProperty(e, o, i), i;
};
Object.defineProperty(o, "__esModule", {
value: !0
});
var i = cc._decorator, l = i.ccclass, p = i.property, u = function(t) {
n(e, t);
function e() {
var e = null !== t && t.apply(this, arguments) || this;
e.label = null;
e.text = "hello";
return e;
}
e.prototype.start = function() {
this.label.string = this.text;
};
e.prototype.showVideoAds = function() {
cc.log("Show video Ads");
jsb.reflection.callStaticMethod("org/cocos2dx/javascript/AdsServiceHelper", "ShowVideoAds", "()V");
};
c([ p(cc.Label) ], e.prototype, "label", void 0);
c([ p ], e.prototype, "text", void 0);
return c([ l ], e);
}(cc.Component);
o.default = u;
cc._RF.pop();
}, {} ]
}, {}, [ "Helloworld" ]);