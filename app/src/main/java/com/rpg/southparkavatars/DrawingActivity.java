package com.rpg.southparkavatars;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.rpg.southparkavatars.new_clothing.ClothingOperationsFacade;

public class DrawingActivity extends AppCompatActivity {
    private ClothingOperationsFacade clothingOperationsFacade;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = getLayoutInflater().inflate(R.layout.drawing_activity, null);
        setContentView(v);

        clothingOperationsFacade = new ClothingOperationsFacade(DrawingActivity.this, this, v);

        clothingOperationsFacade.persistClothing();
    }

}
