package com.rpg.southparkavatars.character.clothing.concrete;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rpg.southparkavatars.character.clothing.Clothing;

public class Hat extends Clothing {
    public Hat(@JsonProperty("coolness") int coolness, @JsonProperty("path") String path) {
        super(coolness, path);

        setScale(2.8f);
        setXPosDivider(2f);
        setYPosDivider(3.5f);
    }
}
