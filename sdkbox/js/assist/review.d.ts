declare module sdkbox {     module ReviewListener {        /**        * trigger when alert prompt show        */        export function onDisplayAlert() : void;
        /**        * trigger when user refuse to rate        */        export function onDeclineToRate() : void;
        /**        * trigger when user want to rate        */        export function onRate() : void;
        /**        * trigger when user want to remind later        */        export function onRemindLater() : void;
    }     module PluginReview {        /**        * Set GDPR        *        * **NOTE**: please call before 'init' function        */        export function setGDPR(enabled : boolean) : void;
        /**        *  initialize the plugin instance.        */        export function init(jsonconfig : object) : boolean;
        /**        * Set listener to listen for adcolony events        */        export function setListener(listener : object) : void;
        /**        * Get the listener        */        export function getListener() : object;
        /**        * Remove the listener, and can't listen to events anymore        */        export function removeListener() : void;
        /**        * Tells 'SDKBox review plugin' to try and show the prompt (a rating alert).        * if you call `show` with `false` or null params,        * the prompt will be showed if there is connection available,        * the user hasn't declined to rate or hasn't rated current version.        * if the item `tryPromptWhenInit` in sdkbox.config is false, you can call this try to show prompt        * if you call `show` with `true` params        * the prompt will be showed without checks (the prompt is always displayed).        * The case where you should call this is if your app has an        * explicit "Rate this app" command somewhere. This is similar to rateApp,        * but instead of jumping to the review directly, an intermediary prompt is displayed.        * another case is for debug        */        export function show(force : boolean) : void;
        /**        * goto rating page directly        */        export function rate() : void;
        export function userDidSignificantEvent(canPromptForRating : boolean) : void;
        export function rateInAppstore(yes : boolean) : void;
        export function SDKBOX_DEPRECATED(setTitle(conststd::string&title : object) : object;
        export function SDKBOX_DEPRECATED(setMessage(conststd::string&message : object) : object;
        export function SDKBOX_DEPRECATED(setCancelButtonTitle(conststd::string&cancelTitle : object) : object;
        export function SDKBOX_DEPRECATED(setRateButtonTitle(conststd::string&rateTitle : object) : object;
        export function SDKBOX_DEPRECATED(setRateLaterButtonTitle(conststd::string&rateLaterTitle : object) : object;
    }}