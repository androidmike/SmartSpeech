package com.changclamor.roomtosprout.smartspeech.fragments;

import java.util.Random;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.OvershootInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.changclamor.roomtosprout.smartspeech.BusProvider;
import com.changclamor.roomtosprout.smartspeech.Constants;
import com.changclamor.roomtosprout.smartspeech.R;
import com.changclamor.roomtosprout.smartspeech.SmartSpeechApp;
import com.changclamor.roomtosprout.smartspeech.controller.TilesController;
import com.changclamor.roomtosprout.smartspeech.data.StorageUtils;
import com.changclamor.roomtosprout.smartspeech.model.Tile;
import com.changclamor.roomtosprout.smartspeech.model.TilesEvents.TileImpressionEvent;
import com.changclamor.roomtosprout.smartspeech.util.UIUtil;

/**
 * Created by androidmike on 4/19/14.
 */

public class TileFragment extends Fragment implements OnInitListener,
		OnClickListener {

	private static final String KIDS = "kids";
	private static final boolean ONLY_RECORD_IF_NEWLY_SHOWN = true;
	private boolean isShown = false;

	private ImageView tileImage;
	private TextView tileText;
	private View tileButton;
	private String tileId = null;
	private View mainView = null;
	private TextToSpeech tts;
	private Tile tile = null;
	private Random random = new Random();

	public TileFragment() {
		super();
		tts = new TextToSpeech(SmartSpeechApp.getContext(), this);
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

		if (tileId == null && getArguments() != null) {
			tileId = getArguments().getString(Constants.EXTRA_TILE_ID);
		}

		tile = TilesController.getInstance().getTile(tileId);
		/*
		 * if (tile.getStyle() == TileStyle.LARGE) { mainView =
		 * inflater.inflate(R.layout.tile_view_large, container, false); } else
		 * {
		 * 
		 * mainView = inflater.inflate(R.layout.tile_view_medium, container,
		 * false); }
		 */

		mainView = inflater
				.inflate(R.layout.tile_view_medium, container, false);
		tileImage = (ImageView) mainView.findViewById(R.id.tile_image);
		tileText = (TextView) mainView.findViewById(R.id.tile_text);
		tileButton = (View) mainView.findViewById(R.id.tile_button);
		tileButton.setOnClickListener(this);
		Typeface face = Typeface.createFromAsset(SmartSpeechApp.getContext()
				.getAssets(), getResources().getString(R.string.thin));
		tileText.setTypeface(face);

		tileText.setTextColor(Color.WHITE);

		UIUtil.setBackground(tileButton, getBackgroundDrawable(tile.getColor()));

		tileImage.setImageBitmap(StorageUtils.getBitmapFromAsset(KIDS, "full_"
				+ tileId + ".png"));

		tileText.setText(tile.getLabel());

		return mainView;
	}

	// No gradient background
	private Drawable getBackgroundDrawable(int color) {
		return getBackgroundDrawable(color, color);
	}

	private Drawable getBackgroundDrawable(int bottomColor, int topColor) {
		GradientDrawable pressed = new GradientDrawable(Orientation.BOTTOM_TOP,
				new int[] { bottomColor, topColor });
		pressed.setShape(GradientDrawable.RECTANGLE);
		pressed.setCornerRadius(10.f);
		pressed.setStroke(0, Color.parseColor("#00000000"));

		GradientDrawable normal = new GradientDrawable(Orientation.BOTTOM_TOP,
				new int[] { bottomColor, topColor });
		// TODO: lighten up bottom color and top color
		normal.setShape(GradientDrawable.RECTANGLE);
		normal.setCornerRadius(10.f);
		normal.setStroke(10, Color.parseColor("#00000000"));

		StateListDrawable states = new StateListDrawable();
		states.addState(new int[] { android.R.attr.state_pressed }, pressed);
		states.addState(new int[] { android.R.attr.state_focused }, pressed);
		states.addState(new int[] {}, normal);
		return states;

	}

	@Override
	public void onInit(int status) {
	}

	@Override
	public void onClick(View v) {
		String spokenWords = tile.getLabel().replace("/", " ");
		tts.speak(spokenWords, TextToSpeech.QUEUE_FLUSH, null);
	}

	@Override
	public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
		// Pop, pop, pop!
		AnimationSet set = new AnimationSet(true);
		set.addAnimation(new ScaleAnimation(0, 1, 0, 1,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				.5f));
		set.setStartOffset(random.nextInt(getResources().getInteger(
				R.integer.pop_start_delay_max)));
		set.setInterpolator(new OvershootInterpolator());
		set.setDuration(getResources().getInteger(R.integer.pop_time));
		return set;
	}
}
