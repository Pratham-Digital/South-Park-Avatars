package com.rpg.southparkavatars.character.head.concrete;

import android.graphics.Bitmap;

import com.rpg.southparkavatars.R;
import com.rpg.southparkavatars.character.head.HeadFeature;

public class Eyes implements HeadFeature {
    private int rId;
    private Bitmap bitmap;

    public Eyes(Bitmap bitmap) {
        rId = R.id.eyes_image;
        this.bitmap = bitmap;
    }

    public int getrId() {
        return rId;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }
}
