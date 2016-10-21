package com.rpg.southparkavatars.character;

import android.graphics.Bitmap;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.rpg.southparkavatars.R;

public class CharacterSkinColorChanged implements CharacterChangedDelegate<Bitmap> {
    private ViewGroup view;

    public CharacterSkinColorChanged(ViewGroup view) {
        this.view = view;
    }

    @Override
    public void invoke(Bitmap object) {
        ImageView oldImage = (ImageView) view.findViewById(R.id.body_image);
        oldImage.setImageBitmap(object);
    }
}
