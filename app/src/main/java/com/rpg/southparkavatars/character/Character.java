package com.rpg.southparkavatars.character;

import android.graphics.Bitmap;

import com.rpg.southparkavatars.character.clothing.Clothing;
import com.rpg.southparkavatars.character.clothing.CompositeClothing;
import com.rpg.southparkavatars.character.head.CompositeHeadFeature;
import com.rpg.southparkavatars.character.head.HeadFeature;

public class Character {
    private static final Character instance = new Character();

    private CompositeClothing clothes = new CompositeClothing();
    private CompositeHeadFeature headFeatures = new CompositeHeadFeature();

    private Bitmap skinColorBitmap;

    private CharacterChangedDelegate<Bitmap> skinColorDelegate;
    private CharacterChangedDelegate<Clothing> clothingDelegate;
    private CharacterChangedDelegate<HeadFeature> headFeatureDelegate;

    private Character() {
    }

    public void setSkinColorBitmap(Bitmap skinColorBitmap) {
        this.skinColorBitmap = skinColorBitmap;

        skinColorDelegate.invoke(skinColorBitmap);
    }

    public Bitmap getSkinColorBitmap() {
        return skinColorBitmap;
    }

    public void addClothing(Clothing clothing) {
        clothes.add(clothing);

        clothingDelegate.invoke(clothing);
    }

    public void removeClothing(Clothing clothing) {
        clothes.remove(clothing);
    }

    public void addHeadFeature(HeadFeature headFeature) {
        headFeatures.add(headFeature);

        headFeatureDelegate.invoke(headFeature);
    }

    public void removeHeadFeature(HeadFeature headFeature) {
        headFeatures.remove(headFeature);
    }

    public void setSkinColorDelegate(CharacterChangedDelegate<Bitmap> skinColorDelegate) {
        this.skinColorDelegate = skinColorDelegate;
    }

    public void setClothingDelegate(CharacterChangedDelegate<Clothing> clothingDelegate) {
        this.clothingDelegate = clothingDelegate;
    }

    public void setHeadFeatureDelegate(CharacterChangedDelegate<HeadFeature> headFeatureDelegate) {
        this.headFeatureDelegate = headFeatureDelegate;
    }

    public static Character getInstance() {
        return instance;
    }
}
