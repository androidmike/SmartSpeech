package com.changclamor.roomtosprout.smartspeech.fragments;

import java.util.ArrayList;
import java.util.List;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

import com.changclamor.roomtosprout.smartspeech.BusProvider;
import com.changclamor.roomtosprout.smartspeech.Constants;
import com.changclamor.roomtosprout.smartspeech.R;
import com.squareup.otto.Subscribe;

/**
 * Created by androidmike on 4/19/14.
 */

public class TileWorkplaceFragment extends TrackingFragment {

	public static final String tag = TileWorkplaceFragment.class
			.getCanonicalName();

	public static TileWorkplaceFragment newInstance(List<String> tags) {
		TileWorkplaceFragment fragment = new TileWorkplaceFragment();
		Bundle args = new Bundle();
		args.putStringArrayList(Constants.EXTRA_TAGS, (ArrayList<String>) tags);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		TileGridFragment tilesFragment = TileGridFragment
				.newInstance(getArguments().getStringArrayList(
						Constants.EXTRA_TAGS));
		SentenceFragment sentenceFragment = new SentenceFragment();
		View view = inflater.inflate(R.layout.workplace_layout, null);

		FragmentTransaction ft = getChildFragmentManager().beginTransaction();
		ft = getChildFragmentManager().beginTransaction();
		ft.replace(R.id.main_container, tilesFragment).commit();
		ft = getChildFragmentManager().beginTransaction();
		ft.replace(R.id.bottom_container, sentenceFragment,
				SentenceFragment.tag).commit();
		return view;
	}
}
