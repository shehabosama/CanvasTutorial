package com.base.canvastutorial;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

import java.util.Random;

public class ProgressDrawable extends Drawable {
    private static final int NUM_SEGMENTS = 4;
    private int mForeground;
    private final int mBackground;
    private final Paint mPaint = new Paint();
    private final RectF mSegment = new RectF();
    int progress = 0;

    public ProgressDrawable(int fgColor, int bgColor) {
        mForeground = fgColor;
        mBackground = bgColor;
    }

    @Override
    protected boolean onLevelChange(int level) {
        invalidateSelf();
        return true;
    }

    @Override
    public void draw(Canvas canvas) {
        float level = getLevel() / 10000f;
        Rect b = getBounds();
        float gapWidth = b.height() / 2f;
        float segmentWidth = (b.width() - (NUM_SEGMENTS - 1) * gapWidth) / NUM_SEGMENTS;
        mSegment.set(0, 0, segmentWidth,
                b.height()
        );
        mPaint.setColor(mForeground);
        for (int i = 0; i < NUM_SEGMENTS; i++) {
            float loLevel = i / (float) NUM_SEGMENTS;
            float hiLevel = (i + 1) / (float) NUM_SEGMENTS;
            if (loLevel <= level && level <= hiLevel) {
                float middle = mSegment.left + NUM_SEGMENTS * segmentWidth * (level - loLevel);
                canvas.drawRect(mSegment.left, mSegment.top, middle, mSegment.bottom, mPaint);
                mPaint.setColor(mBackground);
                canvas.drawRect(middle, mSegment.top, mSegment.right, mSegment.bottom, mPaint);
            } else {
                canvas.drawRect(mSegment, mPaint);
            }
            mSegment.offset(mSegment.width() + gapWidth, 0);
        }
        invalidateSelf();
    }

    @Override
    public void setAlpha(int alpha) {
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    public void changeColorLevel(int newProgress) {
        if (newProgress>=0 && newProgress <=25){
            mForeground = Color.RED;
            mPaint.setColor(mForeground);
        }
        if (newProgress>=25 && newProgress <=50){
            mForeground = Color.RED;
            mPaint.setColor(mForeground);
        }

        if (newProgress >50 && newProgress<=75){
            mForeground = Color.YELLOW;
            mPaint.setColor(mForeground);
        }

        if (newProgress > 75&& newProgress<=100){
            mForeground = Color.GREEN;
            mPaint.setColor(mForeground);
        }

    }
}