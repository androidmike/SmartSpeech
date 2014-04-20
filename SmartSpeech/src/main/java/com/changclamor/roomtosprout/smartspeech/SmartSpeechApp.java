package com.changclamor.roomtosprout.smartspeech;

import android.app.Application;
import android.content.Context;

import com.changclamor.roomtosprout.smartspeech.util.UIUtil;

/**
 * Created by androidmike on 4/19/14.
 */
public class SmartSpeechApp extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        initSingletons();
    }

    protected void initSingletons() {
        UIUtil.init(context);
    }

    public static Context getContext() {
        return context;
    }
}
