package com.rpg.southparkavatars.save;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.rpg.southparkavatars.R;
import com.rpg.southparkavatars.newcloth.CreateClothType;


import java.io.File;
import java.io.FileOutputStream;

public class Save {

    private Context context;
    private View v;
    private Activity activity;
    private CreateClothType drawingParams = new CreateClothType(context, activity, v);

    public Save(Context context, Activity activity, View v){
        this.context = context;
        this.activity = activity;
        this.v = v;
    }

    public void saveClothing(){
        Button saveCloth = (Button) v.findViewById(R.id.save);
        saveCloth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               CreateClothType.PaintCloth v1 = drawingParams.getDv();
                System.out.println(drawingParams.getDv());
                File file = new File(context.getFilesDir() + File.separator + "clothing" +
                        File.separator + drawingParams.getTypeOfCloth()  + File.separator +
                        drawingParams.getTypeOfCloth() + "_" + filesInFolderCount() + ".png");

                FileOutputStream ostream = null;
                try {
                    ostream = new FileOutputStream(file);
                    v1.getImgBitmap().compress(Bitmap.CompressFormat.PNG, 100, ostream);
                    ostream.flush();
                    ostream.close();
                    Toast.makeText(context, "image saved", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(context, "error", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public String filesInFolderCount(){
        final int PUSH_START_COUNTING = 15;
        int numberOfFiles = 0;
        File dir = new File(context.getFilesDir() + File.separator + "clothing" + File.separator + drawingParams.getTypeOfCloth());
        File[] files = dir.listFiles();

        if(files != null) {
            numberOfFiles = files.length;
        }

        return Integer.toString(numberOfFiles + PUSH_START_COUNTING);
    }

}
