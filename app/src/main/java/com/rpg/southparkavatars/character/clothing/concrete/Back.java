package com.rpg.southparkavatars.character.clothing.concrete;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rpg.southparkavatars.R;
import com.rpg.southparkavatars.character.clothing.Clothing;

public class Back extends Clothing {
    public Back(@JsonProperty("coolness") int coolness, @JsonProperty("path") String path) {
        super(coolness, path);

        setScale(2.2f);
        setXPosDivider(2f);
        setYPosDivider(1.4f);
    }
}
