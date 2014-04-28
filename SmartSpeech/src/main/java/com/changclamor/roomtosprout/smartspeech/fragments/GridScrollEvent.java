package com.changclamor.roomtosprout.smartspeech.fragments;

public class GridScrollEvent {

	public enum Direction {
		UP, DOWN;
	}

	public float diff;
	public Direction direction;

	public GridScrollEvent(Direction direction, float diff) {
		this.direction = direction;
		this.diff = diff;
	}
}
