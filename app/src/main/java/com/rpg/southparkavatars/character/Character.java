package com.rpg.southparkavatars.character;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rpg.southparkavatars.character.clothing.Clothing;
import com.rpg.southparkavatars.character.clothing.CompositeClothing;
import com.rpg.southparkavatars.character.head.CompositeHeadFeature;
import com.rpg.southparkavatars.character.head.HeadFeature;
import com.rpg.southparkavatars.character.head.HeadFeatures;
import com.rpg.southparkavatars.character.head.concrete.Eyes;
import com.rpg.southparkavatars.character.head.concrete.Mouth;
import com.rpg.southparkavatars.observer.Observer;

import java.util.ArrayList;
import java.util.List;

public class Character implements Observable {
    private static final transient Character instance = new Character();

    @JsonProperty("name")
    private String name;
    @JsonProperty("compositeClothes")
    private CompositeClothing clothes = new CompositeClothing();
    @JsonProperty("compositeHeadFeatures")
    private CompositeHeadFeature headFeatures = new CompositeHeadFeature();
    private transient List<Observer> observers = new ArrayList<>();

    @JsonProperty("skin")
    private Skin skin;

    public Character() {
        skin = new Skin(Skin.Color.WHITE);
        headFeatures.add(new Eyes(HeadFeatures.EYES.getDefaultPath()));
        headFeatures.add(new Mouth(HeadFeatures.MOUTH.getDefaultPath()));
    }

    public void attach(Observer observer) {
        observers.add(observer);
    }

    public void notifyAllObservers() {
        for (Observer observer : observers) {
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

    public boolean hasSkin() {
        return skin != null;
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

    public static Character getInstance() {
        return instance;
    }
}
