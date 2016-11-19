package com.rpg.southparkavatars.new_cloth;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.AsyncTask;
import android.view.View;

import java.util.LinkedList;
import java.util.Queue;

public class AsyncPaintTask extends AsyncTask<Void, Integer, Void> {
    private Bitmap bmp;
    private Point pt;
    private int replacementColor, targetColor;
    private View v;

    public AsyncPaintTask(Bitmap bm, Point p, int sc, int tc, View v) {
        this.bmp = bm;
        this.pt = p;
        this.replacementColor = tc;
        this.targetColor = sc;
        this.v = v;
    }

    @Override
    protected Void doInBackground(Void... params) {
        if (targetColor != replacementColor) {
            FloodFill(bmp, pt, targetColor, replacementColor);
        }

        return null;
    }

    public void FloodFill(Bitmap bmp, Point pt, int targetColor, int replacementColor) {
        Queue<Point> q = new LinkedList<>();
        q.add(pt);
        while (q.size() > 0) {
            Point n = q.poll();
            if (bmp.getPixel(n.x, n.y) != targetColor)
                continue;

            Point e = new Point(n.x + 1, n.y);
            while ((n.x > 0) && (bmp.getPixel(n.x, n.y) == targetColor)) {
                bmp.setPixel(n.x, n.y, replacementColor);
                if ((n.y > 0) && (bmp.getPixel(n.x, n.y - 1) == targetColor))
                    q.add(new Point(n.x, n.y - 1));
                if ((n.y < bmp.getHeight() - 1)
                        && (bmp.getPixel(n.x, n.y + 1) == targetColor))
                    q.add(new Point(n.x, n.y + 1));
                n.x--;
            }
            while ((e.x < bmp.getWidth() - 1)
                    && (bmp.getPixel(e.x, e.y) == targetColor)) {
                bmp.setPixel(e.x, e.y, replacementColor);

                if ((e.y > 0) && (bmp.getPixel(e.x, e.y - 1) == targetColor))
                    q.add(new Point(e.x, e.y - 1));
                if ((e.y < bmp.getHeight() - 1)
                        && (bmp.getPixel(e.x, e.y + 1) == targetColor))
                    q.add(new Point(e.x, e.y + 1));
                e.x++;
            }
        }
    }

    @Override
    protected void onPostExecute(Void result) {
        v.invalidate();
    }
}



