package com.rpg.southparkavatars.character.head.concrete;

import android.graphics.Bitmap;

import com.rpg.southparkavatars.R;
import com.rpg.southparkavatars.character.head.HeadFeature;

public class Hair implements HeadFeature {
    private int rId;
    private Bitmap bitmap;

    public Hair(Bitmap bitmap) {
        rId = R.id.hair_image;
        this.bitmap = bitmap;
    }

    public int getrId() {
        return rId;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }
}
