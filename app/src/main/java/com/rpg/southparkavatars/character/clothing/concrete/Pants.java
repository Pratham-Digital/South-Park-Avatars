package com.rpg.southparkavatars.character.clothing.concrete;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rpg.southparkavatars.character.clothing.AbstractClothing;

public class Pants extends AbstractClothing {
    public Pants(@JsonProperty("coolness") int coolness, @JsonProperty("path") String path) {
        super(coolness, path);

        setScale(1.3f);
        setXPosDivider(2f);
        setYPosDivider(1.275f);
    }
}
