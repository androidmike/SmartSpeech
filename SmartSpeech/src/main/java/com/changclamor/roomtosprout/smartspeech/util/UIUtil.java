package com.changclamor.roomtosprout.smartspeech.util;

import java.util.HashMap;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
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
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		display.getMetrics(metrics);

		return metrics.widthPixels;
	}

	public static int getScreenHeight() {
		DisplayMetrics metrics = new DisplayMetrics();
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		display.getMetrics(metrics);

		return metrics.heightPixels;
	}

	@SuppressWarnings("deprecation")
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	public static void setBackground(View view, Drawable drawable) {
		if (VERSION.SDK_INT > VERSION_CODES.JELLY_BEAN) {
			view.setBackground(drawable);
		} else {
			view.setBackgroundDrawable(drawable);
		}
	}
}
