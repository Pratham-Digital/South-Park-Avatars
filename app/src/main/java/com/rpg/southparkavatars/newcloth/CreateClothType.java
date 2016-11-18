package com.rpg.southparkavatars.newcloth;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.rpg.southparkavatars.R;
import com.rpg.southparkavatars.save.Save;
import com.rpg.southparkavatars.save.clothingType;

import java.io.File;

import petrov.kristiyan.colorpicker.ColorPicker;


public class CreateClothType {
    private static Integer globalColor = Color.BLACK;

    private Paint mPaint;
    private int penSize = 6;
    private static PaintCloth drawingView;
    private Context context;
    private Activity activity;
    private File directory;
    private File root;
    private Bitmap typeOfClothFrame;
    private static String typeOfCloth;
    private int height;
    private int width;
    private String drawState;
    private LinearLayout paintingArea;
    private View v;


    public CreateClothType(Context context, Activity activity, View v) {
        this.context = context;
        this.activity = activity;
        this.v = v;
    }

    public Paint getmPaint() {
        return mPaint;
    }

    public void onStart() {

        setDrawState(PaintingType.valueOf("DRAWING").toString().toLowerCase());
        ImageView drawTitle = (ImageView) activity.findViewById(R.id.drawTitle);
        paintingArea = (LinearLayout) activity.findViewById(R.id.paint);
        ViewGroup.LayoutParams params = paintingArea.getLayoutParams();
        Resources res = activity.getResources();
        drawingView = new PaintCloth(paintingArea.getContext());

        paintingArea.addView(drawingView);
        setDv(drawingView);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(getGlobalColor());
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(penSize);

        Context context = activity.getApplicationContext();

        width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 260, activity.getResources().getDisplayMetrics());

        Intent intent = activity.getIntent();
        String clothType = intent.getStringExtra("clothType");

        root = new File(context.getFilesDir() + File.separator + "clothing");
        setDirectory(root);

        if (!root.exists()) {
            root.mkdir();
        }

        switch (clothType) {
            case "Shirt":
                height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 190, activity.getResources().getDisplayMetrics());
                params.height = height;

                typeOfClothFrame = BitmapFactory.decodeResource(res, R.drawable.shirt_frame).copy(Bitmap.Config.ARGB_8888, true);
                typeOfClothFrame = Bitmap.createScaledBitmap(typeOfClothFrame, width, height, false);
                setTypeOfClothFrame(typeOfClothFrame);

                typeOfCloth = clothingType.valueOf("SHIRT").toString().toLowerCase();
                setTypeOfCloth(typeOfCloth);

                drawTitle.setImageResource(R.drawable.draw_shirt);

                directory = new File(root + File.separator + "shirt");
                setDirectory(directory);

                if (!directory.exists()) {
                    directory.mkdir();
                }
                break;

            case "Pants":
                height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 80, activity.getResources().getDisplayMetrics());
                params.height = height;

                typeOfClothFrame = BitmapFactory.decodeResource(res, R.drawable.pants_frame).copy(Bitmap.Config.ARGB_8888, true);
                typeOfClothFrame = Bitmap.createScaledBitmap(typeOfClothFrame, width, height, false);
                setTypeOfClothFrame(typeOfClothFrame);

                drawTitle.setImageResource(R.drawable.draw_pant);

                typeOfCloth = clothingType.valueOf("PANTS").toString().toLowerCase();
                setTypeOfCloth(typeOfCloth);

                directory = new File(root + File.separator + "pants");

                setDirectory(directory);

                if (!directory.exists()) {
                    directory.mkdir();
                }
                break;

            case "Hat":
                height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 170, activity.getResources().getDisplayMetrics());
                params.height = height;


                typeOfClothFrame = BitmapFactory.decodeResource(res, R.drawable.hat_frame).copy(Bitmap.Config.ARGB_8888, true);
                typeOfClothFrame = Bitmap.createScaledBitmap(typeOfClothFrame, width, height, false);
                setTypeOfClothFrame(typeOfClothFrame);

                drawTitle.setImageResource(R.drawable.draw_hat);

                typeOfCloth = clothingType.valueOf("HAT").toString().toLowerCase();
                setTypeOfCloth(typeOfCloth);

                directory = new File(root + File.separator + "hat");
                setDirectory(directory);
                if (!directory.exists()) {
                    directory.mkdir();
                }
                break;
        }

        setFillColor();
        setPenColor();
    }


    public void persistCloth(){
        Save save = new Save(context,activity, v);
        save.saveClothing();
    }


    public String getDrawState() {
        return drawState;
    }

    public void setDrawState(String drawState) {
        this.drawState = drawState;
    }

    public Bitmap getTypeOfClothFrame() {
        return typeOfClothFrame;
    }

    public void setTypeOfClothFrame(Bitmap typeOfClothFrame) {
        this.typeOfClothFrame = typeOfClothFrame;
    }

    public Integer getGlobalColor() {
        return globalColor;
    }

    public void setGlobalColor(Integer globalColor) {
        this.globalColor = globalColor;
    }


    public String getTypeOfCloth() {
        return typeOfCloth;
    }

    public void setTypeOfCloth(String typeOfCloth) {
        this.typeOfCloth = typeOfCloth;
    }


    public void setDirectory(File directory) {
        this.directory = directory;
    }

    public File getDirectory() {
        return directory;
    }

    public PaintCloth getDv() {
        return drawingView;
    }

    public void setDv(PaintCloth dv) {
        this.drawingView = dv;
    }

    public void setFillColor() {
        Button setColor = (Button) v.findViewById(R.id.fill);
        setColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorPicker colorPicker = new ColorPicker(context).setRoundColorButton(true);
                colorPicker.show();

                colorPicker.setOnChooseColorListener(new ColorPicker.OnChooseColorListener() {
                    @Override
                    public void onChooseColor(int position, int color) {
                        globalColor = color;
                        setGlobalColor(color);
                        mPaint.setColor(getGlobalColor());
                        setDrawState(PaintingType.valueOf("COLORING").toString().toLowerCase());
                    }


                    @Override
                    public void onCancel() {
                    }
                });
            }
        });
    }

    public void setPenColor() {
        Button setPen = (Button) v.findViewById(R.id.pen);
        setPen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorPicker colorPicker = new ColorPicker(context).setRoundColorButton(true);
                colorPicker.show();

                colorPicker.setOnChooseColorListener(new ColorPicker.OnChooseColorListener() {
                    @Override
                    public void onChooseColor(int position, int color) {
                        globalColor = color;
                        setGlobalColor(color);
                        mPaint.setColor(getGlobalColor());
                        if (getDrawState().equals("coloring"))
                            setDrawState(PaintingType.valueOf("DRAWING").toString().toLowerCase());
                    }

                    @Override
                    public void onCancel() {
                    }
                });
            }
        });
    }

    public class PaintCloth extends View {

        private Bitmap imgBitmap;
        private Canvas mCanvas;
        private Path mPath;
        private Paint mBitmapPaint;
        private Context context;
        private final Point p1 = new Point();
        private float mX, mY;
        private static final float TOUCH_TOLERANCE = 4;

        public PaintCloth(Context c) {
            super(c);
            context = c;

            mPath = new Path();
            mBitmapPaint = new Paint(Paint.DITHER_FLAG);

        }

        @Override
        public void onSizeChanged(int w, int h, int oldw, int oldh) {
            super.onSizeChanged(w, h, oldw, oldh);
            imgBitmap = Bitmap.createBitmap(getTypeOfClothFrame(),0,0,getTypeOfClothFrame().getWidth(),getTypeOfClothFrame().getHeight());
            mCanvas = new Canvas(imgBitmap);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            //Rect imageRect = new Rect(0,0, getWidth(), getHeight());
            canvas.drawBitmap(imgBitmap, 0, 0, mBitmapPaint);

            canvas.drawPath(mPath, getmPaint());
        }

        private void touch_start(float x, float y) {
            mPath.reset();
            mPath.moveTo(x, y);
            mX = x;
            mY = y;
        }

        private void touch_move(float x, float y) {
            float dx = Math.abs(x - mX);
            float dy = Math.abs(y - mY);
            if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
                mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
                mX = x;
                mY = y;
            }
        }

        private void touch_up() {
            mPath.lineTo(mX, mY);
            // commit the path to our offscreen
            mCanvas.drawPath(mPath, getmPaint());
            // kill this so we don't double draw
            mPath.reset();
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            float x = event.getX();
            float y = event.getY();

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    if (getDrawState().equals("drawing")) {
                        touch_start(x, y);
                        invalidate();
                    } else {
                        p1.x = (int) x;
                        p1.y = (int) y;
                        try {
                            final int sourceColor = imgBitmap.getPixel((int) x, (int) y);
                            final int targetColor = getmPaint().getColor();
                            new AsyncPainting(imgBitmap, p1, sourceColor, targetColor, v).execute();
                        }catch (Throwable e){
                            e.printStackTrace();
                        }
                        invalidate();
                    }
                    break;
                case MotionEvent.ACTION_MOVE:
                    if (getDrawState().equals("drawing")) {
                        touch_move(x, y);
                        invalidate();
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    if (getDrawState().equals("drawing")) {
                        touch_up();
                        invalidate();
                    }
                    break;
            }
            return true;
        }

        public Bitmap getImgBitmap() {
            return imgBitmap;
        }
    }
}
