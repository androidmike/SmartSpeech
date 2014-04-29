package com.changclamor.roomtosprout.smartspeech.fragments;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.GridView;

import com.changclamor.roomtosprout.smartspeech.BusProvider;
import com.changclamor.roomtosprout.smartspeech.Constants;
import com.changclamor.roomtosprout.smartspeech.R;
import com.changclamor.roomtosprout.smartspeech.SmartSpeechApp;
import com.changclamor.roomtosprout.smartspeech.controller.TilesController;
import com.changclamor.roomtosprout.smartspeech.model.Tile;
import com.changclamor.roomtosprout.smartspeech.ui.TileGridAdapter;

/**
 * Created by androidmike on 4/19/14.
 */

public class TileGridFragment extends TrackingFragment implements
		OnScrollListener {
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
		gridView.setAdapter(new TileGridAdapter(getActivity(), tilesList));

		gridView.setOnScrollListener(this);

		return view;
	}

	@Override
	public void onScrollStateChanged(final AbsListView view, int scrollState) {

		view.setOnTouchListener(new OnTouchListener() {
			private float mInitialY;

			@Override
			public boolean onTouch(View v, MotionEvent event) {

				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					mInitialY = event.getY();
					return true;
				case MotionEvent.ACTION_MOVE:
					if (view.getLastVisiblePosition() == view.getAdapter()
							.getCount() - 1
							&& view.getChildAt(view.getChildCount() - 1)
									.getBottom() <= view.getHeight()) {
						// It is scrolled all the way down here
						return false;
					}
					if (view.getFirstVisiblePosition() == 0
							&& view.getChildAt(0).getTop() >= 0) {
						// It is scrolled all the way up here
						return false;
					}
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
