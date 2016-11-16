package com.rpg.southparkavatars.character;

import android.net.Uri;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.rpg.southparkavatars.R;

import java.io.Serializable;

public class Skin {
    private String path;
    private Color color;

    @JsonCreator
    public Skin(@JsonProperty("path") String path, @JsonProperty("color") Color color) {
        this.path = path;
        this.color = color;
    }

    public String getPath() {
        return path;
    }

    public Color getColor() {
        return color;
    }

    public enum Color implements Serializable {
        WHITE(R.drawable.white_character_hand, R.drawable.white_character_head),
        LATIN(R.drawable.latin_character_hand, R.drawable.latin_character_head),
        BLACK(R.drawable.black_character_hand, R.drawable.black_character_head),
        ASIAN(R.drawable.asian_character_hand, R.drawable.asian_character_head),
        JERSEY(R.drawable.jersey_character_hand, R.drawable.jersey_character_head);

        private int rDrawableHand;
        private int rDrawableHead;

        Color(int rDrawableHand, int rDrawableHead) {
            this.rDrawableHand = rDrawableHand;
            this.rDrawableHead = rDrawableHead;
        }

        public int getrDrawableHand() {
            return rDrawableHand;
        }


        public int getrDrawableHead() {
            return rDrawableHead;
        }
    }
}
