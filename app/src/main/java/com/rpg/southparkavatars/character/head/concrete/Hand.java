package com.rpg.southparkavatars.character.head.concrete;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rpg.southparkavatars.character.head.AbstractHeadFeature;

public class Hand extends AbstractHeadFeature {
    public Hand(@JsonProperty("path") String path) {
        super(path);

        setScale(.9f);
        setXPosDivider(2);
        setYPosDivider(1.36f);
    }
}
