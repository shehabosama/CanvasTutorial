package com.base.canvastutorial

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.AttributeSet
import android.widget.ProgressBar

class SignalStrengthView (context: Context, attrs: AttributeSet?) : ProgressBar(context, attrs) {
    private val segmentCount: Int
    private val segmentColors: IntArray


    init {

        // Load custom attributes from XML
        val a = context.obtainStyledAttributes(attrs, R.styleable.SignalStrengthView)
        segmentCount = a.getInt(R.styleable.SignalStrengthView_segmentCount, 1)
        segmentColors = IntArray(segmentCount)
        for (i in 0 until segmentCount) {
            if (i == 0){
                segmentColors[i] = a.getColor(R.styleable.SignalStrengthView_segmentColor_1, Color.GREEN)
            }else if (i==1){
                segmentColors[i] = a.getColor(R.styleable.SignalStrengthView_segmentColor_2, Color.GREEN)
            }else if (i==2){
                segmentColors[i] = a.getColor(R.styleable.SignalStrengthView_segmentColor_3, Color.GREEN)
            }

        }
        a.recycle()
        // Set up progress bar properties
        max = segmentCount
        progressDrawable = context.getDrawable(R.drawable.signal_strength_gradient)
        progressTintList = ColorStateList.valueOf(Color.TRANSPARENT)
        isIndeterminate = false
        progress = 0
    }
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val segmentHeight = height / segmentCount
        for (i in 0 until segmentCount) {
            val segmentTop = i * segmentHeight
            val segmentBottom = (i + 1) * segmentHeight
            val segmentColor = segmentColors[i]
            val segmentDrawable = ColorDrawable(segmentColor)
            segmentDrawable.setBounds(0, segmentTop, width, segmentBottom)
            segmentDrawable.draw(canvas)
        }
    }
    fun setSignalStrength(strength: Int) {
        progress = strength
    }
}