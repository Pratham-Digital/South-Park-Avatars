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
import java.util.Random;

public class LoadClothingAsyncTask<T extends AbstractClothing> extends AsyncTask<Void, Void, List<AbstractClothing>> {
    private static final int MAX = 10;
    private static final int MIN = 1;

    private AssetManager assetManager;
    private String path;
    private Class<T> clothingClass;
    private AsyncTaskListener callback;
    private File internal;
    private Random rand;

    public LoadClothingAsyncTask(String path, AsyncTaskListener callback, AssetManager assetManager, File internal,
                                 Class<T> clothingClass) {
        this.assetManager = assetManager;
        this.path = path;
        this.clothingClass = clothingClass;
        this.callback = callback;
        this.internal = internal;

        rand = new Random();
    }

    @Override
    protected List<AbstractClothing> doInBackground(Void... params) {
        List<AbstractClothing> clothes = new ArrayList<>();

        for (String item : getItemNameList()) {
            String filePath = path + '/' + item;
            int coolness = calculateCoolness(item);
            T object = createObject(filePath, coolness);
            clothes.add(object);
        }

        return clothes;
    }

    private int calculateCoolness(String item) {
        int coolness;
        if (item.equals("shirt_1.png") || item.equals("hat_6.png") || item.equals("accessories_10.png") || item.equals("necklace_4.png"))
            coolness = MAX + rand.nextInt(5 - 1 + 1) + 1;
        else if (path.equals("clothing/necklace"))
            coolness = rand.nextInt(7 - 1 + 1) + 1;
        else if (path.equals("clothing/glasses"))
            coolness = rand.nextInt(5 - 1 + 1) + 1;
        else
            coolness = rand.nextInt(MAX - MIN + 1) + MIN;
        return coolness;
    }

    private T createObject(String path, int coolness) {
        try {
            Constructor<T> ctor = clothingClass.getConstructor(int.class, String.class);
            return ctor.newInstance(coolness, path);
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
