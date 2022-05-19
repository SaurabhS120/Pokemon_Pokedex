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
    var progress: Int
    private val outlineThickness = 2f

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.CustomProgressBar,
            0, 0
        ).apply {
            progress = getInt(R.styleable.CustomProgressBar_progress, 0)
        }
    }

    val paint = Paint()
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val progress = with(this.progress) { if (this > 100) 100 else this }
        paint.style = Paint.Style.STROKE
        paint.color = Color.LTGRAY
        paint.strokeWidth = outlineThickness
        val left = outlineThickness
        val top = outlineThickness
        val right = width - outlineThickness
        val bottom = height - outlineThickness
        canvas.drawRect(left, top, right, bottom, paint)
        paint.color = if (progress > 75) context.getColor(R.color.progress_green)
        else if (progress > 50) context.getColor(R.color.progress_orange)
        else context.getColor(R.color.progress_red)
        val lineHeight = (width / 30).toFloat()
        paint.strokeWidth = lineHeight
        val startX: Float = lineHeight - outlineThickness
        val startY: Float = (height / 2).toFloat()
        val stopX: Float = (width * progress / 100 - lineHeight - outlineThickness)
        val stopY: Float = (height / 2).toFloat()
        canvas.drawLine(startX, startY, stopX, stopY, paint)
    }

}