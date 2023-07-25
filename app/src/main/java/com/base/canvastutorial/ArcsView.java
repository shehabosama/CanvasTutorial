package com.base.canvastutorial;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class ArcsView extends View
{
    Paint scalePaint = null;
    public ArcsView(Context context, AttributeSet attr) {
        super(context,attr);
        scalePaint = new Paint();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        scalePaint.setStyle(Paint.Style.STROKE);
        scalePaint.setStrokeWidth(25);
        scalePaint.setColor(Color.RED);
        canvas.save();
        float cx = getWidth() / 2f;
        float cy = getHeight() / 2f;

        float scaleMarkSize = getResources().getDisplayMetrics().density * 16; // 16dp
        float radius = Math.min(getWidth(), getHeight()) / 2;

        for (int i = 0; i < 360; i += 45) {
            float angle = (float) Math.toRadians(i); // Need to convert to radians first

            float startX = (float) (cx + radius * Math.sin(angle));
            float startY = (float) (cy - radius * Math.cos(angle));

            float stopX = (float) (cx + (radius - scaleMarkSize) * Math.sin(angle));
            float stopY = (float) (cy - (radius - scaleMarkSize) * Math.cos(angle));

            canvas.drawLine(startX, startY, stopX, stopY, scalePaint);
        }
        canvas.restore();
    }
}
