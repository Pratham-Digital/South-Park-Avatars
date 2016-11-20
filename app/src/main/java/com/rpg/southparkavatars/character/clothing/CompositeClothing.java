package com.rpg.southparkavatars.character.clothing;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rpg.southparkavatars.visitor.ClothingVisitor;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CompositeClothing extends AbstractClothing implements Iterable<AbstractClothing> {
    @JsonProperty("coolness")
    private int coolness = 0;
    @JsonProperty("clothes")
    private List<AbstractClothing> clothes = new ArrayList<>();

    public CompositeClothing() {
        super(0, "");
    }

    public CompositeClothing(CompositeClothing compositeClothing) {
        super(compositeClothing);

        clothes = new ArrayList<>();
        cloneList(compositeClothing);
    }

    private void cloneList(CompositeClothing compositeClothing) {
        for (AbstractClothing clothing : compositeClothing) {
            Class<?> clothingClass = clothing.getClass();
            try {
                Constructor<?> ctor = clothingClass.getConstructor(int.class, String.class);
                AbstractClothing createdClothing = (AbstractClothing) ctor.newInstance(clothing.getCoolness(), clothing.getPath());
                clothes.add(createdClothing);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException
                    | InstantiationException e) {
                e.printStackTrace();
            }
        }
    }

    public void add(AbstractClothing clothing) {
        clothes.add(clothing);
    }

    public void remove(AbstractClothing clothing) {
        clothes.remove(clothing);
    }

    public void setClothes(List<AbstractClothing> clothes) {
        this.clothes = clothes;
    }

    public List<AbstractClothing> getClothes() {
        return clothes;
    }

    @Override
    public Iterator<AbstractClothing> iterator() {
        return clothes.iterator();
    }

    @Override
    public int getCoolness() {
        return coolness;
    }

    @Override
    public void accept(ClothingVisitor visitor) {
        visitor.visit(this);
        for (AbstractClothing clothing : this.getClothes()) {
            clothing.accept(visitor);
        }
    }
}
