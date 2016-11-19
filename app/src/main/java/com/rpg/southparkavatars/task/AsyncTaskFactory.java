package com.rpg.southparkavatars.task;

import android.content.res.AssetManager;

import com.rpg.southparkavatars.character.clothing.Clothing;
import com.rpg.southparkavatars.character.head.HeadFeatures;
import com.rpg.southparkavatars.character.clothing.AbstractClothing;
import com.rpg.southparkavatars.character.head.AbstractHeadFeature;

public class AsyncTaskFactory {
    private AsyncTaskListener callback;
    private AssetManager assetManager;

    public AsyncTaskFactory(AsyncTaskListener callback, AssetManager assetManager) {
        this.callback = callback;
        this.assetManager = assetManager;
    }

    public <T extends AbstractClothing> LoadClothesAsyncTask<T> createClothingLoadingTask(Class<T> clothingClass) {
        String path = Clothing.valueOf(clothingClass.getSimpleName().toUpperCase()).getPath();
        return new LoadClothesAsyncTask<>(path, callback, assetManager, clothingClass);
    }

    public <T extends AbstractHeadFeature> LoadHeadFeaturesAsyncTask<T> createHeadFeaturesLoadingTask(Class<T> headFeatureClass) {
        String path = HeadFeatures.valueOf(headFeatureClass.getSimpleName().toUpperCase()).getPath();
        return new LoadHeadFeaturesAsyncTask<>(path, callback, assetManager, headFeatureClass);
    }

    public LoadSkinAsyncTask createSkinLoadingTask() {
        String path = "skin_color";
        return new LoadSkinAsyncTask(path, callback, assetManager);
    }
}
