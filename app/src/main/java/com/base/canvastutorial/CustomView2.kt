package com.base.canvastutorial

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import androidx.core.graphics.get

class CustomView2 @JvmOverloads constructor(private val context:Context, attr: AttributeSet) : View(context,attr) {

    init {
        setBackgroundResource(R.color.white)
    }

    private var queen_bm: Bitmap?=null
    var red_paintPrush_fill:Paint?=null
    var blue_paintPrush_fill:Paint? = null
    var green_paintPrush_fill:Paint? = null
    var red_paintPrush_fill_stroks:Paint?=null
    var blue_paintPrush_fill_stroks:Paint? = null
    var green_paintPrush_fill_stroks:Paint? = null
    var queen_x:Float? = null
    var queen_y:Float? = null
    var circl_x:Float? = null
    var circl_y:Float? = null
    var x_dir:Int? = null
    var y_dir:Int? = null
    var chessBackground_bm:Bitmap?= null
    var square:Path? = null
    init {

        queen_x =650f
        queen_y =130f
        circl_x = 650f
        circl_y = 130f
        x_dir = 1 //pixel
        y_dir = 1 // pixel

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
    }
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        chessBackground_bm = BitmapFactory.decodeResource(resources,R.drawable.chess_background)

        canvas?.drawBitmap(chessBackground_bm!!,0f,0f,null)
        drawSquare(130f,130f,650f,650f,canvas)

        queen_bm = BitmapFactory.decodeResource(resources,R.drawable.queen)

        // to get the height and width of the image that you draw or that you amimate it
      //  val option:BitmapFactory.Options = BitmapFactory.Options();
     //   option.inJustDecodeBounds = true
      //  BitmapFactory.decodeResource(resources,R.drawable.indecator,option)
      //  indicatorHeight = option.outHeight
      //  indicatorWidth = option.outWidth

        motionQueen(20)
        motionCircle(10)
        canvas?.drawBitmap(getResizedBitmap(queen_bm!!,200,300)!!,queen_x!!,queen_y!! ,null)
        canvas?.drawCircle(circl_x!!,circl_y!!,50f  ,green_paintPrush_fill!!)
        invalidate()
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
    fun drawSquare(x1:Float,y1:Float,x2:Float,y2:Float,canvas: Canvas?){
        square = Path();
        square?.apply{
            moveTo(x1,y1)
            lineTo(x2,y1)
            moveTo(x2,y1)
            lineTo(x2,y2)
            moveTo(x2,y2)
            lineTo(x1,y2)
            moveTo(x1,y2)
            lineTo(x1,y1)
        }
        canvas?.drawPath(square!!,green_paintPrush_fill_stroks!!)
    }
    fun motionQueen(speed:Int){
        if((queen_y!! == 130f) && (queen_x!! < 650f)){
            queen_x = queen_x!! + speed
        }


        if((queen_y!! < 650f) && (queen_x!! == 650f)){
            queen_y = queen_y!! + speed
        }

        if((queen_y!! == 650f) && (queen_x!! > 130f)){
            queen_x = queen_x!! - speed
        }

        if((queen_y!! > 130f) && (queen_x!! == 130f)){
            queen_y = queen_y!! - speed
        }
    }
    fun motionCircle(speed:Int){
        if((circl_y!! == 130f) && (circl_x!! < 650f)){
            circl_x = circl_x!! + speed
        }

        if((circl_y!! < 650f) && (circl_x!! == 650f)){
            circl_y = circl_y!! + speed
        }

        if((circl_y!! == 650f) && (circl_x!! > 130f)){
            circl_x = circl_x!! - speed
        }

        if((circl_y!! > 130f) && (circl_x!! == 130f)){
            circl_y = circl_y!! - speed
        }
    }
}