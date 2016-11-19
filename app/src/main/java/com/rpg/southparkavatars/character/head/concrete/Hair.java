package com.rpg.southparkavatars.character.head.concrete;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rpg.southparkavatars.character.head.AbstractHeadFeature;

public class Hair extends AbstractHeadFeature {
    public Hair(@JsonProperty("path") String path) {
        super(path);

        setScale(1.8f);
        setXPosDivider(2);
        setYPosDivider(4.6f);
    }
}
