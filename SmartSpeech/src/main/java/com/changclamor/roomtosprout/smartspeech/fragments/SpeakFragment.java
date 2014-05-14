package com.changclamor.roomtosprout.smartspeech.fragments;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.OvershootInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.changclamor.roomtosprout.smartspeech.R;
import com.changclamor.roomtosprout.smartspeech.SmartSpeechApp;
import com.changclamor.roomtosprout.smartspeech.model.SentenceState;

public class SpeakFragment extends TrackingFragment {
    private ViewGroup mainView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mainView = (ViewGroup) inflater.inflate(R.layout.speak_layout, container, false);
        return mainView;
    }

    public void onResume() {
        super.onResume();
        loadWords();

    }

    private void loadWords() {
        String[] words = SentenceState.getSentence().toString().split(" ");

        final List<TextView> textViews = new ArrayList<TextView>();
        for (String word : words) {
            TextView textView = new TextView(SmartSpeechApp.getContext());
            textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            textView.setPadding(20, 20, 20, 20);
            textView.setText(word);
            textView.setTextSize(50f);
            textViews.add(textView);
        }
        Handler handler = new Handler(Looper.getMainLooper());
        for (int i = 0; i < textViews.size(); i++) {
            final int index = i;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Animation scaleAnimation = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, .5f,
                            Animation.RELATIVE_TO_SELF, .5f);

                    mainView.addView(textViews.get(index));
                    scaleAnimation.setDuration(300);
                    scaleAnimation.setInterpolator(new OvershootInterpolator());
                    textViews.get(index).startAnimation(scaleAnimation);
                }
            }, i * 300);
        }
    }
}
