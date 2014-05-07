package com.changclamor.roomtosprout.smartspeech.model;

import java.util.List;

import android.graphics.Color;

public class Tile {
    private int drawableResId;

    private String label;

    private int recScore;

    private String style;

    private String id;

    private Forms forms;

    private String color;

    private boolean isAudible; // Can be inserted into the text or be said on
                               // its own, as opposed to a category

    private List<String> tags;

    private List<String> predictedTags;

    private boolean isLeaf; // Likely the end of the sentence

    public Tile(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getDrawableResId() {
        return drawableResId;
    }

    public void setDrawableResId(int drawableResId) {
        this.drawableResId = drawableResId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void onShown() {
        // TODO Auto-generated method stub

    }

    public int getColor() {
        return Color.parseColor(color);
    }

    public List<String> getPredictedTags() {
        return predictedTags;
    }

    public List<String> getTags() {
        return tags;
    }

    public WordType getType() {
        // TODO Auto-generated method stub
        return null;
    }

    public Forms getForms() {
        return forms;
    }

    public void setForms(Forms forms) {
        this.forms = forms;
    }

}
