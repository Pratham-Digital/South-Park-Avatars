package com.rpg.southparkavatars.character.head.concrete;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rpg.southparkavatars.character.head.AbstractHeadFeature;

public class Beard extends AbstractHeadFeature {
    public Beard(@JsonProperty("path") String path) {
        super(path);

        setScale(1.7f);
        setXPosDivider(2);
        setYPosDivider(2f);
    }
}
