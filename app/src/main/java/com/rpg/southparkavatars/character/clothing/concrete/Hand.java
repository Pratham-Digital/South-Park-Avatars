package com.rpg.southparkavatars.character.clothing.concrete;

import android.graphics.Bitmap;

import com.rpg.southparkavatars.R;
import com.rpg.southparkavatars.character.clothing.Clothing;

public class Hand implements Clothing {
    private int rId;
    private int coolness;
    private Bitmap bitmap;

    public Hand(int coolness, Bitmap bitmap) {
        rId = R.id.accessory_1_image;
        this.coolness = coolness;
        this.bitmap = bitmap;
    }

    public int getrId() {
        return rId;
    }

    @Override
    public int getCoolness() {
        return coolness;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }
}