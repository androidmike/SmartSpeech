package com.changclamor.roomtosprout.smartspeech;

import android.app.Application;
import android.content.Context;

import com.changclamor.roomtosprout.smartspeech.controller.TilesController;
import com.changclamor.roomtosprout.smartspeech.util.UIUtil;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

/**
 * Created by androidmike on 4/19/14.
 */
public class SmartSpeechApp extends Application {
	private static final String TRACKING_ID = "UA-50224611-1";
	private static Tracker tracker = null;
	private static Context context;

	@Override
	public void onCreate() {
		super.onCreate();
		context = getApplicationContext();
		initSingletons();
	}

	protected void initSingletons() {
		UIUtil.init(context);
		TilesController.init();
	}

	public static Context getContext() {
		return context;
	}

	public synchronized static Tracker getTracker() {
		if (tracker == null) {
			GoogleAnalytics analytics = GoogleAnalytics.getInstance(context);
			tracker = analytics.newTracker(TRACKING_ID);
		}
		return tracker;
	}
}
