#include "PluginReviewJSHelper.h"
#include "PluginReview/PluginReview.h"
#include "SDKBoxJSHelper.h"

#ifdef SDKBOX_JSBINDING_CC3
#include "cocos/bindings/jswrapper/SeApi.h"
#include "cocos/bindings/manual/jsb_conversions.h"

namespace cocos2d = cc;
#else
#include "cocos/scripting/js-bindings/jswrapper/SeApi.h"
#include "cocos/scripting/js-bindings/manual/jsb_conversions.hpp"
#endif

class ReviewListenerJS : public sdkbox::ReviewListener, public sdkbox::JSListenerBase
{
public:
    ReviewListenerJS() : sdkbox::JSListenerBase() {
    }

    void onDisplayAlert() {
        RUN_ON_MAIN_THREAD_BEGIN
        MAKE_V8_HAPPY
        se::ValueArray args;
        invokeJSFun(funcName, args);
        RUN_ON_MAIN_THREAD_END
    }

    void onDeclineToRate() {
        RUN_ON_MAIN_THREAD_BEGIN
        MAKE_V8_HAPPY
        se::ValueArray args;
        invokeJSFun(funcName, args);
        RUN_ON_MAIN_THREAD_END

    }

    void onRate() {
        RUN_ON_MAIN_THREAD_BEGIN
        MAKE_V8_HAPPY
        se::ValueArray args;
        invokeJSFun(funcName, args);
        RUN_ON_MAIN_THREAD_END
    }

    void onRemindLater() {
        RUN_ON_MAIN_THREAD_BEGIN
        MAKE_V8_HAPPY
        se::ValueArray args;
        invokeJSFun(funcName, args);
        RUN_ON_MAIN_THREAD_END
    }
};

static bool js_PluginReviewJS_setListener(se::State& s)
{
    const auto& args = s.args();
    int argc = (int)args.size();
    if (argc == 1)
    {
        ReviewListenerJS* nativeDelegate = dynamic_cast<ReviewListenerJS*>(sdkbox::PluginReview::getListener());
        if (!nativeDelegate) {
            nativeDelegate = new (std::nothrow) ReviewListenerJS();
            sdkbox::PluginReview::setListener(nativeDelegate);
        }
        nativeDelegate->setJSDelegate(args[0]);


        return true;
    }

    SE_REPORT_ERROR("wrong number of arguments: %d, was expecting %d", argc, 1);
    return false;
}
SE_BIND_FUNC(js_PluginReviewJS_setListener)

extern se::Object* __jsb_sdkbox_PluginReview_proto;
extern se::Class* __jsb_sdkbox_PluginReview_class;
bool register_all_PluginReviewJS_helper(se::Object* obj)
{
    auto pluginValue = sdkbox::getPluginValue(obj, "sdkbox.PluginReview");
    auto plugin = pluginValue.toObject();
    plugin->defineFunction("setListener", _SE(js_PluginReviewJS_setListener));

    se::ScriptEngine::getInstance()->clearException();
    return true;
}


