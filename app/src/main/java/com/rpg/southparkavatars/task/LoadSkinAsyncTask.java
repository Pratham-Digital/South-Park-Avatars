package com.rpg.southparkavatars.task;

import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.rpg.southparkavatars.character.Skin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoadSkinAsyncTask extends AsyncTask<Void, Void, List<Skin>> {
    private AssetManager assetManager;
    private String path;
    private AsyncTaskListener callback;

    public LoadSkinAsyncTask(String path, AsyncTaskListener callback, AssetManager assetManager) {
        this.assetManager = assetManager;
        this.path = path;
        this.callback = callback;
    }

    @Override
    protected List<Skin> doInBackground(Void... params) {
        List<Skin> skinColors = new ArrayList<>();

        for (String item : getItemNameList()) {
            String fileName = item.substring(0, item.indexOf('.'));
            String filePath = path + File.separator + item;

            Skin.Color color = Skin.Color.valueOf(fileName.toUpperCase());
            Skin skin = new Skin(color);

            skinColors.add(skin);
        }

        return skinColors;
    }

    @NonNull
    private List<String> getItemNameList() {
        List<String> itemNameList = new ArrayList<>();
        try {
            itemNameList = Arrays.asList(assetManager.list(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return itemNameList;
    }

    @Override
    protected void onPostExecute(List<Skin> skinColors) {
        super.onPostExecute(skinColors);
        callback.onSkinAsyncTaskFinished(skinColors);
    }
}
