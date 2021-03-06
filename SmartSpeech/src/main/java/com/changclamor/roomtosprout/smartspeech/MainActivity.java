package com.changclamor.roomtosprout.smartspeech;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.changclamor.roomtosprout.smartspeech.fragments.BrandingFragment;
import com.changclamor.roomtosprout.smartspeech.fragments.HomeFragment;
import com.crashlytics.android.Crashlytics;

@SuppressLint("NewApi")
public class MainActivity extends FragmentActivity implements
		BrandingFragment.BrandingFragmentListener {
	private BrandingFragment brandingFragment = new BrandingFragment();
	private HomeFragment homeFragment = new HomeFragment();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Crashlytics.start(this);
		setContentView(R.layout.root_layout);
		showBrandingSequenceFragment();

	}

	private void showBrandingSequenceFragment() {
		FragmentTransaction ft = getFragmentManager()
				.beginTransaction();
		ft.replace(R.id.main_viewgroup, brandingFragment).commit();
	}

	@Override
	public void onBrandingSequenceComplete() {
		showFragment(homeFragment);
	}

	private void showFragment(Fragment fragment) {
		FragmentManager fm = getFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
/*
		ft.setCustomAnimations(android.R.anim.slide_in_left,
				android.R.anim.slide_out_right);*/
		ft.replace(R.id.main_viewgroup, fragment).commit();
	}
}