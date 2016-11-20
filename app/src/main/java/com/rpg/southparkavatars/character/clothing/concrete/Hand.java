package com.rpg.southparkavatars.character.clothing.concrete;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rpg.southparkavatars.character.clothing.AbstractClothing;

public class Hand extends AbstractClothing {
    public Hand(@JsonProperty("coolness") int coolness, @JsonProperty("path") String path) {
        super(coolness, path);

        setScale(0.7f);
        setXPosDivider(4.1f);
        setYPosDivider(1.35f);
    }
}
