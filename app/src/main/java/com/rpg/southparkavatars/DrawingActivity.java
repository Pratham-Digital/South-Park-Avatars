package com.rpg.southparkavatars;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import petrov.kristiyan.colorpicker.ColorPicker;

public class DrawingActivity extends AppCompatActivity {
    private Integer state = 0;
    private Integer globalColor = Color.BLACK;
    private DrawingView dv;
    private Paint mPaint;
    private final Point p1 = new Point();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawing_activity);

        ImageView sablon = (ImageView) findViewById(R.id.sablon);
        ImageView drawTitle = (ImageView) findViewById(R.id.drawTitle);
        LinearLayout paintingArea = (LinearLayout) findViewById(R.id.paint);
        dv = new DrawingView(paintingArea.getContext());

        paintingArea.addView(dv);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(getGlobalColor());
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(6);

        Context context = getApplicationContext();
        CharSequence toastText = "";
        int duration = Toast.LENGTH_LONG;

        Intent intent = getIntent();
        String cloth_type = intent.getStringExtra("cloth-type");

        switch (cloth_type) {

            case "Shirt":
                toastText = "Draw a nice shirt!";
                sablon.setImageResource(R.drawable.shirt_frame);
                drawTitle.setImageResource(R.drawable.draw_shirt);
                break;
            case "Pants":
                toastText = "Draw a nice pant!";
                sablon.setImageResource(R.drawable.pants_frame);
                drawTitle.setImageResource(R.drawable.draw_pant);
                break;
            case "Hat":
                toastText = "Draw a nice hat!";
                sablon.setImageResource(R.drawable.hat_frame);
                drawTitle.setImageResource(R.drawable.draw_hat);
                break;
        }


        Toast toast = Toast.makeText(context, toastText, duration);
        toast.show();
    }


    public void setState(Integer integer) {
        this.state = integer;
    }

    public Integer getState() {
        return state;
    }


    public Integer getGlobalColor() {
        return globalColor;
    }

    public void setGlobalColor(Integer globalColor) {
        this.globalColor = globalColor;
    }

    public void setFillColor(View v) {
        ColorPicker colorPicker = new ColorPicker(DrawingActivity.this).setRoundColorButton(true);
        colorPicker.show();

        colorPicker.setOnChooseColorListener(new ColorPicker.OnChooseColorListener() {
            @Override
            public void onChooseColor(int position, int color) {
                globalColor = color;
                setGlobalColor(color);
                mPaint.setColor(getGlobalColor());
                setState(1);
            }

            @Override
            public void onCancel() {
            }
        });
    }


    public void setPenColor(View v) {
        ColorPicker colorPicker = new ColorPicker(DrawingActivity.this).setRoundColorButton(true);
        colorPicker.show();

        colorPicker.setOnChooseColorListener(new ColorPicker.OnChooseColorListener() {
            @Override
            public void onChooseColor(int position, int color) {
                globalColor = color;
                setGlobalColor(color);
                mPaint.setColor(getGlobalColor());
                if (getState() == 1)
                    setState(0);
            }

            @Override
            public void onCancel() {
            }
        });
    }


    public class DrawingView extends View {
        private Bitmap mBitmap;
        private Canvas mCanvas;
        private Path mPath;
        private Paint mBitmapPaint;
        Context context;

        public DrawingView(Context c) {
            super(c);
            context = c;
            mPath = new Path();
            mBitmapPaint = new Paint(Paint.DITHER_FLAG);
        }

        @Override
        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
            super.onSizeChanged(w, h, oldw, oldh);

            mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            mCanvas = new Canvas(mBitmap);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            canvas.drawBitmap(mBitmap, 0, 0, mBitmapPaint);
            canvas.drawPath(mPath, mPaint);
        }

        private float mX, mY;
        private static final float TOUCH_TOLERANCE = 4;

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
            mCanvas.drawPath(mPath, mPaint);
            // kill this so we don't double draw
            mPath.reset();
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            float x = event.getX();
            float y = event.getY();

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    if (getState() == 0) {
                        touch_start(x, y);
                        invalidate();
                    } else {
                        p1.x = (int) x;
                        p1.y = (int) y;
                        final int sourceColor = mBitmap.getPixel((int) x, (int) y);
                        final int targetColor = mPaint.getColor();
                        new TheTask(mBitmap, p1, sourceColor, targetColor).execute();
                        invalidate();
                    }
                    break;
                case MotionEvent.ACTION_MOVE:
                    if (getState() == 0) {
                        touch_move(x, y);
                        invalidate();
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    if (getState() == 0) {
                        touch_up();
                        invalidate();
                    }
                    break;
            }
            return true;
        }


        class TheTask extends AsyncTask<Void, Integer, Void> {
            Bitmap bmp;
            Point pt;
            int replacementColor, targetColor;

            public TheTask(Bitmap bm, Point p, int sc, int tc) {
                this.bmp = bm;
                this.pt = p;
                this.replacementColor = tc;
                this.targetColor = sc;
            }

            @Override
            protected Void doInBackground(Void... params) {
                QueueLinearFloodFiller f = new QueueLinearFloodFiller(bmp, targetColor, replacementColor);
                f.floodFill(pt.x, pt.y);
                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                invalidate();
            }
        }
    }
}
