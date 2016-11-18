package com.rpg.southparkavatars.character.clothing.concrete;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rpg.southparkavatars.R;
import com.rpg.southparkavatars.character.clothing.Clothing;

public class Glasses extends Clothing {
    public Glasses(@JsonProperty("coolness") int coolness, @JsonProperty("path") String path) {
        super(coolness, path);

        setScale(1.9f);
        setXPosDivider(2f);
        setYPosDivider(2.8f);
    }
}
