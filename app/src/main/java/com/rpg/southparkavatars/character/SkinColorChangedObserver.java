package com.rpg.southparkavatars.character;

import android.graphics.Bitmap;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.rpg.southparkavatars.Observer;
import com.rpg.southparkavatars.R;

public class SkinColorChangedObserver extends Observer {
    ViewGroup viewGroup;

    public SkinColorChangedObserver(ViewGroup viewGroup) {
        this.viewGroup = viewGroup;
    }

    @Override
    public void update() {
        Character character = Character.getInstance();

        ImageView handImage = (ImageView) viewGroup.findViewById(R.id.hand_image);
        ImageView headImage = (ImageView) viewGroup.findViewById(R.id.head_image);
        ImageView skinImage = (ImageView) viewGroup.findViewById(R.id.body_image);

        handImage.setImageResource(character.getSkinColor().getrDrawableHand());
        headImage.setImageResource(character.getSkinColor().getrDrawableHead());

        skinImage.setImageBitmap(character.getSkinColorBitmap());
    }
}
