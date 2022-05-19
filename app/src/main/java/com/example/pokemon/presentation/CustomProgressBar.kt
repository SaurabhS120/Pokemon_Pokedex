package com.example.pokemon.presentation

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Build
import android.util.AttributeSet
import android.view.View
import androidx.annotation.RequiresApi
import com.example.pokemon.R

class CustomProgressBar(context: Context, attrs: AttributeSet) : View(context, attrs) {
    var progress:Int
    val outlineThickness =2f
    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.CustomProgressBar,
            0, 0
        ).apply {
            progress = getInt(R.styleable.CustomProgressBar_progress,100)
        }
    }
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val progress = with(this.progress){if (this>100)100 else this}
        val paint = Paint()
        paint.style = Paint.Style.STROKE
        paint.color = Color.LTGRAY
        paint.strokeWidth=outlineThickness
        val left =outlineThickness
        val top=outlineThickness
        val right=canvas.width-outlineThickness
        val bottom=canvas.height-outlineThickness
        canvas.drawRect(left, top, right, bottom, paint)
        paint.color = if (progress>75)context.getColor(R.color.progress_greeen)
        else if (progress>50)context.getColor(R.color.progress_orange)
        else context.getColor(R.color.progress_red)
        val lineHeight =  (canvas?.width/30).toFloat()
        paint.strokeWidth = lineHeight
        val startX:Float= lineHeight-outlineThickness
        val startY:Float=(canvas.height/2).toFloat()
        val stopX:Float=(canvas.width*progress/100-lineHeight-outlineThickness).toFloat()
        val stopY:Float=(canvas.height/2).toFloat()
        canvas.drawLine(startX,startY,stopX,stopY, paint)
    }

}