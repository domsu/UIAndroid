package com.uiandroid.examples.sample.viewlayers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.uiandroid.examples.R;

public class UnoptimizedImageView extends View {

    private Bitmap bitmap;

    public UnoptimizedImageView(Context context, AttributeSet attrs) {
        super(context, attrs);

        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.face_aquamarine);
    }


    @Override
    protected void onDraw(Canvas c) {
        super.onDraw(c);

        drawBitmapMesh(c);
    }

    private void drawBitmapMesh(Canvas c) {
        Rect rect = new Rect();
        this.getDrawingRect(rect);

        float[] verts = new float[8];
        verts[0] = rect.left - 50;
        verts[1] = rect.top + 30;

        verts[2] = rect.right + 20;
        verts[3] = rect.top + 40;

        verts[4] = rect.left;
        verts[5] = rect.bottom + 79;

        verts[6] = rect.right;
        verts[7] = rect.bottom - 40;

        int[] colors = new int[4];
        colors[0] = 0xFFFF0000;
        colors[1] = 0xFF00FF00;
        colors[2] = 0xFF0000FF;
        colors[3] = 0xFF000FF0;

        c.drawBitmapMesh(bitmap, 1, 1, verts, 0, colors, 0, null);
    }
}
