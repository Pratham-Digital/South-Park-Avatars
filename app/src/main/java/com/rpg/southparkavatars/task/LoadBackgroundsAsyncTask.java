package com.rpg.southparkavatars.task;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;

import com.rpg.southparkavatars.tool.BitmapLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoadBackgroundsAsyncTask extends AsyncTask<Void, Void, List<BitmapDrawable>> {
    private AsyncTaskListener callback;
    private AssetManager assetManager;

    public LoadBackgroundsAsyncTask(AssetManager assetManager, AsyncTaskListener callback) {
        this.callback = callback;
        this.assetManager = assetManager;
    }

    @Override
    protected List<BitmapDrawable> doInBackground(Void... params) {
        List<BitmapDrawable> bitmaps = new ArrayList<>();
        BitmapLoader loader = new BitmapLoader(assetManager, null);

        try {
            for (String fileName : assetManager.list("background")) {
                Bitmap bitmap = loader.loadFromBackgrounds(fileName);
                BitmapDrawable drawable = new BitmapDrawable(bitmap);
                bitmaps.add(drawable);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bitmaps;
    }

    @Override
    protected void onPostExecute(List<BitmapDrawable> bitmaps) {
        callback.onBackgroundsLoaded(bitmaps);
    }
}
