package com.example.ui.city

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.example.quanghai.weather.R
import java.text.SimpleDateFormat
import java.util.*

/**
 *
 * Created by Hai on 8/29/2017.
 */
class SunAndMoonView : View {
    private var mSunRise: Long? = 0
    private var mSunSet: Long? = 0
    private val mPaint = Paint()

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initPaint()
    }

    private fun initPaint() {
        mPaint.color = Color.WHITE
        mPaint.strokeWidth = 1f
        mPaint.style = Paint.Style.STROKE
        mPaint.textSize = 40f
    }

    fun setSunRiseAndSunSet(sunrise: Long, sunset: Long) {
        mSunRise = sunrise
        mSunSet = sunset
    }

    private fun getSunRise() = mSunRise

    private fun getSunSet() = mSunSet

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawLine(0f, width.toFloat() / 2, width.toFloat(), width.toFloat() / 2, mPaint)
        val rect = RectF(50f, 50f, width.toFloat() - 50, width.toFloat() - 50)
        canvas.drawArc(rect, 180f, 180f, false, mPaint)
        drawMock(canvas)
        drawTime(canvas)
        drawCurrentTime(canvas)
    }

    private fun drawMock(canvas: Canvas) {
        mPaint.color = Color.RED
        mPaint.strokeWidth = 5f
        mPaint.style = Paint.Style.FILL_AND_STROKE
        canvas.drawCircle(50f, width.toFloat() / 2, 5f, mPaint)
        canvas.drawCircle(width - 50f, width.toFloat() / 2, 5f, mPaint)
    }

    private fun drawTime(canvas: Canvas) {
        mPaint.color = Color.WHITE
        mPaint.strokeWidth = 1f
        val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
        val timeSunRise = sdf.format(Date(getSunRise()!! * 1000))
        val timeSunSet = sdf.format(Date(getSunSet()!! * 1000))
        canvas.drawText(timeSunRise, 20f, width.toFloat() / 2 + 40, mPaint)
        canvas.drawText(timeSunSet, width - 100f, width.toFloat() / 2 + 40, mPaint)
    }

    private fun drawCurrentTime(canvas: Canvas) {
        mPaint.color = Color.RED
        mPaint.strokeWidth = 2f
        val millisecondCurrent = Date().time / 1000
        if (millisecondCurrent > (mSunRise!!) && millisecondCurrent < (mSunSet!!)) {
            val ratio: Float = (mSunSet!! - mSunRise!!).toFloat() / (millisecondCurrent - mSunRise!!).toFloat()
            val x: Float = (width - 100) / ratio
            val y = width.toFloat() / 2
            canvas.drawPoint(width.toFloat() / 2, width.toFloat() / 2, mPaint)
            canvas.drawCircle(x, y, 5f, mPaint)

            // calculator edgeB
            mPaint.color = Color.WHITE
            mPaint.strokeWidth = 1f
            val radius: Float = (width.toFloat() - 100) / 2
            val edgeA = if (x < radius) {
                radius - x
            } else {
                x - radius
            }
            val edgeB = Math.sqrt((radius * radius - edgeA * edgeA).toDouble())
            val yy: Float = y - edgeB.toFloat()
            canvas.drawLine(x, y, x, yy, mPaint)
            drawSunImage(canvas, x - 30, yy - 35)
        }
    }

    private fun drawSunImage(canvas: Canvas, left: Float, top: Float) {
        val bitmap: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.ic_sun)
        canvas.drawBitmap(bitmap, left, top, mPaint)
    }
}
