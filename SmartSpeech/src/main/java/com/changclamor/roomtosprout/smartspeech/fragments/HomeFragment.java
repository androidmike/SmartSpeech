package com.changclamor.roomtosprout.smartspeech.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.changclamor.roomtosprout.smartspeech.R;
import com.changclamor.roomtosprout.smartspeech.controller.TilesController;
import com.changclamor.roomtosprout.smartspeech.model.Tile;

/**
 * Created by androidmike on 4/19/14.
 */
public class HomeFragment extends TrackingFragment {

	private ViewGroup buttonsContainer;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.home_layout, container, false);
		buttonsContainer = (ViewGroup) view
				.findViewById(R.id.home_buttons_container);

		// Load home tiles
		for (String id : TilesController.getInstance().getHomeTilesId()) {
			Tile tile = TilesController.getInstance().getTile(id);
		}
		return view;
	}
}
