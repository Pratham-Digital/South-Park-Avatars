package com.rpg.southparkavatars.observer;

import android.graphics.Bitmap;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.rpg.southparkavatars.tool.BitmapLoader;
import com.rpg.southparkavatars.character.Character;
import com.rpg.southparkavatars.character.clothing.Clothing;

import java.util.List;

public class ClothingChangedObserver implements CharacterObserver {
    ViewGroup viewGroup;

    public ClothingChangedObserver(ViewGroup viewGroup) {
        this.viewGroup = viewGroup;
    }

    @Override
    public void update(CharacterChangedEvent event) {
        List<Clothing> clothes = event.getClothes();

        if (clothes != null) {
            for (Clothing clothing : clothes) {
                ImageView imageView = (ImageView) viewGroup.findViewById(clothing.getrId());
                Bitmap bitmap = BitmapLoader.load(clothing.getPath());
                imageView.setImageBitmap(bitmap);
            }
        }
    }
}
