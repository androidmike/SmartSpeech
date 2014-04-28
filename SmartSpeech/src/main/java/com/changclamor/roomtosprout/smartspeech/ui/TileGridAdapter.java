package com.changclamor.roomtosprout.smartspeech.ui;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.changclamor.roomtosprout.smartspeech.Constants;
import com.changclamor.roomtosprout.smartspeech.R;
import com.changclamor.roomtosprout.smartspeech.SmartSpeechApp;
import com.changclamor.roomtosprout.smartspeech.controller.TilesController;
import com.changclamor.roomtosprout.smartspeech.data.StorageUtils;
import com.changclamor.roomtosprout.smartspeech.model.Tile;
import com.changclamor.roomtosprout.smartspeech.util.UIUtil;

public class TileGridAdapter extends BaseAdapter {
	private Context mContext;
	private List<Tile> tileList = new ArrayList<Tile>();

	public TileGridAdapter(Context c, List<Tile> tileList) {
		mContext = c;
		this.tileList = tileList;
	}

	public int getCount() {
		return tileList.size();
	}

	public Object getItem(int position) {
		return tileList.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		Tile tile = TilesController.getInstance().getTile(
				tileList.get(position).getId());
		LayoutInflater inflater = LayoutInflater.from(SmartSpeechApp
				.getContext());
		View mainView = inflater.inflate(R.layout.tile_view_medium, null);
		ImageView tileImage = (ImageView) mainView
				.findViewById(R.id.tile_image);
		TextView tileText = (TextView) mainView.findViewById(R.id.tile_text);
		View tileButton = (View) mainView.findViewById(R.id.tile_button);
		// tileButton.setOnClickListener(null);
		Typeface face = Typeface.createFromAsset(SmartSpeechApp.getContext()
				.getAssets(), mContext.getResources().getString(R.string.thin));
		tileText.setTypeface(face);

		tileText.setTextColor(Color.WHITE);

		UIUtil.setBackground(tileButton,
				UIUtil.getBackgroundDrawable(tile.getColor()));

		tileImage.setImageBitmap(StorageUtils.getBitmapFromAsset(
				Constants.KIDS, "full_" + tile.getId() + ".png"));

		tileText.setText(tile.getLabel());

		mainView.setLayoutParams(new GridView.LayoutParams(
				GridView.LayoutParams.MATCH_PARENT,
				GridView.LayoutParams.WRAP_CONTENT));
		return mainView;
	}
}
