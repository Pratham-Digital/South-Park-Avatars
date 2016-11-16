package com.rpg.southparkavatars.character.head.concrete;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rpg.southparkavatars.R;
import com.rpg.southparkavatars.character.head.HeadFeature;

public class Mouth extends HeadFeature {
    public Mouth(@JsonProperty("path") String path) {
        super(path);
        setrId(R.id.mouth_image);
    }
}
