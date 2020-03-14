package com.stamenkovski.mktv.utils;

import android.util.Log;

import com.stamenkovski.mktv.BuildConfig;

public class Logger {
    private static String TAG = BuildConfig.APPLICATION_ID;

    public static void e(String Msg) {
        logIt(Log.ERROR, TAG, Msg);
    }

    public static void e(String Tag, String Msg) {
        logIt(Log.ERROR, Tag, Msg);
    }

    public static void i(String Msg) {
        logIt(Log.INFO, TAG, Msg);
    }

    public static void i(String Tag, String Msg) {
        logIt(Log.INFO, Tag, Msg);
    }

    public static void d(String Msg) {
        logIt(Log.DEBUG, TAG, Msg);
    }

    public static void d(String Tag, String Msg) {
        logIt(Log.DEBUG, Tag, Msg);
    }

    public static void v(String Msg) {
        logIt(Log.VERBOSE, TAG, Msg);
    }

    public static void v(String Tag, String Msg) {
        logIt(Log.VERBOSE, Tag, Msg);
    }

    public static void w(String Msg) {
        logIt(Log.WARN, TAG, Msg);
    }

    public static void w(String Tag, String Msg) {
        logIt(Log.WARN, Tag, Msg);
    }

    private static void logIt(int LEVEL, String Tag, String Message) {
        try {
            if (BuildConfig.DEBUG) Log.println(LEVEL, Tag != null ? Tag : TAG, Message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
