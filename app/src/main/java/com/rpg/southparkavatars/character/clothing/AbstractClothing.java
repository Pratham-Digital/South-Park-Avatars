package com.rpg.southparkavatars.character.clothing;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.rpg.southparkavatars.character.DrawableItem;
import com.rpg.southparkavatars.character.clothing.concrete.Back;
import com.rpg.southparkavatars.character.clothing.concrete.Glasses;
import com.rpg.southparkavatars.character.clothing.concrete.Hand;
import com.rpg.southparkavatars.character.clothing.concrete.Hat;
import com.rpg.southparkavatars.character.clothing.concrete.Necklace;
import com.rpg.southparkavatars.character.clothing.concrete.Pants;
import com.rpg.southparkavatars.character.clothing.concrete.Shirt;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Back.class, name = "back"),
        @JsonSubTypes.Type(value = Glasses.class, name = "glasses"),
        @JsonSubTypes.Type(value = Hand.class, name = "hand"),
        @JsonSubTypes.Type(value = Hat.class, name = "hat"),
        @JsonSubTypes.Type(value = Necklace.class, name = "necklace"),
        @JsonSubTypes.Type(value = Pants.class, name = "pants"),
        @JsonSubTypes.Type(value = Shirt.class, name = "shirt")
})
public abstract class AbstractClothing extends DrawableItem {
    private int coolness;

    @JsonCreator
    public AbstractClothing(@JsonProperty("coolness") int coolness, @JsonProperty("path") String path) {
        this.coolness = coolness;
        this.path = path;
    }

    public int getCoolness() {
        return coolness;
    }

    public String getPath() {
        return path;
    }
}
