package com.changclamor.roomtosprout.smartspeech.model;

import java.util.ArrayList;
import java.util.List;

import com.changclamor.roomtosprout.smartspeech.BusProvider;
import com.changclamor.roomtosprout.smartspeech.controller.TilesController;
import com.changclamor.roomtosprout.smartspeech.fragments.GridScrollEvent;
import com.changclamor.roomtosprout.smartspeech.fragments.SentenceChangedEvent;
import com.changclamor.roomtosprout.smartspeech.fragments.TileClickedEvent;
import com.squareup.otto.Subscribe;

public class SentenceState {
	private static SentenceState state = new SentenceState();
	private List<String> tileIdsInOrder = new ArrayList<String>();

	public static SentenceState getSentence() {
		return state;
	}

	/**
	 * @return Get the entire sentence in string format. All words separated by
	 *         space.
	 */
	public String toString() {
		String sentence = "";
		for (String id : tileIdsInOrder) {
			sentence = String.format("%s %s", sentence, TilesController
					.getInstance().getTile(id).getLabel());
		}
		sentence = sentence.trim();
		return sentence;
	}

	public List<String> getIds() {
		return tileIdsInOrder;
	}

	/**
	 * There may be multiple items of the same ID, thus position should be used.
	 * 
	 * @param pos
	 */
	public void remove(int pos) {
		tileIdsInOrder.remove(pos);
		BusProvider.getInstance().post(new SentenceChangedEvent());
	}

	/**
	 * Called after a tile has been clicked to add to end of sentence Sentence
	 * can possibly reorder to see where this word would fit.
	 * 
	 * @param id
	 */
	public void add(String id) {
		tileIdsInOrder.add(id);
		BusProvider.getInstance().post(new SentenceChangedEvent());
	}

	/**
	 * If sentence has all signs of a complete sentence (e.g. we can directly
	 * announce it). Alternatively user can also click on speak if this returns
	 * false.
	 * 
	 * @return
	 */
	public boolean isComplete() {
		// Ready to speak
		return true; // TODO
	}

	public void clear() {
		tileIdsInOrder.clear();

		BusProvider.getInstance().post(new SentenceChangedEvent());
	}

	public void remove(String id) {
		tileIdsInOrder.remove(id);

		BusProvider.getInstance().post(new SentenceChangedEvent());
	}
}
