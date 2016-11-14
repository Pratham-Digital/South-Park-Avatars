package com.rpg.southparkavatars.character.clothing.concrete;

import android.graphics.Bitmap;

import com.rpg.southparkavatars.R;
import com.rpg.southparkavatars.character.clothing.Clothing;

public class Back implements Clothing {
    private int rId;
    private int coolness;
    private Bitmap bitmap;

    public Back(int coolness, Bitmap bitmap) {
        rId = R.id.accessory_2_image;
        this.coolness = coolness;
        this.bitmap = bitmap;
    }

    public int getrId() {
        return rId;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    @Override
    public int getCoolness() {
        return coolness;
    }
}
