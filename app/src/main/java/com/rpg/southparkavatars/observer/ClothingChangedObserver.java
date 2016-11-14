package com.rpg.southparkavatars.observer;

import android.view.ViewGroup;
import android.widget.ImageView;

import com.rpg.southparkavatars.character.Character;
import com.rpg.southparkavatars.observer.Observer;
import com.rpg.southparkavatars.character.clothing.Clothing;

public class ClothingChangedObserver extends Observer {
    ViewGroup viewGroup;

    public ClothingChangedObserver(ViewGroup viewGroup) {
        this.viewGroup = viewGroup;
    }

    @Override
    public void update() {
        Character character = Character.getInstance();

        for (Clothing clothing : character.getClothes()) {
            ImageView imageView = (ImageView) viewGroup.findViewById(clothing.getrId());
            imageView.setImageBitmap(clothing.getBitmap());
        }
    }
}
