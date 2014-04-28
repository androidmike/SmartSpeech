package com.changclamor.roomtosprout.smartspeech.fragments;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.changclamor.roomtosprout.smartspeech.BusProvider;
import com.changclamor.roomtosprout.smartspeech.Constants;
import com.changclamor.roomtosprout.smartspeech.R;
import com.changclamor.roomtosprout.smartspeech.SmartSpeechApp;
import com.changclamor.roomtosprout.smartspeech.controller.TilesController;
import com.changclamor.roomtosprout.smartspeech.fragments.TileFragment.TileListener;
import com.changclamor.roomtosprout.smartspeech.model.Tile;
import com.changclamor.roomtosprout.smartspeech.ui.TileGridAdapter;

/**
 * Created by androidmike on 4/19/14.
 */

public class TileGridFragment extends TrackingFragment implements
		OnItemClickListener, OnScrollListener {
	private GridView gridView = null;

	public static TileGridFragment newInstance(List<String> tags) {
		TileGridFragment fragment = new TileGridFragment();
		Bundle args = new Bundle();
		args.putStringArrayList(Constants.EXTRA_TAGS, (ArrayList<String>) tags);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.tile_grid_layout, container,
				false);
		gridView = (GridView) view.findViewById(R.id.gridview);
		ArrayList<String> tagsList = getArguments().getStringArrayList(
				Constants.EXTRA_TAGS);
		List<Tile> tilesList = TilesController.getInstance().getTilesByTags(
				tagsList);
		gridView.setAdapter(new TileGridAdapter(SmartSpeechApp.getContext(),
				tilesList));

		gridView.setOnItemClickListener(this);
		gridView.setOnScrollListener(this);

		return view;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		if (getActivity() == null) {
			return;
		}
		Tile tileClicked = (Tile) gridView.getAdapter().getItem(position);
		((TileListener) getActivity()).onTileClicked(tileClicked.getId());
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {

		view.setOnTouchListener(new OnTouchListener() {
			private float mInitialX;
			private float mInitialY;

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					mInitialX = event.getX();
					mInitialY = event.getY();
					return true;
				case MotionEvent.ACTION_MOVE:
					final float x = event.getX();
					final float y = event.getY();
					final float yDiff = y - mInitialY;
					if (yDiff > 0.0) {
						BusProvider.getInstance().post(
								new GridScrollEvent(null, yDiff));
						break;

					} else if (yDiff < 0.0) {
						BusProvider.getInstance().post(
								new GridScrollEvent(null, yDiff));
						break;
					}
					break;
				}
				return false;
			}
		});
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
	}

}
