package com.rpg.southparkavatars.memento;


import com.rpg.southparkavatars.character.Skin;
import com.rpg.southparkavatars.character.clothing.CompositeClothing;
import com.rpg.southparkavatars.character.head.CompositeHeadFeature;
import com.rpg.southparkavatars.character.head.concrete.Hand;
import com.rpg.southparkavatars.character.head.concrete.Head;

public class Memento {
    private Skin skin;
    private Head head;
    private Hand hand;

    private CompositeClothing clothes = new CompositeClothing();
    private CompositeHeadFeature headFeatures = new CompositeHeadFeature();

    public Memento() {

    }

    public Memento(Skin skin, Head head, Hand hand, CompositeClothing clothes, CompositeHeadFeature headFeatures) {
        this.skin = skin;
        this.head = head;
        this.hand = hand;
        this.clothes = clothes;
        this.headFeatures = headFeatures;
    }

    public void setSkin(Skin skin) {

        this.skin = skin;
    }

    public void setHead(Head head) {
        this.head = head;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public void setClothes(CompositeClothing clothes) {
        this.clothes = clothes;
    }

    public void setHeadFeatures(CompositeHeadFeature headFeatures) {
        this.headFeatures = headFeatures;
    }

    public Skin getSkin() {
        return skin;
    }

    public Head getHead() {
        return head;
    }

    public Hand getHand() {
        return hand;
    }

    public CompositeClothing getClothes() {
        return clothes;
    }

    public CompositeHeadFeature getHeadFeatures() {
        return headFeatures;
    }

    public static class Builder {
        private Memento memento;

        public Builder() {
            this.memento = new Memento();
        }

        public Builder withSkin(Skin skin) {
            memento.setSkin(new Skin(skin));
            return this;
        }

        public Builder withHead(Head head) {
            memento.setHead(new Head(head));
            return this;
        }

        public Builder withHand(Hand hand) {
            memento.setHand(new Hand(hand));
            return this;
        }

        public Builder withCompositeClothing(CompositeClothing clothing) {
            memento.setClothes(new CompositeClothing(clothing));
            return this;
        }


        public Builder withHeadFeatures(CompositeHeadFeature headFeatures) {
            memento.setHeadFeatures(new CompositeHeadFeature(headFeatures));
            return this;
        }

        public Memento build() {
            return memento;
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
