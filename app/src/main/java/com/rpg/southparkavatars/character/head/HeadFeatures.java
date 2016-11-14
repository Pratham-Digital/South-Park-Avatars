package com.rpg.southparkavatars.character.head;

import java.io.File;

public enum HeadFeatures {
    BEARD("head" + File.separator + "beard"),
    EYES("head" + File.separator + "eyes"),
    HAIR("head" + File.separator + "hair"),
    MOUTH("head" + File.separator + "mouth");

    private String path;

    HeadFeatures(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
