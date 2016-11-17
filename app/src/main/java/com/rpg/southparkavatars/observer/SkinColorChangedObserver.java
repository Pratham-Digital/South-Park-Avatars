package com.rpg.southparkavatars.observer;

import android.graphics.Bitmap;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.rpg.southparkavatars.character.Skin;
import com.rpg.southparkavatars.tool.BitmapLoader;
import com.rpg.southparkavatars.character.Character;
import com.rpg.southparkavatars.R;

public class SkinColorChangedObserver implements CharacterObserver {
    private ViewGroup viewGroup;

    public SkinColorChangedObserver(ViewGroup viewGroup) {
        this.viewGroup = viewGroup;
    }

    @Override
    public void update(CharacterChangedEvent event) {
        Skin skin = event.getSkin();

        if (skin != null) {
            ImageView handImage = (ImageView) viewGroup.findViewById(R.id.hand_image);
            ImageView headImage = (ImageView) viewGroup.findViewById(R.id.head_image);
            ImageView skinImage = (ImageView) viewGroup.findViewById(R.id.body_image);

            handImage.setImageResource(skin.getColor().getrDrawableHand());
            headImage.setImageResource(skin.getColor().getrDrawableHead());

            Bitmap bitmap = BitmapLoader.load(skin.getPath());
            skinImage.setImageBitmap(bitmap);
        }
    }
}
