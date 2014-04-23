package com.changclamor.roomtosprout.smartspeech.controller;

import java.util.HashMap;

import com.changclamor.roomtosprout.smartspeech.R;
import com.changclamor.roomtosprout.smartspeech.model.Tile;
import com.changclamor.roomtosprout.smartspeech.style.TileStyle;

public class TilesController {

	private static final String YOU_ID = "you";
	private static final String ME_ID = "me";
	private static final String THIRD_PERSON_ID = "3rd";
	private static final String FEELINGS_ID = "feelings";
	private static final String QUESTIONS_ID = "questions";

	private static TilesController instance = null;
	private String[] homeTilesId = { ME_ID, YOU_ID, THIRD_PERSON_ID,
			FEELINGS_ID };
	private HashMap<String, Tile> tilesMap = null;

	public static void init() {
		instance = new TilesController();
		instance.loadtilesMap();
	}

	private void loadtilesMap() {
		tilesMap = new HashMap<String, Tile>();
		Tile t1 = new Tile(YOU_ID);
		t1.setDrawableResId(R.drawable.icon_you);
		t1.setLabel("You");
		t1.setRecScore(0);
		tilesMap.put(YOU_ID, t1);

		Tile t2 = new Tile(YOU_ID);
		t2.setDrawableResId(R.drawable.icon_me);
		t2.setLabel("Me");
		t2.setRecScore(0);
		t2.setStyle(TileStyle.XLARGE);
		t2.setStyle(TileStyle.MEDIUM);
		tilesMap.put(ME_ID, t2);

		Tile t3 = new Tile(THIRD_PERSON_ID);
		t3.setDrawableResId(R.drawable.icon_me);
		t3.setLabel("3rd Person");
		t3.setRecScore(0);
		t3.setStyle(TileStyle.MEDIUM);
		tilesMap.put(THIRD_PERSON_ID, t3);

		Tile t4 = new Tile(FEELINGS_ID);
		t4.setDrawableResId(R.drawable.icon_me);
		t4.setLabel("Feelings");
		t4.setRecScore(0);
		t4.setStyle(TileStyle.MEDIUM);
		tilesMap.put(FEELINGS_ID, t4);

		Tile t5 = new Tile(QUESTIONS_ID);
		t5.setDrawableResId(R.drawable.icon_me);
		t5.setLabel("Questions");
		t5.setRecScore(0);
		t5.setStyle(TileStyle.MEDIUM);
		tilesMap.put(FEELINGS_ID, t5);
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
