package org.androidkid.logging;

/**
 * Created by fanhaojun on 15/4/21.
 */
public class NativeLog implements Log {

    private final String tag;

    NativeLog(String name) {
        tag = name;
    }

    public void debug(String message) {
        android.util.Log.d(tag, message);
    }


    public void debug(String message, Throwable t) {
        android.util.Log.d(tag, message, t);
    }


    public void error(String message) {
        android.util.Log.e(tag, message);
    }


    public void error(String message, Throwable t) {
        android.util.Log.e(tag, message, t);
    }


    public void fatal(String message) {
        android.util.Log.wtf(tag, message);
    }


    public void fatal(String message, Throwable t) {
        android.util.Log.wtf(tag, message, t);
    }


    public void info(String message) { android.util.Log.i(tag, message); }


    public void info(String message, Throwable t) {
        android.util.Log.i(tag, message, t);
    }


    public void trace(String message) {
        android.util.Log.v(tag, message);
    }


    public void trace(String message, Throwable t) {
        android.util.Log.v(tag, message, t);
    }


    public void warn(String message) {
        android.util.Log.w(tag, message);
    }


    public void warn(String message, Throwable t) {
        android.util.Log.w(tag, message, t);
    }

}
