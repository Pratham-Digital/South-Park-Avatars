package com.rpg.southparkavatars.character.head;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.rpg.southparkavatars.character.DrawableItem;
import com.rpg.southparkavatars.character.head.concrete.Beard;
import com.rpg.southparkavatars.character.head.concrete.Eyes;
import com.rpg.southparkavatars.character.head.concrete.Hair;
import com.rpg.southparkavatars.character.head.concrete.Mouth;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Beard.class, name = "beard"),
        @JsonSubTypes.Type(value = Eyes.class, name = "eyes"),
        @JsonSubTypes.Type(value = Hair.class, name = "hair"),
        @JsonSubTypes.Type(value = Mouth.class, name = "mouth")
})
public abstract class AbstractHeadFeature extends DrawableItem {
    @JsonCreator
    public AbstractHeadFeature(@JsonProperty("path") String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public AbstractHeadFeature(AbstractHeadFeature headFeature) {
        this.path = headFeature.getPath();
    }
}
