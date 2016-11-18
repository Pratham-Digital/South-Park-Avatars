package com.rpg.southparkavatars.character.head.concrete;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rpg.southparkavatars.character.head.HeadFeature;

public class Eyes extends HeadFeature {
    public Eyes(@JsonProperty("path") String path) {
        super(path);

        setScale(1.25f);
        setXPosDivider(2);
        setYPosDivider(2.9f);
    }
}
