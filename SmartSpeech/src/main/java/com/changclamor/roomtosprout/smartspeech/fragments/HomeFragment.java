package com.changclamor.roomtosprout.smartspeech.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.changclamor.roomtosprout.smartspeech.R;
import com.changclamor.roomtosprout.smartspeech.controller.TilesController;

/**
 * Created by androidmike on 4/19/14.
 */
public class HomeFragment extends TrackingFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.home_layout, container, false);

		TileFragment meTile = TileFragment.newInstance(TilesController.ME_ID);
		TileFragment youTile = TileFragment.newInstance(TilesController.YOU_ID);
		TileFragment questionTile = TileFragment
				.newInstance(TilesController.QUESTIONS_ID);
		TileFragment thirdPersonTile = TileFragment
				.newInstance(TilesController.THIRD_PERSON_ID);
		TileFragment feelingsTile = TileFragment
				.newInstance(TilesController.FEELINGS_ID);
		android.support.v4.app.FragmentTransaction ft = getChildFragmentManager()
				.beginTransaction();
		ft.add(R.id.large_tile_1, youTile).commit();
		ft = getChildFragmentManager().beginTransaction();
		ft.add(R.id.large_tile_2, meTile).commit();
		ft = getChildFragmentManager().beginTransaction();
		ft.add(R.id.small_tile_1, questionTile).commit();
		ft = getChildFragmentManager().beginTransaction();
		ft.add(R.id.small_tile_2, thirdPersonTile).commit();
		ft = getChildFragmentManager().beginTransaction();
		ft.add(R.id.small_tile_3, feelingsTile).commit();
		return view;
	}
}
