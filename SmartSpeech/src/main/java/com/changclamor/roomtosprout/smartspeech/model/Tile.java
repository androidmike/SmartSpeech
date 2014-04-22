package com.changclamor.roomtosprout.smartspeech.model;

import com.changclamor.roomtosprout.smartspeech.style.TileStyle;

import android.graphics.Color;

public class Tile {
	private int drawableResId;
	private String label;
	private int recommendationScore;
	private TileStyle style;
	private String id;
	private int color = com.changclamor.roomtosprout.smartspeech.R.color.default_tile;

	public Tile(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getDrawableResId() {
		return drawableResId;
	}

	public void setDrawableResId(int drawableResId) {
		this.drawableResId = drawableResId;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getRecScore() {
		return recommendationScore;
	}

	public void setRecScore(int recommendationScore) {
		this.recommendationScore = recommendationScore;
	}

	public void onShown() {
		// TODO Auto-generated method stub

	}

	public void setColor(String hex) {
		this.color = Color.parseColor(hex);
	}

	public int getColor() {
		return color;
	}

	public void setStyle(TileStyle style) {
		this.style = style;
	}

}
