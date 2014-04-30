package com.changclamor.roomtosprout.smartspeech.ui;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.changclamor.roomtosprout.smartspeech.BusProvider;
import com.changclamor.roomtosprout.smartspeech.R;
import com.changclamor.roomtosprout.smartspeech.SmartSpeechApp;
import com.changclamor.roomtosprout.smartspeech.controller.TilesController;
import com.changclamor.roomtosprout.smartspeech.fragments.WordDeleteEvent;
import com.changclamor.roomtosprout.smartspeech.util.UIUtil;

public class BreadcrumbView extends LinearLayout {

	private String id;
	private TextView textView;
	private Button delButton;

	public BreadcrumbView(Context context) {
		super(context);
		init();
	}

	public BreadcrumbView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public void init() {
		setOrientation(LinearLayout.HORIZONTAL);
		LayoutInflater.from(SmartSpeechApp.getContext()).inflate(
				R.layout.word_view, this, true);

		textView = (TextView) findViewById(R.id.text);

		UIUtil.setBackground(textView,
				UIUtil.getBackgroundDrawable(Color.parseColor("#CCCCCC")));
		delButton = (Button) findViewById(R.id.del_button);
		delButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (id == null) {
					return;
				}
				BusProvider.getInstance().post(new WordDeleteEvent(id));
			}
		});
	}

	public void setTileId(String id) {
		this.id = id;
		textView.setText(TilesController.getInstance().getTile(id).getLabel());
	}
}
