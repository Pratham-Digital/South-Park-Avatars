package com.rpg.southparkavatars.new_cloth;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.support.annotation.NonNull;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.rpg.southparkavatars.R;
import com.rpg.southparkavatars.character.clothing.Clothing;
import com.rpg.southparkavatars.save.ClothingSaver;

import java.io.File;

import petrov.kristiyan.colorpicker.ColorPicker;


public class CreatedClothing {
    private static int globalColor = Color.BLACK;
    private Paint mPaint;
    private static PaintingView paintingView;
    private Context context;
    private Activity activity;
    private Bitmap typeOfClothFrame;
    private DrawState drawState;
    private View view;
    private Clothing clothingType;
    private String typeName;
    private File directory;

    public CreatedClothing(Context context, Activity activity, View view) {
        this.context = context;
        this.activity = activity;
        this.view = view;
    }

    private void ensureDirectoriesExist(Activity activity) {
        String intentExtraString = activity.getIntent().getStringExtra("clothType");

        clothingType = Clothing.valueOf(intentExtraString.toUpperCase());
        typeName = clothingType.toString().toLowerCase();

        File root = getRootDirectory();
        ensureClothingDirectoryExists(root, typeName);
    }

    public void onStart() {
        ensureDirectoriesExist(activity);

        ImageView drawTitle = (ImageView) activity.findViewById(R.id.drawTitle);
        LinearLayout paintingArea = (LinearLayout) activity.findViewById(R.id.paint);

        ViewGroup.LayoutParams params = paintingArea.getLayoutParams();

        drawState = DrawState.DRAWING;

        paintingView = new PaintingView(paintingArea.getContext());
        paintingArea.addView(paintingView);

        initPaint();

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

        setFillColor();
        setPenColor();
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

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(globalColor);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(6);
    }


    public void persistCloth() {
        ClothingSaver clothingSaver = new ClothingSaver(this, context, activity, view);
        clothingSaver.saveClothing();
    }

    public Bitmap getTypeOfClothFrame() {
        return typeOfClothFrame;
    }

    public PaintingView getDrawingView() {
        return paintingView;
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

                        if (drawState == DrawState.COLORING) {
                            drawState = DrawState.DRAWING;
                        }
                    }

                    @Override
                    public void onCancel() {
                    }
                });
            }
        });
    }

    public class PaintingView extends View {
        private Bitmap bitmap;
        private Canvas mCanvas;
        private Path mPath;
        private Paint mBitmapPaint;
        private float mX, mY;
        private static final float TOUCH_TOLERANCE = 4;

        public PaintingView(Context context) {
            super(context);

            mPath = new Path();
            mBitmapPaint = new Paint(Paint.DITHER_FLAG);
        }

        @Override
        public void onSizeChanged(int w, int h, int oldw, int oldh) {
            super.onSizeChanged(w, h, oldw, oldh);

            bitmap = Bitmap.createBitmap(getTypeOfClothFrame(), 0, 0, getTypeOfClothFrame().getWidth(), getTypeOfClothFrame().getHeight());
            mCanvas = new Canvas(bitmap);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            canvas.drawBitmap(bitmap, 0, 0, mBitmapPaint);
            canvas.drawPath(mPath, mPaint);
        }

        private void touchStart(float x, float y) {
            mPath.reset();
            mPath.moveTo(x, y);
            mX = x;
            mY = y;
        }

        private void touchMove(float x, float y) {
            float dx = Math.abs(x - mX);
            float dy = Math.abs(y - mY);
            if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
                mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
                mX = x;
                mY = y;
            }

            invalidate();
        }

        private void touchUp() {
            mPath.lineTo(mX, mY);
            mCanvas.drawPath(mPath, mPaint);
            mPath.reset();

            invalidate();
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            Point point = new Point((int) event.getX(), (int) event.getY());

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    onActionDown(point);
                    invalidate();
                    break;
                case MotionEvent.ACTION_MOVE:
                    onActionMove(point);
                    break;
                case MotionEvent.ACTION_UP:
                    onActionUp();
                    break;
            }

            return true;
        }

        private void onActionUp() {
            if (drawState == DrawState.DRAWING) {
                touchUp();
            }
        }

        private void onActionMove(Point point) {
            if (drawState == DrawState.DRAWING) {
                touchMove(point.x, point.y);
            }
        }

        private void onActionDown(Point point) {
            if (drawState == DrawState.DRAWING) {
                touchStart(point.x, point.y);
            } else {
                try {
                    final int sourceColor = bitmap.getPixel(point.x, point.y);
                    final int targetColor = mPaint.getColor();

                    new AsyncPaintTask(bitmap, point, sourceColor, targetColor, view)
                            .execute();
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
        }

        public Bitmap getBitmap() {
            return bitmap;
        }
    }
}
