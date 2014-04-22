package com.changclamor.roomtosprout.smartspeech.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.changclamor.roomtosprout.smartspeech.BusProvider;
import com.changclamor.roomtosprout.smartspeech.Constants;
import com.changclamor.roomtosprout.smartspeech.R;
import com.changclamor.roomtosprout.smartspeech.model.Tile;
import com.changclamor.roomtosprout.smartspeech.model.TilesEvents.TileImpressionEvent;

/**
 * Created by androidmike on 4/19/14.
 */
public class TileFragment extends Fragment {

	private static final boolean ONLY_RECORD_IF_NEWLY_SHOWN = true;
	private boolean isShown = false;
	private Tile tile = null;

	public TileFragment() {
		super();
	}

	// Convenience factory method
	public static TileFragment newInstance(String tileId) {
		TileFragment fragment = new TileFragment();

		Bundle args = new Bundle();
		args.putString(Constants.EXTRA_TILE_ID, tileId);
		fragment.setArguments(args);

		return fragment;
	}

	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);
		// Only record if hasn't been shown before
		if (isVisibleToUser && !isShown) {
			BusProvider.getInstance().post(
					new TileImpressionEvent(tile.getId()));
			isShown = ONLY_RECORD_IF_NEWLY_SHOWN;
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.home_layout, container, false);
	}

}
