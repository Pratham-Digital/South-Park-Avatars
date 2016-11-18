package com.rpg.southparkavatars.character;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.rpg.southparkavatars.character.clothing.Clothing;
import com.rpg.southparkavatars.character.clothing.CompositeClothing;
import com.rpg.southparkavatars.character.head.CompositeHeadFeature;
import com.rpg.southparkavatars.character.head.HeadFeature;
import com.rpg.southparkavatars.character.head.HeadFeatures;
import com.rpg.southparkavatars.character.head.concrete.Eyes;
import com.rpg.southparkavatars.character.head.concrete.Mouth;
import com.rpg.southparkavatars.observer.CharacterChangedEvent;
import com.rpg.southparkavatars.observer.CharacterObserver;

import java.util.ArrayList;
import java.util.List;

public class Character implements ObservableCharacter {
    private String name;
    private Skin skin;

    private CompositeClothing clothes = new CompositeClothing();
    private CompositeHeadFeature headFeatures = new CompositeHeadFeature();
    private transient List<CharacterObserver> observers = new ArrayList<>();

    @JsonCreator
    public Character(@JsonProperty("name") String name,
                     @JsonProperty("compositeClothes") CompositeClothing clothes,
                     @JsonProperty("compositeHeadFeatures") CompositeHeadFeature headFeatures,
                     @JsonProperty("skin") Skin skin) {
        this.name = name;
        this.clothes = clothes;
        this.headFeatures = headFeatures;
        this.skin = skin;
    }

    public Character() {
        skin = new Skin(Skin.Color.WHITE);
        headFeatures.add(new Eyes(HeadFeatures.EYES.getDefaultPath()));
        headFeatures.add(new Mouth(HeadFeatures.MOUTH.getDefaultPath()));

        notifyAllObservers();
    }

    public void copy(Character character) {
        name = character.name;
        clothes = character.clothes;
        headFeatures = character.headFeatures;
        skin = character.skin;

        notifyAllObservers();
    }

    public void attach(CharacterObserver observer) {
        observers.add(observer);
    }

    public void notifyAllObservers() {
        for (CharacterObserver observer : observers) {
            observer.update();
        }
    }

    public void setSkin(Skin skin) {
        this.skin = skin;
        notifyAllObservers();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Skin.Color getSkinColor() {
        return skin.getColor();
    }

    public String getSkinPath() {
        return skin.getPath();
    }

    public Skin getSkin() {
        return skin;
    }

    public void addClothing(Clothing clothing) {
        Clothing oldClothing = getSameTypeObjectAlreadyWorn(clothing);

        if (oldClothing != null) {
            clothes.remove(oldClothing);
        }

        clothes.add(clothing);
        notifyAllObservers();
    }

    public CompositeClothing getClothes() {
        return clothes;
    }

    private Clothing getSameTypeObjectAlreadyWorn(Clothing newClothing) {
        Class<?> newClass = newClothing.getClass();

        for (Clothing clothing : clothes) {
            if (clothing.getClass().equals(newClass)) {
                return clothing;
            }
        }

        return null;
    }

    private HeadFeature getSameTypeObjectAlreadyWorn(HeadFeature newFeature) {
        Class<?> newClass = newFeature.getClass();

        for (HeadFeature feature : headFeatures) {
            if (feature.getClass().equals(newClass)) {
                return feature;
            }
        }

        return null;
    }

    public void removeClothing(Clothing clothing) {
        clothes.remove(clothing);
    }

    public void addHeadFeature(HeadFeature headFeature) {
        HeadFeature oldHeadFeature = getSameTypeObjectAlreadyWorn(headFeature);

        if (oldHeadFeature != null) {
            headFeatures.remove(oldHeadFeature);
        }

        headFeatures.add(headFeature);
        notifyAllObservers();
    }

    public void removeHeadFeature(HeadFeature headFeature) {
        headFeatures.remove(headFeature);
    }

    public CompositeHeadFeature getHeadFeatures() {
        return headFeatures;
    }
}
