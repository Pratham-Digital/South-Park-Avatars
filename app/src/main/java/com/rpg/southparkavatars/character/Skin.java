package com.rpg.southparkavatars.character;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.rpg.southparkavatars.R;

import java.io.Serializable;

public class Skin extends DrawableItem {
    private Color color;

    public Skin(Color color) {
        this(color, color.getPath());
    }

    @JsonCreator
    public Skin(@JsonProperty("color") Color color, @JsonProperty("path") String path) {
        this.color = color;
        this.path = path;

        setScale(4);
        setXPosDivider(2f);
        setYPosDivider(2f);
    }

    public Color getColor() {
        return color;
    }

    public enum Color implements Serializable {
        WHITE(R.drawable.white_character_hand, R.drawable.white_character_head, "skin_color/white.png"),
        LATIN(R.drawable.latin_character_hand, R.drawable.latin_character_head, "skin_color/latin.png"),
        BLACK(R.drawable.black_character_hand, R.drawable.black_character_head, "skin_color/black.png"),
        ASIAN(R.drawable.asian_character_hand, R.drawable.asian_character_head, "skin_color/asian.png"),
        JERSEY(R.drawable.jersey_character_hand, R.drawable.jersey_character_head, "skin_color/jersey.png");

        private int rDrawableHand;
        private int rDrawableHead;
        private String path;

        Color(int rDrawableHand, int rDrawableHead, String path) {
            this.rDrawableHand = rDrawableHand;
            this.rDrawableHead = rDrawableHead;
            this.path = path;
        }

        public String getPath() {
            return path;
        }
    }
}
