package com.rpg.southparkavatars.character;

import android.view.ViewGroup;
import android.widget.ImageView;

import com.rpg.southparkavatars.R;
import com.rpg.southparkavatars.character.clothing.Clothing;
import com.rpg.southparkavatars.character.clothing.concrete.BackAccessory;
import com.rpg.southparkavatars.character.clothing.concrete.Hat;
import com.rpg.southparkavatars.character.clothing.concrete.Necklace;
import com.rpg.southparkavatars.character.clothing.concrete.Pants;

public class CharacterClothingChanged implements CharacterChangedDelegate<Clothing> {
    private ViewGroup view;

    public CharacterClothingChanged(ViewGroup view) {
        this.view = view;
    }

    @Override
    public void invoke(Clothing clothing) {
        ImageView imageView = null;

        if (clothing instanceof Hat) {
            imageView = (ImageView) view.findViewById(R.id.hat_image);
            imageView.setImageBitmap(((Hat) clothing).getBitmap());
        } else if (clothing instanceof Necklace) {
            imageView = (ImageView) view.findViewById(R.id.necklace_image);
            imageView.setImageBitmap(((Necklace) clothing).getBitmap());
        } else if (clothing instanceof BackAccessory) {
            imageView = (ImageView) view.findViewById(R.id.accessory_2_image);
            imageView.setImageBitmap(((BackAccessory) clothing).getBitmap());
        } else if (clothing instanceof Pants) {
            imageView = (ImageView) view.findViewById(R.id.pants_image);
            imageView.setImageBitmap(((Pants) clothing).getBitmap());
        }
    }
}
