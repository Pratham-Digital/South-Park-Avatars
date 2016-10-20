package com.rpg.southparkavatars.character;

import android.view.ViewGroup;

import com.rpg.southparkavatars.character.head.HeadFeature;

public class CharacterHeadFeatureChanged implements CharacterChangedDelegate<HeadFeature> {
    private ViewGroup view;

    public CharacterHeadFeatureChanged(ViewGroup view) {
        this.view = view;
    }

    @Override
    public void invoke(HeadFeature object) {

    }
}
