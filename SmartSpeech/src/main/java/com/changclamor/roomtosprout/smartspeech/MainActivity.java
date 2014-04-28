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
import com.changclamor.roomtosprout.smartspeech.fragments.TileFragment.TileListener;
import com.changclamor.roomtosprout.smartspeech.fragments.TileWorkplaceFragment;
import com.changclamor.roomtosprout.smartspeech.model.SentenceState;
import com.changclamor.roomtosprout.smartspeech.model.Tile;
import com.crashlytics.android.Crashlytics;

public class MainActivity extends FragmentActivity implements
		BrandingFragment.BrandingFragmentListener, TileListener, OnInitListener {
	private BrandingFragment brandingFragment = new BrandingFragment();
	private HomeFragment homeFragment = new HomeFragment();
	private TextToSpeech tts;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Crashlytics.start(this);
		setContentView(R.layout.root_layout);
		showBrandingSequenceFragment();

	}

	private void showBrandingSequenceFragment() {
		android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager()
				.beginTransaction();
		ft.replace(R.id.main_viewgroup, brandingFragment).commit();
	}

	@Override
	public void onBrandingSequenceComplete() {
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
			speak(tile.getLabel());
		} else {
			SentenceState.getSentence().add(tileId);
			if (SentenceState.getSentence().isComplete()) {
				speak(SentenceState.getSentence().toString());
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

	private void speak(String words) {
		if (tts == null) {
			tts = new TextToSpeech(this, this);
		}
		String spokenWords = words.replace("/", " ");
		tts.speak(spokenWords, TextToSpeech.QUEUE_FLUSH, null);
	}

	@Override
	public void onInit(int status) {
		// TODO Auto-generated method stub

	}

}