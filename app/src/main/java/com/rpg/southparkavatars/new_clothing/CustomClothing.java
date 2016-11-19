package com.rpg.southparkavatars.new_clothing;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.rpg.southparkavatars.ClothingTypePickerActivity;
import com.rpg.southparkavatars.R;
import com.rpg.southparkavatars.observer.CustomClothingEventArgs;
import com.rpg.southparkavatars.observer.ItemObserver;
import com.rpg.southparkavatars.observer.ObservableItem;
import com.rpg.southparkavatars.character.clothing.Clothing;
import com.rpg.southparkavatars.observer.CustomClothingObserver;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import petrov.kristiyan.colorpicker.ColorPicker;

public class CustomClothing implements ObservableItem {
    private static int globalColor = Color.BLACK;

    private List<CustomClothingObserver> observers = new ArrayList<>();
    private DrawState drawState = DrawState.DRAWING;

    private Paint mPaint = new Paint();
    private Context context;
    private Activity activity;
    private Bitmap typeOfClothFrame;
    private View view;
    private Clothing clothingType;
    private String typeName;
    private File directory;

    public CustomClothing(Context context, Activity activity, View view) {
        this.context = context;
        this.activity = activity;
        this.view = view;
    }

    public Clothing getClothingType() {
        return clothingType;
    }

    private void ensureDirectoriesExist() {
        File root = getRootDirectory();
        ensureClothingDirectoryExists(root, typeName);
    }

    public void onStart() {
        inferTypeName();
        ensureDirectoriesExist();

//        ImageView drawTitle = (ImageView) activity.findViewById(R.id.drawTitle);
//        LinearLayout paintingArea = (LinearLayout) activity.findViewById(R.id.paint);
//        ViewGroup.LayoutParams params = paintingArea.getLayoutParams();
//
//        paintingView = new PaintingView(paintingArea.getContext());
//        paintingArea.addView(paintingView);

//        initPaint();
//        drawImages(drawTitle, params);
        setFillColor();
        setPenColor();
    }

    private void inferTypeName() {
        String intentExtraString = activity.getIntent()
                .getStringExtra(ClothingTypePickerActivity.ClothingTypeExtra);

        clothingType = Clothing.valueOf(intentExtraString.toUpperCase());
        typeName = clothingType.toString().toLowerCase();
    }

    private void drawImages(ImageView drawTitle, ViewGroup.LayoutParams params) {
        int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 260, activity.getResources().getDisplayMetrics());
        int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, clothingType.getTypeDimensionValue(), context.getResources().getDisplayMetrics());
        params.height = height;

        try {
            int rId = R.drawable.class.getField(typeName + "_frame")
                    .getInt(null);

            typeOfClothFrame = BitmapFactory.decodeResource(context.getResources(), rId).copy(Bitmap.Config.ARGB_8888, true);
            typeOfClothFrame = Bitmap.createScaledBitmap(typeOfClothFrame, width, height, false);

            rId = R.drawable.class.getField("draw_" + typeName)
                    .getInt(null);

            drawTitle.setImageResource(rId);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void ensureClothingDirectoryExists(File root, String typeName) {
        directory = new File(root + File.separator + typeName);

        if (!directory.exists()) {
            directory.mkdir();
        }
    }

    @NonNull
    public File getRootDirectory() {
        File root = new File(context.getFilesDir() + File.separator + "clothing");

        if (!root.exists()) {
            root.mkdir();
        }

        return root;
    }

    public String getClothingTypeName() {
        return clothingType.toString().toLowerCase();
    }

    public File getDirectory() {
        return directory;
    }

    public void setFillColor() {
        Button setColor = (Button) view.findViewById(R.id.fill);
        setColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorPicker colorPicker = new ColorPicker(context).setRoundColorButton(true);
                colorPicker.show();

                colorPicker.setOnChooseColorListener(new ColorPicker.OnChooseColorListener() {
                    @Override
                    public void onChooseColor(int position, int color) {
                        globalColor = color;
                        mPaint.setColor(globalColor);
                        drawState = DrawState.COLORING;
                        notifyAllObservers();
                    }

                    @Override
                    public void onCancel() {
                    }
                });
            }
        });
    }

    public void setPenColor() {
        Button setPen = (Button) view.findViewById(R.id.pen);
        setPen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorPicker colorPicker = new ColorPicker(context).setRoundColorButton(true);
                colorPicker.show();
                colorPicker.setOnChooseColorListener(new ColorPicker.OnChooseColorListener() {
                    @Override
                    public void onChooseColor(int position, int color) {
                        globalColor = color;
                        mPaint.setColor(globalColor);

//                        if (drawState == DrawState.COLORING) {
                        drawState = DrawState.DRAWING;
                        notifyAllObservers();
//                        }
                    }

                    @Override
                    public void onCancel() {
                    }
                });
            }
        });
    }

    @Override
    public void attach(ItemObserver observer) {
        observers.add((CustomClothingObserver) observer);
    }

    @Override
    public void notifyAllObservers() {
        for (CustomClothingObserver observer : observers) {
            observer.update(new CustomClothingEventArgs(
                    globalColor,
                    drawState
            ));
        }
    }
}
