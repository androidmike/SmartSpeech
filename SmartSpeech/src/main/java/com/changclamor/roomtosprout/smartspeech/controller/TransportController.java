package com.changclamor.roomtosprout.smartspeech.controller;

import com.changclamor.roomtosprout.smartspeech.transport.AbstractResponse;

public class TransportController {
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
