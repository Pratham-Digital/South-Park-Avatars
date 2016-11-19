package com.rpg.southparkavatars.character;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.rpg.southparkavatars.character.clothing.AbstractClothing;
import com.rpg.southparkavatars.character.clothing.CompositeAbstractClothing;
import com.rpg.southparkavatars.character.head.CompositeHeadFeature;
import com.rpg.southparkavatars.character.head.AbstractHeadFeature;
import com.rpg.southparkavatars.character.head.HeadFeatures;
import com.rpg.southparkavatars.character.head.concrete.Eyes;
import com.rpg.southparkavatars.character.head.concrete.Mouth;
import com.rpg.southparkavatars.observer.CharacterObserver;
import com.rpg.southparkavatars.observer.ItemObserver;
import com.rpg.southparkavatars.observer.ObservableItem;
import com.rpg.southparkavatars.tool.UniqueIdentifierGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Character implements ObservableItem {
    private String name;
    private Skin skin;
    private String uuid;

    private CompositeAbstractClothing clothes = new CompositeAbstractClothing();
    private CompositeHeadFeature headFeatures = new CompositeHeadFeature();
    private transient List<CharacterObserver> observers = new ArrayList<>();

    @JsonCreator
    public Character(@JsonProperty("name") String name,
                     @JsonProperty("compositeClothes") CompositeAbstractClothing clothes,
                     @JsonProperty("compositeHeadFeatures") CompositeHeadFeature headFeatures,
                     @JsonProperty("skin") Skin skin,
                     @JsonProperty("uuid") String uuid) {
        this.name = name;
        this.clothes = clothes;
        this.headFeatures = headFeatures;
        this.skin = skin;
        this.uuid = uuid;
    }

    public Character() {
        skin = new Skin(Skin.Color.WHITE);
        headFeatures.add(new Eyes(HeadFeatures.EYES.getDefaultPath()));
        headFeatures.add(new Mouth(HeadFeatures.MOUTH.getDefaultPath()));

        uuid = UniqueIdentifierGenerator.getInstance().generateUuid();

        notifyAllObservers();
    }

    public void copy(Character character) {
        name = character.name;
        clothes = character.clothes;
        headFeatures = character.headFeatures;
        skin = character.skin;
        uuid = character.uuid;

        notifyAllObservers();
    }

    @Override
    public void attach(ItemObserver observer) {
        observers.add((CharacterObserver) observer);
    }

    @Override
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

    public String getUuid() {
        return uuid;
    }

    public void addClothing(AbstractClothing clothing) {
        AbstractClothing oldClothing = getSameTypeObjectAlreadyWorn(clothing);

        if (oldClothing != null) {
            clothes.remove(oldClothing);
        }

        clothes.add(clothing);
        notifyAllObservers();
    }

    public CompositeAbstractClothing getClothes() {
        return clothes;
    }

    private AbstractClothing getSameTypeObjectAlreadyWorn(AbstractClothing newClothing) {
        Class<?> newClass = newClothing.getClass();

        for (AbstractClothing clothing : clothes) {
            if (clothing.getClass().equals(newClass)) {
                return clothing;
            }
        }

        return null;
    }

    private AbstractHeadFeature getSameTypeObjectAlreadyWorn(AbstractHeadFeature newFeature) {
        Class<?> newClass = newFeature.getClass();

        for (AbstractHeadFeature feature : headFeatures) {
            if (feature.getClass().equals(newClass)) {
                return feature;
            }
        }

        return null;
    }

    public void removeClothing(AbstractClothing clothing) {
        clothes.remove(clothing);
    }

    public void addHeadFeature(AbstractHeadFeature headFeature) {
        AbstractHeadFeature oldHeadFeature = getSameTypeObjectAlreadyWorn(headFeature);

        if (oldHeadFeature != null) {
            headFeatures.remove(oldHeadFeature);
        }

        headFeatures.add(headFeature);
        notifyAllObservers();
    }

    public void removeHeadFeature(AbstractHeadFeature headFeature) {
        headFeatures.remove(headFeature);
    }

    public CompositeHeadFeature getHeadFeatures() {
        return headFeatures;
    }
}
