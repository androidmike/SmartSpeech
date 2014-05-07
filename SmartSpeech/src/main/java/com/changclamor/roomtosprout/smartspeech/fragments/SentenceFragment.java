package com.changclamor.roomtosprout.smartspeech.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.changclamor.roomtosprout.smartspeech.BusProvider;
import com.changclamor.roomtosprout.smartspeech.R;
import com.changclamor.roomtosprout.smartspeech.SmartSpeechApp;
import com.changclamor.roomtosprout.smartspeech.model.SentenceState;
import com.changclamor.roomtosprout.smartspeech.ui.FlowLayout;
import com.changclamor.roomtosprout.smartspeech.ui.WordView;
import com.changclamor.roomtosprout.smartspeech.util.SpeakEngine;
import com.changclamor.roomtosprout.smartspeech.util.SpeakEngine.Emotion;
import com.squareup.otto.Subscribe;

public class SentenceFragment extends Fragment {
    public static final String tag = SentenceFragment.class.getCanonicalName();

    private FlowLayout mainContainer = null;

    private View speakButton = null;

    private View resetButton = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sentence_layout, null);
        mainContainer = (FlowLayout) view.findViewById(R.id.sentence_main_container);
        speakButton = view.findViewById(R.id.sentence_speak_button);
        resetButton = view.findViewById(R.id.sentence_clear_button);
        view.findViewById(R.id.sentence_speak_button_sad).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                SpeakEngine.speak(SentenceState.getSentence().toString(), Emotion.SAD);
            }
        });
        view.findViewById(R.id.sentence_speak_button_angry).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                SpeakEngine.speak(SentenceState.getSentence().toString(), Emotion.ANGRY);
            }
        });
        speakButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                SpeakEngine.speak(SentenceState.getSentence().toString());
            }
        });
        resetButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                SentenceState.getSentence().clear();
            }
        });
        return view;
    }

    public void onResume() {
        super.onResume();
        BusProvider.getInstance().register(this);
        reloadSentence();
    }

    @Subscribe
    public void onSentenceChanged(SentenceChangedEvent event) {
        reloadSentence();
    }

    private void reloadSentence() {
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
