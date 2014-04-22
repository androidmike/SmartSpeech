package com.changclamor.roomtosprout.smartspeech.controller;

import java.util.HashMap;

import com.changclamor.roomtosprout.smartspeech.model.Tile;

public class TilesController {

	private static final String YOU_ID = "you";
	private static final String ME_ID = "me";
	private static TilesController instance = null;
	private String[] homeTilesId = { ME_ID, YOU_ID };
	private HashMap<String, Tile> tileMap = null;

	public static void init() {
		instance = new TilesController();
		instance.loadTileMap();
	}

	private void loadTileMap() {
		Tile t1 = new Tile(YOU_ID);
		t1.setDrawableResId(0);
		t1.setLabel("You");
		t1.setRecScore(0);
	}

	public String[] getHomeTilesId() {
		return homeTilesId;
	}

	public static TilesController getInstance() {
		return instance;
	}

	public Tile getTile(String id) {
		return null;
	}
}
