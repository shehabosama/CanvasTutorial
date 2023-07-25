package com.base.canvastutorial

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.withStyledAttributes


class MainActivity : AppCompatActivity() {
    var mSegmentedProgressBar: SegmentedProgressBar? = null
    var arrayList = ArrayList<Int>()
    @JvmField
    var progress = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       setContentView(R.layout.activity_main)

//        val linearLayout = findViewById<LinearLayout>(R.id.linearLayout)
//        val peterProgress = SignalStrengthView(this,null,android.R.attr.progressBarStyleSmall)
//        peterProgress.setSignalStrength(10)
//        linearLayout.addView(peterProgress)
//
//        val peterProgress = findViewById<SignalStrengthView>(R.id.speedT)
//        peterProgress.setSignalStrength(50)


//         setContentView(linearLayout)
//        val horizontalProgressBar = findViewById<ProgressBar>(R.id.horizontal_progress_bar)
//        val fillColor = ContextCompat.getColor(this, R.color.blue)
//        val emptyColor = ContextCompat.getColor(this, R.color.green)
//        val separatorColor = ContextCompat.getColor(this, com.google.android.material.R.color.mtrl_btn_transparent_bg_color)
//
//        val progressDrawable = SegmentedProgressDrawable(20, fillColor, emptyColor, separatorColor)
//        horizontalProgressBar.setProgressDrawable(progressDrawable)
//        horizontalProgressBar.setProgress(60)



//         mSegmentedProgressBar = findViewById(R.id.segmented_pb_1)
//        arrayList.add(progress)
//
//        object : CountDownTimer(200000, 2000) {
//            override fun onTick(millisUntilFinished: Long) {
//                if (progress == 0) {
//                    arrayList.add(progress)
//                } else {
//                    arrayList.add(progress)
//                }
//                progress = progress + 1
//                mSegmentedProgressBar?.setEnabledDivisions(arrayList)
//            }
//
//            override fun onFinish() {}
//        }.start()
//

        val ll = LinearLayout(this)
        ll.orientation = LinearLayout.VERTICAL

        val pb = findViewById<ProgressBar>(R.id.progress_slots)

       // val pb = ProgressBar(this, null, android.R.attr.progressBarStyleHorizontal)
//        val d: Drawable = ProgressDrawable(Color.GREEN, Color.GRAY)
        val d: Drawable = ProgressDrawable(Color.GREEN, Color.GRAY)
              // val peterProgress = SignalStrengthView(this,null,android.R.attr.progressBarStyleSmall)

        pb.progressDrawable = d
        pb.setPadding(20, 100, 20, 0)
       // ll.addView(pb)

        val l: SeekBar.OnSeekBarChangeListener = object : SeekBar.OnSeekBarChangeListener {
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                val newProgress = pb.max * progress / seekBar.max
                Log.d("TAG", "onProgressChanged $progress")
                pb.progress = newProgress
                (d as ProgressDrawable).changeColorLevel(newProgress)
            }
        }


            val sb = findViewById<SeekBar>(R.id.seekBar)
            sb.max = 110
            sb.setOnSeekBarChangeListener(l)
            sb.setPadding(20, 20, 20, 0)
           // ll.addView(sb)


       // setContentView(ll)
    }

}