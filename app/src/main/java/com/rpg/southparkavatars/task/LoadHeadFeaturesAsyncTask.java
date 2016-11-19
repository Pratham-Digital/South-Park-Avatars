package com.rpg.southparkavatars.task;

import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.rpg.southparkavatars.character.head.AbstractHeadFeature;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoadHeadFeaturesAsyncTask<T extends AbstractHeadFeature> extends AsyncTask<Void, Void, List<AbstractHeadFeature>> {
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
    protected List<AbstractHeadFeature> doInBackground(Void... params) {
        List<AbstractHeadFeature> clothes = new ArrayList<>();

        for (String item : getItemNameList()) {
            String filePath = path + File.separator + item;
            T object = createObject(filePath);
            clothes.add(object);
        }

        return clothes;
    }

    private T createObject(String path) {
        try {
            Constructor<T> ctor = headFeatureClass.getConstructor(String.class);
            return ctor.newInstance(path);
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException
                | InvocationTargetException e) {
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
    protected void onPostExecute(List<AbstractHeadFeature> headFeatures) {
        super.onPostExecute(headFeatures);
        callback.onHeadFeaturesAsyncTaskFinished(headFeatures);
    }
}
