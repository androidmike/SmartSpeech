package com.changclamor.roomtosprout.smartspeech.model;

import java.util.HashMap;

import com.changclamor.roomtosprout.smartspeech.BusProvider;

/**
 * Profile for user include customizations and user info
 * 
 * @author androidmike
 */
public class Profile {

	private HashMap<String, Integer> timesTilesClickedMap = null;
	private HashMap<String, Integer> timesTilesShownMap = null;

	private UserInfo info = new UserInfo();

	public UserInfo getInfo() {
		return info;
	}

	public Profile() {
		super();
		timesTilesClickedMap = new HashMap<String, Integer>();
		timesTilesShownMap = new HashMap<String, Integer>();

		BusProvider.getInstance().register(this);
	}

	public void delete() {
		BusProvider.getInstance().unregister(this);
	}

	public void setInfo(UserInfo info) {
		this.info = info;
	}

	public int getTileTimesUsed(int tileId) {
		Integer timesUsed = timesTilesClickedMap.get(tileId);
		if (timesUsed == null) {
			return 0;
		}
		return timesUsed;
	}

	public void onTileClicked(String id) {
		// Initialize
		if (timesTilesClickedMap.get(id) == null) {
			timesTilesClickedMap.put(id, 0);
		}
		// Increment
		timesTilesClickedMap.put(id, timesTilesClickedMap.get(id) + 1);
	}

	public void onTileShown(String id) {
		// Initialize
		if (timesTilesShownMap.get(id) == null) {
			timesTilesShownMap.put(id, 0);
		}
		// Increment
		timesTilesShownMap.put(id, timesTilesShownMap.get(id) + 1);
	}
}
