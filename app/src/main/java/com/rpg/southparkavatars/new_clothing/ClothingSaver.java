package com.rpg.southparkavatars.new_clothing;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.rpg.southparkavatars.R;
import com.rpg.southparkavatars.character.clothing.Clothing;
import com.rpg.southparkavatars.view.PaintingView;


import org.apache.commons.lang3.ArrayUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;

public class ClothingSaver {
    private Context context;
    private View view;
    private Activity activity;
    private PaintingView paintingView;
    private CustomClothing customClothing;

    public ClothingSaver(CustomClothing customClothing, PaintingView paintingView, Context context, Activity activity, View view) {
        this.context = context;
        this.activity = activity;
        this.view = view;
        this.paintingView = paintingView;
        this.customClothing = customClothing;
    }

    public void saveClothing() {
        Button saveCloth = (Button) view.findViewById(R.id.save_clothing_button);
        saveCloth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path = customClothing.getDirectory().getPath() + File.separator
                        + customClothing.getClothingTypeName() + "_" + filesInFolderCountString() + ".png";
                File file = new File(path);

                try (FileOutputStream ostream = new FileOutputStream(file)) {
                    Clothing type = customClothing.getClothingType();
                    Bitmap bitmap = paintingView.getBitmap();

                    bitmap = Bitmap.createScaledBitmap(bitmap, type.getWidth(), type.getHeight(), false);
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, ostream);
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
        final int PUSH_START_COUNTING = 21;
        int numberOfFiles = 0;
        File dir = customClothing.getDirectory();

        File[] files = dir.listFiles();
        if (files != null) {
            numberOfFiles = files.length;
        }

        return String.valueOf(numberOfFiles + PUSH_START_COUNTING);
    }

}
