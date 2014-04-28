package com.changclamor.roomtosprout.smartspeech.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.changclamor.roomtosprout.smartspeech.BusProvider;
import com.changclamor.roomtosprout.smartspeech.R;
import com.squareup.otto.Subscribe;

public class SentenceFragment extends Fragment {
	public static final String tag = SentenceFragment.class.getCanonicalName();
	private View mainContainer = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.sentence_layout, null);
		mainContainer = view.findViewById(R.id.main_container);
		return view;
	}

	public void onResume() {
		super.onResume();
		BusProvider.getInstance().register(this);
	}

	@SuppressLint("NewApi")
	@Subscribe
	public void onGridScroll(GridScrollEvent event) {
		Log.d("temp", String.valueOf(event.diff));
		((FrameLayout.LayoutParams) mainContainer.getLayoutParams()).topMargin = (int) event.diff;
		mainContainer.requestLayout();

	}

	public void onPause() {
		super.onPause();
		BusProvider.getInstance().unregister(this);
	}
}
