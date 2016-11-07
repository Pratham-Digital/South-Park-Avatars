package com.rpg.southparkavatars.character;

import android.graphics.Bitmap;

import com.rpg.southparkavatars.Observer;
import com.rpg.southparkavatars.character.clothing.Clothing;
import com.rpg.southparkavatars.character.clothing.CompositeClothing;
import com.rpg.southparkavatars.character.head.CompositeHeadFeature;
import com.rpg.southparkavatars.character.head.HeadFeature;

import java.util.ArrayList;
import java.util.List;

public class Character implements Observable {
    private static final Character instance = new Character();

    private CompositeClothing clothes = new CompositeClothing();
    private CompositeHeadFeature headFeatures = new CompositeHeadFeature();

    private List<Observer> observers = new ArrayList<>();

    private Bitmap skinColorBitmap;
    private SkinColor skinColor;

    private Character() {
    }

    public void attach(Observer observer) {
        observers.add(observer);
    }

    public void notifyAllObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    public Bitmap getSkinColorBitmap() {
        return skinColorBitmap;
    }

    public void addClothing(Clothing clothing) {
        Clothing oldClothing = (Clothing) getSameTypeObjectAlreadyWorn(clothing);

        if (oldClothing != null) {
            clothes.remove(oldClothing);
        }

        clothes.add(clothing);
        notifyAllObservers();
    }

    public CompositeClothing getClothes() {
        return clothes;
    }

    private Object getSameTypeObjectAlreadyWorn(Object newClothing) {
        Class<?> newClass = newClothing.getClass();

        for (Object clothing : clothes) {
            if (clothing.getClass().equals(newClass)) {
                return clothing;
            }
        }

        return null;
    }

    public SkinColor getSkinColor() {
        return skinColor;
    }

    public void removeClothing(Clothing clothing) {
        clothes.remove(clothing);
    }

    public void addHeadFeature(HeadFeature headFeature) {
        HeadFeature oldHeadFeature = (HeadFeature) getSameTypeObjectAlreadyWorn(headFeature);

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

    public void setSkinColorBitmap(Bitmap skinColorBitmap, SkinColor skinColor) {
        this.skinColorBitmap = skinColorBitmap;
        this.skinColor = skinColor;

        notifyAllObservers();
    }
}
