package com.rpg.southparkavatars.character.head;

import java.io.File;

public enum HeadFeature {
    BEARD("head" + File.separator + "beard", "head" + File.separator + "beard" + File.separator + "beard_1.png"),
    EYES("head" + File.separator + "eyes", "head" + File.separator + "eyes" + File.separator + "eye_1.png"),
    HAIR("head" + File.separator + "hair", "head" + File.separator + "hair" + File.separator + "hair_3.png"),
    MOUTH("head" + File.separator + "mouth", "head" + File.separator + "mouth" + File.separator + "mouth_4.png"),
    HEAD("just_head", "just_head" + File.separator + "asian.png"),
    HAND("just_hand", "just_hand" + File.separator + "asian.png");

    private String path;
    private String defaultPath;

    HeadFeature(String path, String defaultPath) {
        this.path = path;
        this.defaultPath = defaultPath;
    }

    public String getPath() {
        return path;
    }

    public String getDefaultPath() {
        return defaultPath;
    }
}
