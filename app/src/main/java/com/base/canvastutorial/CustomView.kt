package com.base.canvastutorial

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import android.view.animation.Animation
import android.view.animation.RotateAnimation


class CustomView @JvmOverloads constructor(private val context:Context,attr: AttributeSet) : View(context,attr) {

    init {
        setBackgroundResource(R.color.white)
    }
    var red_paintPrush_fill:Paint?=null
    var blue_paintPrush_fill:Paint? = null
    var green_paintPrush_fill:Paint? = null
    var red_paintPrush_fill_stroks:Paint?=null
    var blue_paintPrush_fill_stroks:Paint? = null
    var green_paintPrush_fill_stroks:Paint? = null
    var indecator_x:Float? = null
    var indecator_y:Float? = null
    var x_dir:Int? = null
    var y_dir:Int? = null
    var indecator_bm:Bitmap?= null
    var tringle:Path? = null
    var indicatorWidth = 0
    var indicatorHeight = 0
    init {
        indecator_x =50f
        indecator_y =300f

        x_dir = 1 //pixel
        y_dir = 1 // pixel
    }
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        red_paintPrush_fill = Paint()
        red_paintPrush_fill?.apply {
            color = Color.RED
            style = Paint.Style.FILL
        }

        blue_paintPrush_fill = Paint()
        blue_paintPrush_fill?.apply {
            color = Color.BLUE
            style = Paint.Style.FILL
        }

        green_paintPrush_fill = Paint()
        green_paintPrush_fill?.apply {
            color = Color.GREEN
            style = Paint.Style.FILL
        }



        red_paintPrush_fill_stroks = Paint()
        red_paintPrush_fill_stroks?.apply {
            color = Color.RED
            style = Paint.Style.STROKE
            strokeWidth = 10F
        }

        blue_paintPrush_fill_stroks = Paint()
        blue_paintPrush_fill_stroks?.apply {
            color = Color.BLUE
            style = Paint.Style.STROKE
            strokeWidth = 10F
        }

        green_paintPrush_fill_stroks = Paint()
        green_paintPrush_fill_stroks?.apply {
            color = Color.GREEN
            style = Paint.Style.STROKE
            strokeWidth = 10F
        }

        val rec001:Rect = Rect()
        rec001.set(210,125,300,175)
        canvas?.drawRect(rec001,green_paintPrush_fill_stroks!!)
        val redRect:Rect = Rect()
        redRect.set(210,175,300,300)
        canvas?.drawRect(redRect,red_paintPrush_fill!!)

        canvas?.drawCircle(400f,400f,70f,blue_paintPrush_fill_stroks!!)
        canvas?.drawCircle(400f,400f,20f,green_paintPrush_fill!!)
        canvas?.drawCircle(400f,400f,10f,red_paintPrush_fill!!)

        tringle = Path();
        tringle?.apply{
            moveTo(400f,400f)
            lineTo(600f,600f)
            moveTo(600f,600f)
            lineTo(200f,600f)
            moveTo(200f,600f)
            lineTo(400f,400f)
        }
        canvas?.drawPath(tringle!!,red_paintPrush_fill_stroks!!)
        canvas?.drawCircle(600f,600f,70f,blue_paintPrush_fill_stroks!!)
        canvas?.drawCircle(600f,600f,20f,green_paintPrush_fill!!)
        canvas?.drawCircle(600f,600f,10f,red_paintPrush_fill!!)

        canvas?.drawCircle(200f,600f,70f,blue_paintPrush_fill_stroks!!)
        canvas?.drawCircle(200f,600f,20f,green_paintPrush_fill!!)
        canvas?.drawCircle(200f,600f,10f,red_paintPrush_fill!!)

        canvas?.drawCircle(400f,400f,70f,blue_paintPrush_fill_stroks!!)
        canvas?.drawCircle(400f,400f,20f,green_paintPrush_fill!!)
        canvas?.drawCircle(400f,400f,10f,red_paintPrush_fill!!)
        indecator_bm = BitmapFactory.decodeResource(resources,R.drawable.indecator)

        // to get the height and width of the image that you draw or that you amimate it
        val option:BitmapFactory.Options = BitmapFactory.Options();
        option.inJustDecodeBounds = true
        BitmapFactory.decodeResource(resources,R.drawable.indecator,option)
        indicatorHeight = option.outHeight
        indicatorWidth = option.outWidth

        if(indecator_x!! >= width- indicatorWidth){
            x_dir=-1
        }

        if (indecator_x!! <=0){
            x_dir = 1
        }

        if(indecator_y!! >= height - indicatorHeight ){
            y_dir=-1
        }

        if (indecator_y!! <=0){
            y_dir = 1
        }

        indecator_x =indecator_x!! + x_dir?.toFloat()!!
        indecator_y =indecator_y!! + y_dir?.toFloat()!!
        canvas?.drawBitmap(getResizedBitmap(indecator_bm!!,200,300)!!,indecator_x!!,indecator_y!!,null)

         val cx:Int = getWidth()/2; // x-coordinate of center of the screen
        val cy = getHeight()/2; // y-coordinate of the center of the screen

        // Starts the animation to rotate the circle.
        if (anim == null)
            createAnimation(canvas!!)

     //   canvas?.drawCircle(cx.toFloat(), cy.toFloat(), 150f, mPaint); // drawing the circle.
        invalidate()

    }
    var mPaint = Paint()
    private var anim: Animation? = null
    init{
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeWidth = 10f
        mPaint.color = Color.RED
    }
    private fun createAnimation(canvas: Canvas) {
        anim = RotateAnimation(0f, 360f, (width / 2).toFloat(), (height / 2).toFloat())
        anim?.setRepeatMode(Animation.RESTART)
        anim?.setRepeatCount(Animation.INFINITE)
        anim?.setDuration(10000L)
        startAnimation(anim)
    }

    fun getResizedBitmap(bm: Bitmap, newWidth: Int, newHeight: Int): Bitmap? {
        val width = bm.width
        val height = bm.height
        val scaleWidth = newWidth.toFloat() / width
        val scaleHeight = newHeight.toFloat() / height
        // CREATE A MATRIX FOR THE MANIPULATION
        val matrix = Matrix()
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight)

        // "RECREATE" THE NEW BITMAP
        val resizedBitmap = Bitmap.createBitmap(
            bm, 0, 0, width, height, matrix, false
        )
        bm.recycle()
        return resizedBitmap
    }
}