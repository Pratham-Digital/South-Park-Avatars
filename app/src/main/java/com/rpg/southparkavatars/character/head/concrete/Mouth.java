package com.rpg.southparkavatars.character.head.concrete;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rpg.southparkavatars.character.head.AbstractHeadFeature;

public class Mouth extends AbstractHeadFeature {
    public Mouth(@JsonProperty("path") String path) {
        super(path);

        setScale(1f);
        setXPosDivider(2);
        setYPosDivider(1.9f);
    }
}
