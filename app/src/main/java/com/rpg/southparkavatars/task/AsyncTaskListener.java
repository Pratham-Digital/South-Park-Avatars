package com.rpg.southparkavatars.task;

import com.rpg.southparkavatars.character.Skin;
import com.rpg.southparkavatars.character.clothing.AbstractClothing;
import com.rpg.southparkavatars.character.head.AbstractHeadFeature;

import java.util.List;

public interface AsyncTaskListener {
    void onClothesAsyncTaskFinished(List<AbstractClothing> clothes);

    void onHeadFeaturesAsyncTaskFinished(List<AbstractHeadFeature> headFeatures);

    void onSkinAsyncTaskFinished(List<Skin> skins);
}
