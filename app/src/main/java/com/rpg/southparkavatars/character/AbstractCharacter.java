package com.rpg.southparkavatars.character;

import com.rpg.southparkavatars.character.clothing.AbstractClothing;
import com.rpg.southparkavatars.character.head.AbstractHeadFeature;
import com.rpg.southparkavatars.character.head.concrete.Hand;
import com.rpg.southparkavatars.character.head.concrete.Head;
import com.rpg.southparkavatars.observer.ObservableItem;

import java.util.List;

public interface AbstractCharacter extends ObservableItem {
    void addClothing(AbstractClothing clothing);

    List<AbstractClothing> getClothes();

    void addHeadFeature(AbstractHeadFeature headFeature);

    List<AbstractHeadFeature> getHeadFeatures();

    Skin getSkin();

    DrawableItem find(Class<?> classToBeFound);

    List<DrawableItem> display();

    void setName(String name);

    String getName();

    String getUuid();

    void copy(Character character);



    void setSkinFeatures(Skin skin, Head head, Hand hand);

    Character getRawCharacter();

    Head getHead();

    Hand getHand();
}