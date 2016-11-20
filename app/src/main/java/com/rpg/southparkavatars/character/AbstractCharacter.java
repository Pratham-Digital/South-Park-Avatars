package com.rpg.southparkavatars.character;

import com.rpg.southparkavatars.character.clothing.AbstractClothing;
import com.rpg.southparkavatars.character.head.AbstractHeadFeature;
import com.rpg.southparkavatars.character.head.concrete.Hand;
import com.rpg.southparkavatars.character.head.concrete.Head;
import com.rpg.southparkavatars.character.voice.VoiceState;
import com.rpg.southparkavatars.memento.Memento;
import com.rpg.southparkavatars.observer.ObservableItem;
import com.rpg.southparkavatars.visitor.ClothingVisitor;

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

    void removeClothingType(Class<? extends AbstractClothing> clothingClass);

    void removeHeadFeatureType(Class<? extends AbstractHeadFeature> headFeatureClass);

    void setSkinFeatures(Skin skin, Head head, Hand hand);

    Head getHead();

    Hand getHand();

    void setState(VoiceState state);

    int handleVoice();

    Memento saveToMemento();

    void restoreFromMemento(Memento memento);

    Character saveable();

    void accept(ClothingVisitor visitor);
}