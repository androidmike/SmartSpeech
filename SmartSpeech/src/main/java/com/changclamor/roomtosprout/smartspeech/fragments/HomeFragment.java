package com.changclamor.roomtosprout.smartspeech.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.changclamor.roomtosprout.smartspeech.R;

/**
 * Created by androidmike on 4/19/14.
 */
public class HomeFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.home_layout, container, false);
	}
}
