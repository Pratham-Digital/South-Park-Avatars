package com.rpg.southparkavatars;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends Activity{

    private Button createClothing;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        createClothing=(Button)findViewById(R.id.createClothing);
        createClothing.setOnClickListener(new View.OnClickListener()
                              {
                                  public void onClick(View v)
                                  {
                                      Intent i=new Intent(getApplicationContext(),ClothTypePickerActivity.class);
                                      startActivity(i);
                                  }
                              }
        );


    }




}
