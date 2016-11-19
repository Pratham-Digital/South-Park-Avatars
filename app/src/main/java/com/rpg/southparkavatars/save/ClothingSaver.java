package com.rpg.southparkavatars.save;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.rpg.southparkavatars.R;
import com.rpg.southparkavatars.new_cloth.CreatedClothing;


import java.io.File;
import java.io.FileOutputStream;

public class ClothingSaver {

    private Context context;
    private View view;
    private Activity activity;
    private CreatedClothing createdClothing; // = new CreatedClothing(context, activity, view);

    public ClothingSaver(CreatedClothing createdClothing, Context context, Activity activity, View view) {
        this.context = context;
        this.activity = activity;
        this.view = view;
        this.createdClothing = createdClothing;
    }

    public void saveClothing() {
        Button saveCloth = (Button) view.findViewById(R.id.save_clothing_button);
        saveCloth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreatedClothing.PaintingView v1 = createdClothing.getDrawingView();
                System.out.println(createdClothing.getDrawingView());

                String path = createdClothing.getDirectory().getPath() + File.separator
                        + createdClothing.getClothingTypeName() + "_" + filesInFolderCountString() + ".png";
                File file = new File(path);

                try (FileOutputStream ostream = new FileOutputStream(file)) {
                    v1.getBitmap().compress(Bitmap.CompressFormat.PNG, 100, ostream);
                    ostream.flush();

                    Toast.makeText(context, "image saved", Toast.LENGTH_LONG)
                            .show();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(context, "error", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });
    }

    public String filesInFolderCountString() {
        final int PUSH_START_COUNTING = 15;
        int numberOfFiles = 0;
        File dir = createdClothing.getDirectory();

        File[] files = dir.listFiles();
        if (files != null) {
            numberOfFiles = files.length;
        }

        return String.valueOf(numberOfFiles + PUSH_START_COUNTING);
    }

}
