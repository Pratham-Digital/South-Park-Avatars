package com.rpg.southparkavatars.observer;

import android.graphics.Bitmap;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.rpg.southparkavatars.tool.BitmapLoader;
import com.rpg.southparkavatars.character.Character;
import com.rpg.southparkavatars.character.head.HeadFeature;

import java.util.List;

public class HeadFeatureChangedObserver { // implements CharacterObserver {
    ViewGroup viewGroup;

    public HeadFeatureChangedObserver(ViewGroup viewGroup) {
        this.viewGroup = viewGroup;
    }

//    @Override
    public void update(CharacterChangedEvent event) {
        List<HeadFeature> features = event.getHeadFeatures();

        if (features != null) {
            for (HeadFeature headFeature : features) {
                ImageView imageView = (ImageView) viewGroup.findViewById(headFeature.getrId());
                Bitmap bitmap = BitmapLoader.load(headFeature.getPath());

                imageView.setImageBitmap(bitmap);
            }
        }
    }
}
