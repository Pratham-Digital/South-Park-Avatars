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

    public void invokeWithData(Bitmap object, SkinColor color) {
        ImageView handImage = (ImageView) view.findViewById(R.id.hand_image);
        ImageView headImage = (ImageView) view.findViewById(R.id.head_image);

        if (color == SkinColor.ASIAN) {
            handImage.setImageResource(R.drawable.asian_character_hand);
            headImage.setImageResource(R.drawable.asian_character_head);
        } else if (color == SkinColor.BLACK) {
            handImage.setImageResource(R.drawable.black_character_hand);
            headImage.setImageResource(R.drawable.black_character_head);
        } else if (color == SkinColor.LATIN) {
            handImage.setImageResource(R.drawable.latin_character_hand);
            headImage.setImageResource(R.drawable.latin_character_head);
        } else if (color == SkinColor.WHITE) {
            handImage.setImageResource(R.drawable.white_character_hand);
            headImage.setImageResource(R.drawable.white_character_head);
        } else {
            handImage.setImageResource(R.drawable.jersey_character_hand);
            headImage.setImageResource(R.drawable.jersey_character_head);
        }

        ImageView oldImage = (ImageView) view.findViewById(R.id.body_image);
        oldImage.setImageBitmap(object);
    }
}
