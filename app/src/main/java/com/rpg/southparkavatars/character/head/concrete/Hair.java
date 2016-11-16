package com.rpg.southparkavatars.character.head.concrete;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rpg.southparkavatars.R;
import com.rpg.southparkavatars.character.head.HeadFeature;

public class Hair extends HeadFeature {
    public Hair(@JsonProperty("path") String path) {
        super(path);
        setrId(R.id.hair_image);
    }
}
