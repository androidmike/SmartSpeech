package com.changclamor.roomtosprout.smartspeech.model;

import java.util.ArrayList;
import java.util.List;

import com.changclamor.roomtosprout.smartspeech.BusProvider;
import com.changclamor.roomtosprout.smartspeech.controller.TilesController;
import com.changclamor.roomtosprout.smartspeech.fragments.SentenceChangedEvent;

public class SentenceState {
    private static SentenceState state = new SentenceState();

    private List<String> tileIdsInOrder = new ArrayList<String>();

    public static SentenceState getSentence() {
        return state;
    }

    public int getSize() {
        return tileIdsInOrder.size();
    }

    public boolean isEmpty() {
        return tileIdsInOrder.isEmpty();
    }

    /**
     * @return Get the entire sentence in string format. All words separated by
     *         space.
     */
    public String toString() {
        String sentence = "";
        for (String id : tileIdsInOrder) {
            sentence = String.format("%s %s", sentence, TilesController.getInstance().getTile(id).getLabel());
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

        polishSentence();
        BusProvider.getInstance().post(new SentenceChangedEvent());
    }

    private int getPronounPosition() {
        // Find the first subject in sentence
        for (int i = 0; i < tileIdsInOrder.size(); i++) {
            Tile tile = TilesController.getInstance().getTile(tileIdsInOrder.get(i));
            if (tile.getTags().contains("pronoun")) {
                return i;
            }
        }
        return -1;
    }

    private void polishSentence() {
        int pronounPos = getPronounPosition();
        if (pronounPos != -1) {
            Tile tile = TilesController.getInstance().getTile(tileIdsInOrder.get(pronounPos));
            if (pronounPos == 0) {
                if (tile.getTags().contains("object")) {
                    String id = tile.getForms().getSubject();
                    replaceTileId(0, id);
                }
            } else if (tile.getTags().contains("subject")) {
                tile.getForms().getSubject();
            }
        }
    }

    private void replaceTileId(int i, String id) {
        tileIdsInOrder.set(i, id);
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
