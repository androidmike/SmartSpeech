package com.changclamor.roomtosprout.smartspeech.model;

import java.util.HashMap;

import com.changclamor.roomtosprout.smartspeech.BusProvider;
import com.changclamor.roomtosprout.smartspeech.controller.ProfileController;
import com.changclamor.roomtosprout.smartspeech.controller.TransportController.RequestType;
import com.changclamor.roomtosprout.smartspeech.controller.TransportController.TransportControllerListener;
import com.changclamor.roomtosprout.smartspeech.model.TilesEvents.TileImpressionEvent;
import com.changclamor.roomtosprout.smartspeech.transport.AbstractResponse;
import com.squareup.otto.Subscribe;

public class TilesStore implements TransportControllerListener {

	// master ID to tiles mapping collection
	private HashMap<String, Tile> tilesMap = new HashMap<String, Tile>();

	public TilesStore() {
		super();
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
