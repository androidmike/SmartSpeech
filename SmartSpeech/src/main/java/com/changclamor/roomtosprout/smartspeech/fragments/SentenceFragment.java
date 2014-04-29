package com.changclamor.roomtosprout.smartspeech.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.changclamor.roomtosprout.smartspeech.BusProvider;
import com.changclamor.roomtosprout.smartspeech.R;
import com.changclamor.roomtosprout.smartspeech.SmartSpeechApp;
import com.changclamor.roomtosprout.smartspeech.model.SentenceState;
import com.changclamor.roomtosprout.smartspeech.ui.WordView;
import com.squareup.otto.Subscribe;

public class SentenceFragment extends Fragment {
	public static final String tag = SentenceFragment.class.getCanonicalName();
	private LinearLayout mainContainer = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.sentence_layout, null);
		mainContainer = (LinearLayout) view.findViewById(R.id.main_container);
		return view;
	}

	public void onResume() {
		super.onResume();
		BusProvider.getInstance().register(this);
	}

	@Subscribe
	public void onGridScroll(GridScrollEvent event) {

	}

	@Subscribe
	public void onSentenceChanged(SentenceChangedEvent event) {
		mainContainer.removeAllViews();
		for (String id : SentenceState.getSentence().getIds()) {
			WordView view = new WordView(SmartSpeechApp.getContext());
			view.setTileId(id);
			mainContainer.addView(view);
		}
	}

	public void onPause() {
		super.onPause();
		BusProvider.getInstance().unregister(this);
	}
}
