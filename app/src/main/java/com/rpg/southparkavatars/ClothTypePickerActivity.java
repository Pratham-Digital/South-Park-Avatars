package com.rpg.southparkavatars;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by peter on 2016. 10. 19..
 */

public class ClothTypePickerActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cloth_picker_activity);


    }

    public void ButtonOnClick(View v){

        Intent intent=new Intent(getApplicationContext(),DrawingActivity.class);


        switch(v.getId()){

            case R.id.createShirt:
                intent.putExtra("cloth-type", "Shirt");
            break;

            case R.id.createPants:
                intent.putExtra("cloth-type", "Pants");
            break;

            case R.id.createHat:
                intent.putExtra("cloth-type", "Hat");
            break;
        }

        startActivity(intent);
    }

}

