package com.changclamor.roomtosprout.smartspeech.util;

import android.speech.tts.TextToSpeech;

import com.changclamor.roomtosprout.smartspeech.SmartSpeechApp;

public class SpeakEngine {

    public static TextToSpeech tts;

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

    public static void speak(String words, Emotion emotion) {

        if (tts == null) {
            tts = new TextToSpeech(SmartSpeechApp.getContext(), null);
        }

        String spokenWords = words.replace("/", " ");
        tts.setPitch(emotion.pitch);
        tts.setSpeechRate(emotion.speed);
        tts.speak(spokenWords, TextToSpeech.QUEUE_FLUSH, null);
    }
}
