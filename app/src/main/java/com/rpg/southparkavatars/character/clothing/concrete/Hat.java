package com.rpg.southparkavatars.character.clothing.concrete;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rpg.southparkavatars.R;
import com.rpg.southparkavatars.character.clothing.Clothing;

public class Hat extends Clothing {
    public Hat(@JsonProperty("coolness") int coolness, @JsonProperty("path") String path) {
        super(coolness, path);
        setrId(R.id.hat_image);
    }
}
