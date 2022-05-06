package org.cocos2dx.javascript;

// JAVA
public class JsbBridge {
    public interface ICallback{
        /**
         * Applies this callback to the given argument.
         *
         * @param arg0 as input
         * @param arg1 as input
         */
        void onScript(String arg0, String arg1);
    }
    /* Add a callback which you would like to apply
     * @param f ICallback, the method which will be actually applied. multiple calls will override
     * multiple calls will override */
    public static void setCallback(ICallback f){

    }
    /*
     * Java dispatch Js event, use native c++ code
     * @param arg0 input values
     */
    public static void sendToScript(String arg0, String arg1){

    }

    public static void sendToScript(String arg0){

    }
}
