package com.changclamor.roomtosprout.smartspeech.controller;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.changclamor.roomtosprout.smartspeech.SmartSpeechApp;
import com.changclamor.roomtosprout.smartspeech.transport.AbstractResponse;

public class TransportController {
	private RequestQueue queue = null;

	public TransportController() {
		super();
		queue = Volley.newRequestQueue(SmartSpeechApp.getContext());
	}

	public enum RequestType {
		FETCH_TILES
	}

	public interface TransportControllerListener {
		public void onRequestSuccess(RequestType type, AbstractResponse resp);

		public void onRequestFailed(RequestType type);
	}

	public void makeRequest(RequestType type) {

	}
}
