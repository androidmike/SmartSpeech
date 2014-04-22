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

	private ViewGroup buttonsContainer1, buttonsContainer2;
	private static final int NUM_PER_ROW = 2;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.home_layout, container, false);

		buttonsContainer1 = (ViewGroup) view
				.findViewById(R.id.home_buttons_container_1);
		buttonsContainer2 = (ViewGroup) view
				.findViewById(R.id.home_buttons_container_2);

		int col = 1;
		// Load home tiles
		for (String id : TilesController.getInstance().getHomeTilesId()) {
			int containerId;
			if (col == NUM_PER_ROW) {
				col = 0;
				containerId = R.id.home_buttons_container_2;
			} else {
				containerId = R.id.home_buttons_container_1;
			}
			TileFragment tileFragment = TileFragment.newInstance(id);

			android.support.v4.app.FragmentTransaction ft = getChildFragmentManager()
					.beginTransaction();
			ft.add(containerId, tileFragment).commit();
			col++;
		}

		return view;
	}
}
