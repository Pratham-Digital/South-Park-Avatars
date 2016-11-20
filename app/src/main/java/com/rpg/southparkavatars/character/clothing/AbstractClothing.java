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
import com.rpg.southparkavatars.visitor.ClothingVisitor;
import com.rpg.southparkavatars.visitor.VisitableClothing;

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
public abstract class AbstractClothing extends DrawableItem implements VisitableClothing {
    private int coolness;

    @JsonCreator
    public AbstractClothing(@JsonProperty("coolness") int coolness, @JsonProperty("path") String path) {
        this.coolness = coolness;
        this.path = path;
    }

    public AbstractClothing(AbstractClothing abstractClothing) {
        this.coolness = abstractClothing.getCoolness();
        this.path = new String(abstractClothing.getPath());
    }

    public int getCoolness() {
        return coolness;
    }

    public void setCoolness(int coolness) {
        if (this.coolness == 0)
            this.coolness = coolness;
    }

    public String getPath() {
        return path;
    }

    @Override
    public void accept(ClothingVisitor visitor) {
        visitor.visit(this);
    }
}
