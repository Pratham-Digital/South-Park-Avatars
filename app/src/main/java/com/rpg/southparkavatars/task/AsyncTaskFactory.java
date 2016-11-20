package com.rpg.southparkavatars.task;

import android.content.res.AssetManager;

import com.rpg.southparkavatars.character.clothing.Clothing;
import com.rpg.southparkavatars.character.head.HeadFeature;
import com.rpg.southparkavatars.character.clothing.AbstractClothing;
import com.rpg.southparkavatars.character.head.AbstractHeadFeature;

import java.io.File;

public class AsyncTaskFactory {
    private AsyncTaskListener callback;
    private AssetManager assetManager;
    private File internalStorage;

    public AsyncTaskFactory(AsyncTaskListener callback, AssetManager assetManager, File internalStorage) {
        this.callback = callback;
        this.assetManager = assetManager;
        this.internalStorage = internalStorage;
    }

    public <T extends AbstractClothing> LoadClothingAsyncTask<T> createClothingLoadingTask(Class<T> clothingClass) {
        String path = Clothing.valueOf(clothingClass.getSimpleName().toUpperCase()).getPath();
        File concreteClothingPath = new File(internalStorage.getPath() + File.separator + path);
        return new LoadClothingAsyncTask<>(path, callback, assetManager, concreteClothingPath, clothingClass);
    }

    public <T extends AbstractHeadFeature> LoadHeadFeaturesAsyncTask<T> createHeadFeaturesLoadingTask(Class<T> headFeatureClass) {
        String path = HeadFeature.valueOf(headFeatureClass.getSimpleName().toUpperCase()).getPath();
        return new LoadHeadFeaturesAsyncTask<>(path, callback, assetManager, headFeatureClass);
    }

    public LoadSkinAsyncTask createSkinLoadingTask() {
        String path = "skin_color";
        return new LoadSkinAsyncTask(path, callback, assetManager);
    }

    public LoadBackgroundsAsyncTask createLoadBackgroundsTask() {
        return new LoadBackgroundsAsyncTask(assetManager, callback);
    }
}
