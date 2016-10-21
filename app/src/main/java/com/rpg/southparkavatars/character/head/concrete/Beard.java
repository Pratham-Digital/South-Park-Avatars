package com.rpg.southparkavatars.character.head.concrete;

import android.graphics.Bitmap;

import com.rpg.southparkavatars.character.head.HeadFeature;

public class Beard extends HeadFeature {
    Bitmap bitmap;

    public Beard(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }
}
