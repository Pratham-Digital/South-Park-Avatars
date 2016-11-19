package com.rpg.southparkavatars.observer;

import android.graphics.Bitmap;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.rpg.southparkavatars.tool.BitmapLoader;
import com.rpg.southparkavatars.character.head.AbstractHeadFeature;

import java.util.List;

public class HeadFeatureChangedObserver { // implements CharacterObserver {
    ViewGroup viewGroup;

    public HeadFeatureChangedObserver(ViewGroup viewGroup) {
        this.viewGroup = viewGroup;
    }

//    @Override
    public void update(CharacterChangedEvent event) {
        List<AbstractHeadFeature> features = event.getHeadFeatures();

        if (features != null) {
            for (AbstractHeadFeature headFeature : features) {
                ImageView imageView = (ImageView) viewGroup.findViewById(headFeature.getrId());
                Bitmap bitmap = BitmapLoader.load(headFeature.getPath());

                imageView.setImageBitmap(bitmap);
            }
        }
    }
}
