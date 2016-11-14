package com.rpg.southparkavatars.observer;

import android.view.ViewGroup;
import android.widget.ImageView;

import com.rpg.southparkavatars.character.Character;
import com.rpg.southparkavatars.observer.Observer;
import com.rpg.southparkavatars.character.head.HeadFeature;

public class HeadFeatureChangedObserver extends Observer {
    ViewGroup viewGroup;

    public HeadFeatureChangedObserver(ViewGroup viewGroup) {
        this.viewGroup = viewGroup;
    }

    @Override
    public void update() {
        Character character = Character.getInstance();
        for (HeadFeature headFeature : character.getHeadFeatures()) {
            ImageView imageView = (ImageView) viewGroup.findViewById(headFeature.getrId());
            imageView.setImageBitmap(headFeature.getBitmap());
        }
    }
}
