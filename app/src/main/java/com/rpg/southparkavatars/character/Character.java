package com.rpg.southparkavatars.character;

import android.support.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.rpg.southparkavatars.character.clothing.AbstractClothing;
import com.rpg.southparkavatars.character.clothing.CompositeClothing;
import com.rpg.southparkavatars.character.head.AbstractHeadFeature;
import com.rpg.southparkavatars.character.head.CompositeHeadFeature;
import com.rpg.southparkavatars.character.head.HeadFeature;
import com.rpg.southparkavatars.character.head.concrete.Eyes;
import com.rpg.southparkavatars.character.head.concrete.Hand;
import com.rpg.southparkavatars.character.head.concrete.Head;
import com.rpg.southparkavatars.character.head.concrete.Mouth;
import com.rpg.southparkavatars.character.voice.VoiceState;
import com.rpg.southparkavatars.memento.Memento;
import com.rpg.southparkavatars.observer.CharacterObserver;
import com.rpg.southparkavatars.observer.ItemObserver;
import com.rpg.southparkavatars.tool.UniqueIdentifierGenerator;
import com.rpg.southparkavatars.visitor.ClothingVisitor;

import java.util.ArrayList;
import java.util.List;

public class Character implements AbstractCharacter {
    private String name;
    private Skin skin;
    private Head head;
    private Hand hand;
    private String uuid;

    private CompositeClothing clothes = new CompositeClothing();
    private CompositeHeadFeature headFeatures = new CompositeHeadFeature();

    private transient VoiceState currentVoiceState;
    private transient List<CharacterObserver> observers = new ArrayList<>();

    @JsonCreator
    public Character(@JsonProperty("name") String name,
                     @JsonProperty("compositeClothes") CompositeClothing clothes,
                     @JsonProperty("compositeHeadFeatures") CompositeHeadFeature headFeatures,
                     @JsonProperty("skin") Skin skin,
                     @JsonProperty("head") Head head,
                     @JsonProperty("hand") Hand hand,
                     @JsonProperty("uuid") String uuid) {
        this.name = name;
        this.skin = skin;
        this.head = head;
        this.hand = hand;
        this.uuid = uuid;
        this.clothes = clothes;
        this.headFeatures = headFeatures;
    }

    public Character() {
        skin = new Skin(Skin.Color.WHITE);
        head = new Head(HeadFeature.HEAD.getDefaultPath());
        hand = new Hand(HeadFeature.HAND.getDefaultPath());

        headFeatures.add(new Eyes(HeadFeature.EYES.getDefaultPath()));
        headFeatures.add(new Mouth(HeadFeature.MOUTH.getDefaultPath()));

        uuid = UniqueIdentifierGenerator.getInstance().generateUuid();

        notifyAllObservers();
    }

    @Override
    public void copy(Character character) {
        name = character.name;
        clothes = character.clothes;
        headFeatures = character.headFeatures;
        skin = character.skin;
        head = character.head;
        hand = character.hand;
        uuid = character.uuid;

        notifyAllObservers();
    }

    public void setState(VoiceState state) {
        currentVoiceState = state;
    }

    public int handleVoice() {
        return currentVoiceState.handleVoice();
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

    public void setSkinFeatures(Skin skin, Head head, Hand hand) {
        this.skin = skin;
        this.hand = hand;
        this.head = head;

        notifyAllObservers();
    }

    public void removeClothingType(Class<? extends AbstractClothing> clothingClass) {
        for (AbstractClothing clothing : clothes) {
            if (clothing.getClass() == clothingClass) {
                clothes.remove(clothing);
                notifyAllObservers();
                return;
            }
        }
    }

    public void removeHeadFeatureType(Class<? extends AbstractHeadFeature> headFeatureClass) {
        for (AbstractHeadFeature feature : headFeatures) {
            if (feature.getClass() == headFeatureClass) {
                headFeatures.remove(feature);
                notifyAllObservers();
                return;
            }
        }
    }

    public Memento saveToMemento() {
        return Memento.builder()
                .withHand(hand)
                .withHead(head)
                .withSkin(skin)
                .withCompositeClothing(clothes)
                .withHeadFeatures(headFeatures)
                .build();
    }

    public void restoreFromMemento(Memento memento) {
        if (memento == null) return;

        hand = memento.getHand();
        head = memento.getHead();
        skin = memento.getSkin();
        clothes = memento.getClothes();
        headFeatures = memento.getHeadFeatures();

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

    public VoiceState getCurrentVoiceState() {
        return currentVoiceState;
    }

    public Skin getSkin() {
        return skin;
    }

    public String getUuid() {
        return uuid;
    }

    public DrawableItem find(Class<?> classToBeFound) {
        if (AbstractClothing.class.isAssignableFrom(classToBeFound)) {
            DrawableItem clothing = findClothing(classToBeFound);
            if (clothing != null) return clothing;
        } else if (AbstractHeadFeature.class.isAssignableFrom(classToBeFound)) {
            DrawableItem feature = findHeadFeature(classToBeFound);
            if (feature != null) return feature;
        }

        return null;
    }

    @Nullable
    private DrawableItem findHeadFeature(Class<?> classToBeFound) {
        for (AbstractHeadFeature feature : headFeatures) {
            if (feature.getClass() == classToBeFound) {
                return feature;
            }
        }
        return null;
    }

    @Nullable
    private DrawableItem findClothing(Class<?> classToBeFound) {
        for (AbstractClothing clothing : clothes) {
            if (clothing.getClass() == classToBeFound) {
                return clothing;
            }
        }
        return null;
    }

    @Override
    public List<DrawableItem> display() {
        return new ArrayList<DrawableItem>();
    }

    public void addClothing(AbstractClothing clothing) {
        AbstractClothing oldClothing = getSameTypeObjectAlreadyWorn(clothing);

        if (oldClothing != null) {
            clothes.remove(oldClothing);
        }

        clothes.add(clothing);
        notifyAllObservers();
    }

    public Character saveable() {
        return this;
    }

    public List<AbstractClothing> getClothes() {
        return clothes.getClothes();
    }


    public void accept(ClothingVisitor visitor) {
        clothes.accept(visitor);
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

    public List<AbstractHeadFeature> getHeadFeatures() {
        return headFeatures.getHeadFeatures();
    }

    public Head getHead() {
        return head;
    }

    public Hand getHand() {
        return hand;
    }
}