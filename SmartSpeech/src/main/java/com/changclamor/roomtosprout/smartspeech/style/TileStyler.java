package com.changclamor.roomtosprout.smartspeech.style;

import java.util.HashMap;

import android.view.View;

import com.changclamor.roomtosprout.smartspeech.R;

public class TileStyler {
	private HashMap<String, TileStyle> stylesMap = new HashMap<String, TileStyle>();

	public void loadStyles() {
		// TileStyle style = new TileStyle(font, fontSize);
		// stylesMap.put("key", style);
	}

	// View must be from getView() on a TileFragment
	public static void applyStyle(View view, TileStyle style, int color) {
		View text = view.findViewById(R.id.tile_text);
		View button = view.findViewById(R.id.tile_button);

	}
}
