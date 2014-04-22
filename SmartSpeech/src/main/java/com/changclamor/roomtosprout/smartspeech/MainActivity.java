package com.changclamor.roomtosprout.smartspeech;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.changclamor.roomtosprout.smartspeech.fragments.BrandingFragment;
import com.changclamor.roomtosprout.smartspeech.fragments.HomeFragment;
import com.crashlytics.android.Crashlytics;

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
		android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager()
				.beginTransaction();
		ft.replace(R.id.main_viewgroup, brandingFragment).commit();
	}

	@Override
	public void onBrandingSequenceComplete() {
		showFragment(homeFragment);
	}

	private void showFragment(Fragment fragment) {
		FragmentManager fm = getSupportFragmentManager();
		android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();

		ft.setCustomAnimations(android.R.anim.slide_in_left,
				android.R.anim.slide_out_right);
		ft.replace(R.id.main_viewgroup, fragment).commit();
	}
}