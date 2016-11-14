package com.rpg.southparkavatars.task;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.rpg.southparkavatars.character.head.HeadFeature;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoadHeadFeaturesAsyncTask<T extends HeadFeature> extends AsyncTask<Void, Void, List<HeadFeature>> {
    private AssetManager assetManager;
    private String path;
    private Class<T> headFeatureClass;
    private AsyncTaskListener callback;

    public LoadHeadFeaturesAsyncTask(String path, AsyncTaskListener callback, AssetManager assetManager,
                                     Class<T> headFeatureClass) {
        this.assetManager = assetManager;
        this.path = path;
        this.headFeatureClass = headFeatureClass;
        this.callback = callback;
    }

    @Override
    protected List<HeadFeature> doInBackground(Void... params) {
        List<HeadFeature> clothes = new ArrayList<>();

        for (String item : getItemNameList()) {
            String filePath = path + File.separator + item;

            try (InputStream inputStream = assetManager.open(filePath)) {
                T object = createObject(inputStream);
                clothes.add(object);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return clothes;
    }

    private T createObject(InputStream inputStream) {
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
        try {
            Constructor<T> ctor = headFeatureClass.getConstructor(Bitmap.class);
            return ctor.newInstance(bitmap);
        } catch (NoSuchMethodException | IllegalAccessException
                | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
            return null;
        }
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
    protected void onPostExecute(List<HeadFeature> headFeatures) {
        super.onPostExecute(headFeatures);
        callback.onHeadFeaturesAsyncTaskFinished(headFeatures);
    }
}
