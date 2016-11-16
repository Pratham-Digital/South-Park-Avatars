package com.rpg.southparkavatars.task;

import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.rpg.southparkavatars.character.clothing.Clothing;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoadClothesAsyncTask<T extends Clothing> extends AsyncTask<Void, Void, List<Clothing>> {
    private AssetManager assetManager;
    private String path;
    private Class<T> clothingClass;
    private AsyncTaskListener callback;

    public LoadClothesAsyncTask(String path, AsyncTaskListener callback, AssetManager assetManager,
                                Class<T> clothingClass) {
        this.assetManager = assetManager;
        this.path = path;
        this.clothingClass = clothingClass;
        this.callback = callback;
    }

    @Override
    protected List<Clothing> doInBackground(Void... params) {
        List<Clothing> clothes = new ArrayList<>();

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
            itemNameList = Arrays.asList(assetManager.list(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return itemNameList;
    }

    @Override
    protected void onPostExecute(List<Clothing> clothes) {
        super.onPostExecute(clothes);
        callback.onClothesAsyncTaskFinished(clothes);
    }
}
