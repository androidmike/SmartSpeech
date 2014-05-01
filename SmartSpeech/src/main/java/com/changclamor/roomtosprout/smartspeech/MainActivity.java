package com.changclamor.roomtosprout.smartspeech;

import java.util.List;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.changclamor.roomtosprout.smartspeech.controller.TilesController;
import com.changclamor.roomtosprout.smartspeech.fragments.BrandingFragment;
import com.changclamor.roomtosprout.smartspeech.fragments.HomeFragment;
import com.changclamor.roomtosprout.smartspeech.fragments.TileClickedEvent;
import com.changclamor.roomtosprout.smartspeech.fragments.WordDeleteEvent;
import com.changclamor.roomtosprout.smartspeech.fragments.TileFragment.TileListener;
import com.changclamor.roomtosprout.smartspeech.fragments.TileWorkplaceFragment;
import com.changclamor.roomtosprout.smartspeech.model.SentenceState;
import com.changclamor.roomtosprout.smartspeech.model.Tile;
import com.changclamor.roomtosprout.smartspeech.util.SpeakEngine;
import com.crashlytics.android.Crashlytics;
import com.squareup.otto.Subscribe;

public class MainActivity extends FragmentActivity implements
		BrandingFragment.BrandingFragmentListener, TileListener, OnInitListener {
	private BrandingFragment brandingFragment = new BrandingFragment();
	private HomeFragment homeFragment = new HomeFragment();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Crashlytics.start(this);
		setContentView(R.layout.root_layout);
		showBrandingSequenceFragment();

	}

	@Override
	public void onResume() {
		super.onResume();
		BusProvider.getInstance().register(this);
	}

	@Override
	public void onPause() {
		super.onPause();
		BusProvider.getInstance().unregister(this);
	}

	private void showBrandingSequenceFragment() {
		android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager()
				.beginTransaction();
		ft.replace(R.id.main_viewgroup, brandingFragment).commit();
	}

	@Override
	public void onBrandingSequenceComplete() {
		goHome();
	}

	public void goHome() {
		showFragment(homeFragment);
	}

	private void showFragment(Fragment fragment) {
		android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
		android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
		/*
		 * ft.setCustomAnimations(android.R.anim.slide_in_left,
		 * android.R.anim.slide_out_right);
		 */
		ft.replace(R.id.main_viewgroup, fragment).addToBackStack(null).commit();
	}

	@Override
	public void onTileClicked(String tileId) {
		Tile tile = TilesController.getInstance().getTile(tileId);
		if (false) {
			// Check for profile preference to speak as you go
			SpeakEngine.speak(tile.getLabel());
		} else {
			SentenceState.getSentence().add(tileId);
			if (SentenceState.getSentence().isComplete()) {
				SpeakEngine.speak(SentenceState.getSentence().toString());
				SentenceState.getSentence().clear();
			}
		}
		showWorkplace(tile.getPredictedTags());
	}

	private void showWorkplace(List<String> predictedTags) {
		android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager()
				.beginTransaction();
		TileWorkplaceFragment wsFragment = TileWorkplaceFragment
				.newInstance(predictedTags);

		ft.replace(R.id.main_viewgroup, wsFragment, TileWorkplaceFragment.tag)
				.addToBackStack(TileWorkplaceFragment.tag).commit();
	}

	@Override
	public void onInit(int status) {
		// TODO Auto-generated method stub

	}

	@Subscribe
	public void onWordDelete(WordDeleteEvent event) {
		SentenceState.getSentence().remove(event.id);
	}

	@Subscribe
	public void onTileClicked(TileClickedEvent event) {
		showWorkplace(TilesController.getInstance().getTile(event.id).getPredictedTags());
		SentenceState.getSentence().add(event.id);
	}
}