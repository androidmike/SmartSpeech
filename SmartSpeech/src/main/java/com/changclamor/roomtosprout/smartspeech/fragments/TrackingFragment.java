package com.changclamor.roomtosprout.smartspeech.fragments;

import android.support.v4.app.Fragment;

import com.changclamor.roomtosprout.smartspeech.SmartSpeechApp;
import com.google.android.gms.analytics.HitBuilders;

public class TrackingFragment extends Fragment {

	@Override
	public void onResume() {
		super.onResume();
		SmartSpeechApp.getTracker().setScreenName(
				this.getClass().getSimpleName());
		SmartSpeechApp.getTracker().send(
				new HitBuilders.AppViewBuilder().build());
	}
}
