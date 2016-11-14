package com.rpg.southparkavatars.character;

import android.graphics.Bitmap;

import com.rpg.southparkavatars.R;

public class Skin {
    Bitmap bitmap;
    Color color;

    public Skin(Bitmap bitmap, Color color) {
        this.bitmap = bitmap;
        this.color = color;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public Color getColor() {
        return color;
    }

    public enum Color {
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
