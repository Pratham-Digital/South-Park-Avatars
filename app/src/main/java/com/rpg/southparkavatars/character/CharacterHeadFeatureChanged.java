package com.rpg.southparkavatars.character;

import android.view.ViewGroup;
import android.widget.ImageView;

import com.rpg.southparkavatars.R;
import com.rpg.southparkavatars.character.clothing.concrete.Glasses;
import com.rpg.southparkavatars.character.head.HeadFeature;
import com.rpg.southparkavatars.character.head.concrete.Beard;
import com.rpg.southparkavatars.character.head.concrete.Eyes;
import com.rpg.southparkavatars.character.head.concrete.Hair;
import com.rpg.southparkavatars.character.head.concrete.Mouth;

public class CharacterHeadFeatureChanged implements CharacterChangedDelegate<HeadFeature> {
    private ViewGroup view;

    public CharacterHeadFeatureChanged(ViewGroup view) {
        this.view = view;
    }

    @Override
    public void invoke(HeadFeature object) {
        ImageView imageView;

        if (object instanceof Beard) {
            imageView = (ImageView) view.findViewById(R.id.beard_image);
            imageView.setImageBitmap(((Beard) object).getBitmap());
        } else if (object instanceof Eyes) {
            imageView = (ImageView) view.findViewById(R.id.eyes_image);
            imageView.setImageBitmap(((Eyes) object).getBitmap());
        } else if (object instanceof Hair) {
            imageView = (ImageView) view.findViewById(R.id.hair_image);
            imageView.setImageBitmap(((Hair) object).getBitmap());
        } else if (object instanceof Mouth) {
            imageView = (ImageView) view.findViewById(R.id.mouth_image);
            imageView.setImageBitmap(((Mouth) object).getBitmap());
        }
    }
}
