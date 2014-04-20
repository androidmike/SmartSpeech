package com.changclamor.roomtosprout.smartspeech.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by androidmike on 4/19/14.
 */
public class UIUtil {

    private static Context context;

    public static void init(Context cxt) {
        context = cxt;
    }

    public static int getScreenWidth() {
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        display.getMetrics(metrics);

        return metrics.widthPixels;
    }

    public static int getScreenHeight() {
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        display.getMetrics(metrics);

        return metrics.heightPixels;
    }
}
