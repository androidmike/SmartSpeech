package com.changclamor.roomtosprout.smartspeech.controller;

import java.util.HashMap;

import com.changclamor.roomtosprout.smartspeech.BusProvider;
import com.changclamor.roomtosprout.smartspeech.model.Profile;
import com.changclamor.roomtosprout.smartspeech.model.TilesEvents.TileImpressionEvent;
import com.squareup.otto.Subscribe;

public class ProfileController {
	private static ProfileController instance = null;
	private Profile current = null;
	private HashMap<String, Profile> profiles = new HashMap<String, Profile>();

	public void init() {
		instance = new ProfileController();
		BusProvider.getInstance().register(this);
	}

	public void login(String userId) {
		saveCurrentProfile();
		current = profiles.get(userId);
	}

	private void saveCurrentProfile() {
		// TODO Auto-generated method stub

	}

	public static ProfileController getInstance() {
		return instance;
	}

	@Subscribe
	public void onTileShown(TileImpressionEvent event) {
		if (event == null || event.id == null) {
			return;
		}
		current.onTileShown(event.id);
	}
}
