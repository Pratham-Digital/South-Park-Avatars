package com.rpg.southparkavatars.character.clothing.concrete;

import android.graphics.Bitmap;

import com.rpg.southparkavatars.character.clothing.Clothing;

public class Glasses extends Clothing {
    private Bitmap bitmap;

    public Glasses(int coolness, Bitmap image) {
        super(coolness);
    }

    public Bitmap getBitmap() {
        return bitmap;
    }
}
