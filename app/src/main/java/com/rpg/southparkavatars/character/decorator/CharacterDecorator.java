package com.rpg.southparkavatars.character.decorator;

import com.rpg.southparkavatars.character.AbstractCharacter;
import com.rpg.southparkavatars.character.Character;
import com.rpg.southparkavatars.character.DrawableItem;
import com.rpg.southparkavatars.character.Skin;
import com.rpg.southparkavatars.character.clothing.AbstractClothing;
import com.rpg.southparkavatars.character.head.AbstractHeadFeature;
import com.rpg.southparkavatars.character.head.concrete.Hand;
import com.rpg.southparkavatars.character.head.concrete.Head;
import com.rpg.southparkavatars.character.voice.VoiceState;
import com.rpg.southparkavatars.memento.Memento;
import com.rpg.southparkavatars.observer.ItemObserver;
import com.rpg.southparkavatars.visitor.ClothingVisitor;

import java.util.List;

public abstract class CharacterDecorator implements AbstractCharacter {
    protected AbstractCharacter character;
    protected Class<?> itemClass;

    public CharacterDecorator(AbstractCharacter character, Class<?> itemClass) {
        this.character = character;
        this.itemClass = itemClass;
    }

    public Character saveable() {
        return character.saveable();
    }

    @Override
    public void addClothing(AbstractClothing clothing) {
        character.addClothing(clothing);
    }

    @Override
    public void addHeadFeature(AbstractHeadFeature headFeature) {
        character.addHeadFeature(headFeature);
    }

    @Override
    public List<AbstractClothing> getClothes() {
        return character.getClothes();
    }

    @Override
    public List<AbstractHeadFeature> getHeadFeatures() {
        return character.getHeadFeatures();
    }

    @Override
    public List<DrawableItem> display() {
        List<DrawableItem> list = character.display();
        DrawableItem item = character.find(itemClass);
        if (item != null) {
            list.add(item);
        }
        return list;
    }

    public DrawableItem find(Class<?> classToBeFound) {
        return character.find(classToBeFound);
    }

    @Override
    public void attach(ItemObserver observer) {
        character.attach(observer);
    }

    @Override
    public void notifyAllObservers() {
        character.notifyAllObservers();
    }

    @Override
    public Skin getSkin() {
        return character.getSkin();
    }

    @Override
    public void setName(String name) {
        character.setName(name);
    }

    @Override
    public String getUuid() {
        return character.getUuid();
    }

    @Override
    public String getName() {
        return character.getName();
    }

    @Override
    public void copy(Character character) {
        this.character.copy(character);
    }

    @Override
    public void setSkinFeatures(Skin skin, Head head, Hand hand) {
        character.setSkinFeatures(skin, head, hand);
    }

    @Override
    public void removeClothingType(Class<? extends AbstractClothing> clothingClass) {
        character.removeClothingType(clothingClass);
    }

    @Override
    public void removeHeadFeatureType(Class<? extends AbstractHeadFeature> headFeatureClass) {
        character.removeHeadFeatureType(headFeatureClass);
    }

    @Override
    public void accept(ClothingVisitor visitor) {
        character.accept(visitor);
    }

    @Override
    public Head getHead() {
        return character.getHead();
    }

    @Override
    public Hand getHand() {
        return character.getHand();
    }

    @Override
    public void setState(VoiceState state) {
        character.setState(state);
    }

    @Override
    public int handleVoice() {
        return character.handleVoice();
    }

    @Override
    public Memento saveToMemento() {
        return character.saveToMemento();
    }

    @Override
    public void restoreFromMemento(Memento memento) {
        character.restoreFromMemento(memento);
    }
}
