package com.rpg.southparkavatars.character.clothing.concrete;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rpg.southparkavatars.R;
import com.rpg.southparkavatars.character.clothing.Clothing;

public class Shirt extends Clothing {
    public Shirt(@JsonProperty("coolness") int coolness, @JsonProperty("path") String path) {
        super(coolness, path);
        setrId(R.id.shirt_image);
    }
}
