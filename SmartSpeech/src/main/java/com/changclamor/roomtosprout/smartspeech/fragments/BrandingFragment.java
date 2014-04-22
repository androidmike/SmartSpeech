package com.changclamor.roomtosprout.smartspeech.fragments;

import java.util.Timer;
import java.util.TimerTask;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.OvershootInterpolator;
import android.view.animation.ScaleAnimation;

import com.changclamor.roomtosprout.smartspeech.R;

/**
 * Created by androidmike on 4/19/14.
 */
public class BrandingFragment extends TrackingFragment {

	public static int DURATION = 5000;

	private View logo = null;

	public interface BrandingFragmentListener {
		public void onBrandingSequenceComplete();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.launch_rts, container, false);
		logo = view.findViewById(R.id.logo);
		return view;
	}

	@SuppressLint("NewApi")
	public void onResume() {
		super.onResume();

		// Animation
		AnimationSet set = new AnimationSet(true);
		ScaleAnimation scale = new ScaleAnimation(0, 1, 0, 1,
				Animation.RELATIVE_TO_SELF, .5f, Animation.RELATIVE_TO_SELF,
				.5f);
		set.setInterpolator(new OvershootInterpolator());
		set.addAnimation(scale);
		set.setDuration(1000);
		logo.startAnimation(set);

		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				{
					((BrandingFragmentListener) getActivity())
							.onBrandingSequenceComplete();
				}
			}
		}, DURATION);
	}
}
