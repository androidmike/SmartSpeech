package com.changclamor.roomtosprout.smartspeech.model;

import java.net.Authenticator.RequestorType;
import java.util.HashMap;

import com.changclamor.roomtosprout.smartspeech.controller.TransportController.RequestType;
import com.changclamor.roomtosprout.smartspeech.controller.TransportController.TransportControllerListener;
import com.changclamor.roomtosprout.smartspeech.transport.AbstractResponse;
import com.changclamor.roomtosprout.smartspeech.transport.TilesFetchResponse;

public class TilesStore implements TransportControllerListener {

	// master ID to tiles mapping collection
	private HashMap<String, Tile> tilesMap = new HashMap<String, Tile>();

	public class TileStore {

	}

	public void loadTiles() {
		fetchTiles();
	}

	private void fetchTiles() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onRequestSuccess(RequestType type, AbstractResponse resp) {
		if (type != RequestType.FETCH_TILES) {
			return;
		}
	}

	@Override
	public void onRequestFailed(RequestType type) {
		if (type != RequestType.FETCH_TILES) {
			return;
		}
	}
}
