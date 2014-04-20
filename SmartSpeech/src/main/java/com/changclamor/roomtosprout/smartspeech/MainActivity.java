package com.changclamor.roomtosprout.smartspeech;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.changclamor.roomtosprout.smartspeech.fragments.BrandingFragment;
import com.changclamor.roomtosprout.smartspeech.fragments.HomeFragment;

public class MainActivity extends FragmentActivity implements
		BrandingFragment.BrandingFragmentListener {
	private BrandingFragment brandingFragment = new BrandingFragment();
	private HomeFragment homeFragment = new HomeFragment();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
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
		FragmentManager fm = getSupportFragmentManager();
		android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();

		ft.setCustomAnimations(android.R.anim.slide_in_left,
				android.R.anim.slide_out_right);
		ft.replace(R.id.main_viewgroup, homeFragment).commit();
	}
}