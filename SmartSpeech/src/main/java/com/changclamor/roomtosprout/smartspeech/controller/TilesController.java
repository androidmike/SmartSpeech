package com.changclamor.roomtosprout.smartspeech.controller;

import java.util.HashMap;

import android.content.res.Resources.NotFoundException;

import com.changclamor.roomtosprout.smartspeech.R;
import com.changclamor.roomtosprout.smartspeech.SmartSpeechApp;
import com.changclamor.roomtosprout.smartspeech.model.Tile;
import com.changclamor.roomtosprout.smartspeech.style.TileStyle;
import com.changclamor.roomtosprout.smartspeech.transport.GetTilesResponse;
import com.changclamor.roomtosprout.smartspeech.util.TransportUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class TilesController {

	public static final String YOU_ID = "you";
	public static final String ME_ID = "me";
	public static final String THIRD_PERSON_ID = "third_person";
	public static final String FEELINGS_ID = "feelings";
	public static final String QUESTIONS_ID = "questions";

	private static TilesController instance = null;
	private String[] homeTilesId = { ME_ID, YOU_ID, THIRD_PERSON_ID,
			FEELINGS_ID };
	private HashMap<String, Tile> tilesMap = null;

	public static void init() {
		instance = new TilesController();
		instance.loadTilesMap();
	}

	private void loadTilesMap() {
		Gson gson = new GsonBuilder().create();
		String json = null;
		try {
			json = TransportUtil.convertStreamToString(SmartSpeechApp
					.getContext().getResources().openRawResource(R.raw.tiles));
		} catch (NotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		GetTilesResponse r = gson.fromJson(json, GetTilesResponse.class);

		tilesMap = new HashMap<String, Tile>();
		for (Tile t : r.tiles) {
			tilesMap.put(t.getId(), t);
		}
	}

	public String[] getHomeTilesId() {
		return homeTilesId;
	}

	public static TilesController getInstance() {
		return instance;
	}

	public Tile getTile(String id) {
		return tilesMap.get(id);
	}
}
