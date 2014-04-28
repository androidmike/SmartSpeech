package com.changclamor.roomtosprout.smartspeech.util;

import java.util.HashMap;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
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

	// No gradient background
	public static Drawable getBackgroundDrawable(int color) {
		return UIUtil.getBackgroundDrawable(color, color);
	}

	public static Drawable getBackgroundDrawable(int bottomColor, int topColor) {
		GradientDrawable pressed = new GradientDrawable(Orientation.BOTTOM_TOP,
				new int[] { bottomColor, topColor });
		pressed.setShape(GradientDrawable.RECTANGLE);
		pressed.setCornerRadius(10.f);
		pressed.setStroke(0, Color.parseColor("#00000000"));

		GradientDrawable normal = new GradientDrawable(Orientation.BOTTOM_TOP,
				new int[] { bottomColor, topColor });
		// TODO: lighten up bottom color and top color
		normal.setShape(GradientDrawable.RECTANGLE);
		normal.setCornerRadius(10.f);
		normal.setStroke(10, Color.parseColor("#00000000"));

		StateListDrawable states = new StateListDrawable();
		states.addState(new int[] { android.R.attr.state_pressed }, pressed);
		states.addState(new int[] { android.R.attr.state_focused }, pressed);
		states.addState(new int[] {}, normal);
		return states;

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
