package com.rpg.southparkavatars.character.head;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rpg.southparkavatars.character.clothing.AbstractClothing;
import com.rpg.southparkavatars.character.clothing.CompositeClothing;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CompositeHeadFeature extends AbstractHeadFeature implements Iterable<AbstractHeadFeature> {
    @JsonProperty("headFeatures")
    private List<AbstractHeadFeature> headFeatures = new ArrayList<>();

    public CompositeHeadFeature() {
        super("");
    }

    public CompositeHeadFeature(CompositeHeadFeature compositeHeadFeature) {
        super("");

        headFeatures = new ArrayList<>();
        cloneList(compositeHeadFeature);
    }

    private void cloneList(CompositeHeadFeature compositeHeadFeature) {
        for (AbstractHeadFeature feature : compositeHeadFeature) {
            Class<?> clothingClass = feature.getClass();
            try {
                Constructor<?> ctor = clothingClass.getConstructor(String.class);
                AbstractHeadFeature createdFeature = (AbstractHeadFeature) ctor.newInstance(feature.getPath());
                headFeatures.add(createdFeature);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException
                    | InstantiationException e) {
                e.printStackTrace();
            }
        }
    }

    public void add(AbstractHeadFeature headFeature) {
        headFeatures.add(headFeature);
    }

    public void remove(AbstractHeadFeature headFeature) {
        headFeatures.remove(headFeature);
    }

    @Override
    public Iterator<AbstractHeadFeature> iterator() {
        return headFeatures.iterator();
    }

    public List<AbstractHeadFeature> getHeadFeatures() {
        return headFeatures;
    }
}
