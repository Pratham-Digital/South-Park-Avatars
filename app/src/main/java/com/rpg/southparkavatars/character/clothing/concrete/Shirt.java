package com.rpg.southparkavatars.character.clothing.concrete;

import android.graphics.Bitmap;

import com.rpg.southparkavatars.character.clothing.Clothing;

public class Shirt extends Clothing {
    private Bitmap bitmap;

    public Shirt(int coolness, Bitmap bitmap) {
        super(coolness);
        this.bitmap = bitmap;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }
}
