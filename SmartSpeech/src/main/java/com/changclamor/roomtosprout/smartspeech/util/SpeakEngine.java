package com.changclamor.roomtosprout.smartspeech.util;

import java.util.HashMap;

import android.annotation.SuppressLint;
import android.media.AudioManager;
import android.os.Handler;
import android.os.Looper;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.speech.tts.UtteranceProgressListener;
import android.util.Log;

import com.changclamor.roomtosprout.smartspeech.BusProvider;
import com.changclamor.roomtosprout.smartspeech.SmartSpeechApp;
import com.changclamor.roomtosprout.smartspeech.fragments.SpeakCompleteEvent;

public class SpeakEngine {

    public static TextToSpeech tts;

    public static boolean ttsInitialized = false;

    private static Runnable pendingRunnable = null;

    private static Handler handler = new Handler(Looper.getMainLooper());

    public enum Emotion {

        NORMAL(1.0f, 1.0f), ANGRY(3.0f, 1.0f), SAD(.5f, 0.5f), HAPPY(2.0f, 1.0f), LOUD(1.0f, 1.0f), QUIET(1.0f, 1.0f), QUESTION(
                1.0f, 1.0f);
        public float pitch, speed;

        Emotion(float pitch, float speed) {
            this.pitch = pitch;
            this.speed = speed;
        }
    }

    public static void speak(String words) {
        speak(words, Emotion.NORMAL);
    }

    public static void init() {
        OnInitListener listener = new OnInitListener() {

            @SuppressLint("NewApi")
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    ttsInitialized = true;
                    if (pendingRunnable != null) {
                        pendingRunnable.run();
                    }
                }
            }
        };
        tts = new TextToSpeech(SmartSpeechApp.getContext(), listener);
    }

    private static String spokenWords;

    @SuppressLint("NewApi")
    public static void speak(final String words, final Emotion emotion) {
        pendingRunnable = new Runnable() {

            @Override
            public void run() {
                HashMap<String, String> myHashAlarm = new HashMap<String, String>();
                myHashAlarm.put(TextToSpeech.Engine.KEY_PARAM_STREAM, String.valueOf(AudioManager.STREAM_ALARM));
                myHashAlarm.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "sentence_id");

                tts.setOnUtteranceProgressListener(new UtteranceProgressListener() {

                    @Override
                    public void onStart(String utteranceId) {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void onError(String utteranceId) {
                        throw new RuntimeException();
                    }

                    @Override
                    public void onDone(String utteranceId) {

                        handler.post(new Runnable() {

                            @Override
                            public void run() {
                                try {
                                    BusProvider.getInstance().post(new SpeakCompleteEvent());
                                    Log.d("mchang", "posted");
                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        });
                    }
                });
                tts.setPitch(emotion.pitch);
                tts.setSpeechRate(emotion.speed);
                tts.speak(spokenWords, TextToSpeech.QUEUE_FLUSH, myHashAlarm);

                pendingRunnable = null;
            }
        };
        if (ttsInitialized) {
            pendingRunnable.run();
        }
    }
}
