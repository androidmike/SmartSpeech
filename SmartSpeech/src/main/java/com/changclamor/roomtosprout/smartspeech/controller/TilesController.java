package com.changclamor.roomtosprout.smartspeech.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import android.content.res.Resources.NotFoundException;

import com.changclamor.roomtosprout.smartspeech.R;
import com.changclamor.roomtosprout.smartspeech.SmartSpeechApp;
import com.changclamor.roomtosprout.smartspeech.model.Tile;
import com.changclamor.roomtosprout.smartspeech.transport.GetTilesResponse;
import com.changclamor.roomtosprout.smartspeech.util.TransportUtil;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class TilesController {
    public static String RES_URL = "http://roomtosprout.com/smartspeech/res/kids.zip";

    public static final String YOU_ID = "you";

    public static final String ME_ID = "me";

    public static final String THIRD_PERSON_ID = "third_person";

    public static final String FEELINGS_ID = "feelings";

    public static final String QUESTIONS_ID = "questions";

    private static TilesController instance = null;

    private String[] homeTilesId = { ME_ID, YOU_ID, THIRD_PERSON_ID, FEELINGS_ID };

    private HashMap<String, Tile> tilesMap = null;

    private HashMap<String, Set<String>> tagMap = new HashMap<String, Set<String>>();

    public static void init() {
        instance = new TilesController();
        instance.loadTilesMap();
    }

    private void loadTilesMap() {
        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
        String json = null;
        try {
            json = TransportUtil.convertStreamToString(SmartSpeechApp.getContext().getResources()
                    .openRawResource(R.raw.tiles));
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        GetTilesResponse r = gson.fromJson(json, GetTilesResponse.class);

        tilesMap = new HashMap<String, Tile>();
        for (Tile t : r.tiles) {
            tilesMap.put(t.getId(), t);

            for (String tag : t.getTags()) {
                Set<String> existingList = tagMap.get(tag);
                if (existingList == null) {
                    Set<String> list = new HashSet<String>();
                    list.add(t.getId());
                    tagMap.put(tag, list);
                } else {
                    existingList.add(t.getId());
                }
            }
        }

        // loadResources();
    }

    private void loadResources() {
        final DownloadTask downloadTask = new DownloadTask(SmartSpeechApp.getContext());
        downloadTask.execute(RES_URL);
    }

    public String[] getHomeTilesId() {
        return homeTilesId;
    }

    public static TilesController getInstance() {
        return instance;
    }

    public Tile getTile(String id) {
        return tilesMap.get(id);
    }

    public Set<String> getTilesIdsByTags(ArrayList<String> tagsList, boolean addEndTiles) {
        Set<String> list = new HashSet<String>();
        if (tagsList == null) {
            return list;
        }
        for (String tag : tagsList) {
            Set<String> set = tagMap.get(tag);
            if (set == null) {
                continue;
            }
            list.addAll(set);
        }
        list.add("question");
        list.add("speak");
        return list;
    }

}
