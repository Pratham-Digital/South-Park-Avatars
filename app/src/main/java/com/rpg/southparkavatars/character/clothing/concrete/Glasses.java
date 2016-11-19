package com.rpg.southparkavatars.character.clothing.concrete;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rpg.southparkavatars.character.clothing.AbstractClothing;

public class Glasses extends AbstractClothing {
    public Glasses(@JsonProperty("coolness") int coolness, @JsonProperty("path") String path) {
        super(coolness, path);

        setScale(1.9f);
        setXPosDivider(2f);
        setYPosDivider(2.8f);
    }
}
