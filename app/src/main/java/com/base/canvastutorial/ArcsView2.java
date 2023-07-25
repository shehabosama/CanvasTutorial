package com.base.canvastutorial;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

public class ArcsView2 extends View
{
    Paint p;RectF rectF;
    public ArcsView2(Context context, AttributeSet attr) {
        super(context,attr);
         p = new Paint();
        p.setColor(Color.BLACK);
        rectF = new RectF(0, 20, 180, 80);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(50);
        // Setting the color of the circle
        p.setColor(Color.BLUE);

        // Draw the circle at (x,y) with radius 250
        int radius = 250;
      //  canvas.drawCircle(400, 400, radius, p);

        p.setColor(Color.YELLOW);
        p.setDither(true);                    // set the dither to true
        p.setStyle(Paint.Style.STROKE);       // set to STOKE
        p.setStrokeJoin(Paint.Join.ROUND);    // set the join to round you want
        p.setStrokeCap(Paint.Cap.ROUND);      // set the paint cap to round too
        p.setPathEffect(new CornerPathEffect(50) );   // set the path effect when they join.
        p.setAntiAlias(true);

        RectF oval = new RectF(getWidth()/2 - radius, getHeight()/2 - radius, getWidth()/2 + radius, getHeight()/2 + radius);
        //canvas.drawArc(oval, -90, 90, false, p);
        p.setColor(Color.RED);
      //  canvas.drawArc(oval, 46* (float) Math.PI, 80* (float) Math.PI, false, p);
        canvas.drawArc(oval, 140, 260, false, p);
        drawNumeral(canvas);
    }
    float fontSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,13,getResources().getDisplayMetrics());
    private int[] numbers = {1,2,3,4,5,6,7,8,9,10,11,12};
    int min = Math.min(getWidth(),getHeight());
    int padding = 50;
    float radius = min/2 -padding;
    private Rect rect = new Rect();
    private void drawNumeral(Canvas canvas) {
        p.setTextSize(fontSize);
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(1);
        for (int number : numbers){
            String tmp = String.valueOf(number);
            p.getTextBounds(tmp,0,tmp.length(),rect);
            double angle = Math.PI /6 * (number-3);
            int x = (int)(getWidth()/2 + Math.cos(angle)*180- rect.width()/2);
            int y = (int)(getHeight()/2 + Math.sin(angle)*180+ rect.height()/2);

            canvas.drawText(tmp,x,y,p);
        }
    }
}
