package com.rpg.southparkavatars.observer;

import com.rpg.southparkavatars.character.Skin;
import com.rpg.southparkavatars.character.clothing.Clothing;
import com.rpg.southparkavatars.character.head.HeadFeature;

import java.util.List;

public class CharacterChangedEvent {
    private List<HeadFeature> headFeatures;
    private List<Clothing> clothes;
    private Skin skin;

    public CharacterChangedEvent(List<HeadFeature> headFeatures, List<Clothing> clothes, Skin skin) {
        this.headFeatures = headFeatures;
        this.clothes = clothes;
        this.skin = skin;
    }

    public List<HeadFeature> getHeadFeatures() {
        return headFeatures;
    }

    public List<Clothing> getClothes() {
        return clothes;
    }

    public Skin getSkin() {
        return skin;
    }
}
