package com.rpg.southparkavatars.task;

import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.rpg.southparkavatars.character.clothing.AbstractClothing;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoadClothesAsyncTask<T extends AbstractClothing> extends AsyncTask<Void, Void, List<AbstractClothing>> {
    private AssetManager assetManager;
    private String path;
    private Class<T> clothingClass;
    private AsyncTaskListener callback;
    private File internal;

    public LoadClothesAsyncTask(String path, AsyncTaskListener callback, AssetManager assetManager, File internal,
                                Class<T> clothingClass) {
        this.assetManager = assetManager;
        this.path = path;
        this.clothingClass = clothingClass;
        this.callback = callback;
        this.internal = internal;
    }

    @Override
    protected List<AbstractClothing> doInBackground(Void... params) {
        List<AbstractClothing> clothes = new ArrayList<>();

        for (String item : getItemNameList()) {
            String filePath = path + '/' + item;
            T object = createObject(filePath);
            clothes.add(object);
        }

        return clothes;
    }

    private T createObject(String path) {
        try {
            Constructor<T> ctor = clothingClass.getConstructor(int.class, String.class);
            return ctor.newInstance(0, path);
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
            itemNameList = new ArrayList<>(Arrays.asList(assetManager.list(path)));

            String[] fileNames = internal.list();
            if (fileNames != null) {
                itemNameList.addAll(Arrays.asList(internal.list()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return itemNameList;
    }

    @Override
    protected void onPostExecute(List<AbstractClothing> clothes) {
        super.onPostExecute(clothes);
        callback.onClothesAsyncTaskFinished(clothes);
    }
}
