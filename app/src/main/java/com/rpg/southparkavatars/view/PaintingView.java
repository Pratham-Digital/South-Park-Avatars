package com.rpg.southparkavatars.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.rpg.southparkavatars.ClothingTypePickerActivity;
import com.rpg.southparkavatars.R;
import com.rpg.southparkavatars.character.clothing.Clothing;
import com.rpg.southparkavatars.new_clothing.AsyncPaintTask;
import com.rpg.southparkavatars.new_clothing.DrawState;
import com.rpg.southparkavatars.observer.CustomClothingEventArgs;
import com.rpg.southparkavatars.observer.CustomClothingObserver;

public class PaintingView extends View implements CustomClothingObserver {
    private static final float TOUCH_TOLERANCE = 4;
    private static int globalColor = Color.BLACK;

    private Bitmap bitmap;
    private Canvas mCanvas;
    private Path mPath;
    private Paint mBitmapPaint;
    private Paint mPaint;
    private float mX, mY;
    private DrawState drawState = DrawState.DRAWING;

    private Context context;
    private Activity activity;
    private Clothing clothingType;
    private String typeName;
    private Bitmap typeOfClothFrame;
    private View view;

    public PaintingView(Clothing clothingType, Context context, Activity activity, View view) {
        super(context);

        this.context = context;
        this.activity = activity;
        this.clothingType = clothingType;
        this.view = view;

        mPath = new Path();
        mBitmapPaint = new Paint(Paint.DITHER_FLAG);

        ImageView drawTitle = (ImageView) activity.findViewById(R.id.drawTitle);
        LinearLayout paintingArea = (LinearLayout) activity.findViewById(R.id.paint);
        ViewGroup.LayoutParams params = paintingArea.getLayoutParams();
        paintingArea.addView(this);

        inferTypeName();
        initPaint();
        drawImages(drawTitle, params);
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

    @Override
    public void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        bitmap = Bitmap.createBitmap(typeOfClothFrame, 0, 0, typeOfClothFrame.getWidth(), typeOfClothFrame.getHeight());
        mCanvas = new Canvas(bitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.TRANSPARENT);
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

    @Override
    public void update(CustomClothingEventArgs eventArgs) {
        drawState = eventArgs.getDrawState();
        globalColor = eventArgs.getGlobalColor();

        mPaint.setColor(globalColor);
    }
}
