package com.changclamor.roomtosprout.smartspeech.fragments;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.changclamor.roomtosprout.smartspeech.BusProvider;
import com.changclamor.roomtosprout.smartspeech.Constants;
import com.changclamor.roomtosprout.smartspeech.R;
import com.changclamor.roomtosprout.smartspeech.SmartSpeechApp;
import com.changclamor.roomtosprout.smartspeech.controller.TilesController;
import com.changclamor.roomtosprout.smartspeech.model.Tile;
import com.changclamor.roomtosprout.smartspeech.model.TilesEvents.TileImpressionEvent;
import com.changclamor.roomtosprout.smartspeech.style.TileStyle;
import com.changclamor.roomtosprout.smartspeech.util.UIUtil;

/**
 * Created by androidmike on 4/19/14.
 */
public class TileFragment extends Fragment {

	private static final boolean ONLY_RECORD_IF_NEWLY_SHOWN = true;
	private boolean isShown = false;

	private ImageView tileImage;
	private TextView tileText;
	private View tileButton;
	private String tileId = null;
	private View mainView = null;

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
			BusProvider.getInstance().post(new TileImpressionEvent(tileId));
			isShown = ONLY_RECORD_IF_NEWLY_SHOWN;
			// TODO log
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.tile_view, container, false);
		tileImage = (ImageView) view.findViewById(R.id.tile_image);
		tileText = (TextView) view.findViewById(R.id.tile_text);
		tileButton = (View) view.findViewById(R.id.tile_button);

		Typeface face = Typeface.createFromAsset(SmartSpeechApp.getContext()
				.getAssets(), getResources().getString(R.string.thin));
		tileText.setTypeface(face);
		tileText.setTextSize(100f);
		tileText.setTextColor(Color.WHITE);
		if (tileId == null) {
			tileId = getArguments().getString(Constants.EXTRA_TILE_ID);
		}
		Tile tile = TilesController.getInstance().getTile(tileId);

		applyStyle(tile.getStyle());
		UIUtil.setBackground(tileButton,
				getBackgroundDrawawable(tile.getColor()));
		tileImage.setImageResource(tile.getDrawableResId());
		tileText.setText(tile.getLabel());
		mainView = view;
		return view;
	}

	private void applyStyle(TileStyle style) {
		switch (style) {
		case SMALL:
			break;
		case LARGE:
			break;
		case MEDIUM:
			mainView.getLayoutParams().width = 100;
			mainView.getLayoutParams().height = 100;
			break;
		case XLARGE:
			break;
		}

	}

	private Drawable getBackgroundDrawawable(int color) {
		return getBackgroundDrawawable(color, color);
	}

	private Drawable getBackgroundDrawawable(int bottomColor, int topColor) {
		GradientDrawable gradient = new GradientDrawable(
				Orientation.BOTTOM_TOP, new int[] { bottomColor, topColor });
		gradient.setShape(GradientDrawable.RECTANGLE);
		gradient.setCornerRadius(10.f);
		return gradient;
	}
}
