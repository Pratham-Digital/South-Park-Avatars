package com.rpg.southparkavatars.character;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Skin extends DrawableItem {
    private Color color;

    public Skin(Color color) {
        this(color, color.getPath());
    }

    @JsonCreator
    public Skin(@JsonProperty("color") Color color, @JsonProperty("path") String path) {
        this.color = color;
        this.path = path;

        setScalingFactors();
    }

    private void setScalingFactors() {
        setScale(4);
        setXPosDivider(2f);
        setYPosDivider(2f);
    }

    public Skin(Skin skin) {
        color = skin.getColor();
        path = new String(skin.getPath());

        setScalingFactors();
    }

    public Color getColor() {
        return color;
    }

    public enum Color {
        WHITE("skin_color/white.png"),
        LATIN("skin_color/latin.png"),
        BLACK("skin_color/black.png"),
        ASIAN("skin_color/asian.png"),
        JERSEY("skin_color/jersey.png");

        private String path;

        Color(String path) {
            this.path = path;
        }

        public String getPath() {
            return path;
        }
    }
}
