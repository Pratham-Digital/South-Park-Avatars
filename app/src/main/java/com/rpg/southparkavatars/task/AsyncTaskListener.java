package com.rpg.southparkavatars.task;

import android.graphics.Bitmap;

import com.rpg.southparkavatars.character.Skin;
import com.rpg.southparkavatars.character.clothing.Clothing;
import com.rpg.southparkavatars.character.head.HeadFeature;

import java.util.List;

public interface AsyncTaskListener {
    void onClothesAsyncTaskFinished(List<Clothing> clothes);

    void onHeadFeaturesAsyncTaskFinished(List<HeadFeature> headFeatures);

    void onSkinAsyncTaskFinished(List<Skin> skins);
}
