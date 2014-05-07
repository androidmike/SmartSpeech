package com.changclamor.roomtosprout.smartspeech;

import java.util.List;

import android.os.Bundle;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.changclamor.roomtosprout.smartspeech.controller.TilesController;
import com.changclamor.roomtosprout.smartspeech.fragments.BrandingFragment;
import com.changclamor.roomtosprout.smartspeech.fragments.HomeFragment;
import com.changclamor.roomtosprout.smartspeech.fragments.SpeakFragment;
import com.changclamor.roomtosprout.smartspeech.fragments.TileClickedEvent;
import com.changclamor.roomtosprout.smartspeech.fragments.TileWorkplaceFragment;
import com.changclamor.roomtosprout.smartspeech.fragments.WordDeleteEvent;
import com.changclamor.roomtosprout.smartspeech.model.SentenceState;
import com.changclamor.roomtosprout.smartspeech.util.SpeakEngine;
import com.crashlytics.android.Crashlytics;
import com.squareup.otto.Subscribe;

public class MainActivity extends FragmentActivity implements BrandingFragment.BrandingFragmentListener, OnInitListener {
    private BrandingFragment brandingFragment = new BrandingFragment();
    private SpeakFragment speakFragment = new SpeakFragment();
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
        android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
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

    private void showWorkplace(List<String> predictedTags) {
        android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        TileWorkplaceFragment wsFragment = TileWorkplaceFragment.newInstance(predictedTags);

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
        if (SentenceState.getSentence().isEmpty()) {
            goHome();
        }
    }

    @Subscribe
    public void onTileClicked(TileClickedEvent event) {

        if (event.id.equals("question")) {
            SentenceState.getSentence().add(event.id);
            showSpeakFragment(true, true);
        } else if (event.id.equals("speak")) {
            SpeakEngine.speak(SentenceState.getSentence().toString());
            showSpeakFragment(true, true);
        } else {
            showWorkplace(TilesController.getInstance().getTile(event.id).getPredictedTags());
            SentenceState.getSentence().add(event.id);
        }
    }

    private void showSpeakFragment(boolean clear, boolean goHome) {
        android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.main_viewgroup, speakFragment).commit();
        if (clear) {
         //   SentenceState.getSentence().clear();
        }
        if (goHome) {
         //  goHome();
        } else {
          //  onBackPressed();
        }
    }
}