package com.rpg.southparkavatars.task;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;

public class LoadImageAsyncTask extends AsyncTask<Void, Void, Bitmap> {
    private final String path;
    private final AssetManager assetManager;
    private AsyncTaskListener callback;

    public LoadImageAsyncTask(String path, AsyncTaskListener callback, AssetManager assetManager) {
        this.callback = callback;
        this.path = path;
        this.assetManager = assetManager;
    }

    @Override
    protected Bitmap doInBackground(Void... params) {
        try (InputStream inputStream = assetManager.open(path)) {
            return BitmapFactory.decodeStream(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
