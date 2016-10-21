package com.rpg.southparkavatars.character.head.concrete;

import android.graphics.Bitmap;

import com.rpg.southparkavatars.character.head.HeadFeature;

public class Mouth extends HeadFeature {
    Bitmap bitmap;

    public Mouth(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }
}
