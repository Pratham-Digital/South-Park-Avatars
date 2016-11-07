package com.rpg.southparkavatars.character.head.concrete;

import android.graphics.Bitmap;

import com.rpg.southparkavatars.R;
import com.rpg.southparkavatars.character.head.HeadFeature;

public class Beard implements HeadFeature {
    private int rId;
    private Bitmap bitmap;

    public Beard(Bitmap bitmap) {
        rId = R.id.beard_image;
        this.bitmap = bitmap;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public int getrId() {
        return rId;
    }
}
