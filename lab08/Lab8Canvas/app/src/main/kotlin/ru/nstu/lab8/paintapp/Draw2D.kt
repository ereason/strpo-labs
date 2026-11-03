package ru.nstu.lab8.paintapp

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.graphics.Typeface
import android.view.View

class Draw2D(context: Context) : View(context) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val balloon = BitmapFactory.decodeResource(
        resources,
        R.drawable.hot_air_balloon,
    )

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.style = Paint.Style.FILL
        paint.color = Color.rgb(244, 222, 210)
        canvas.drawPaint(paint)

        val width = canvas.width.toFloat()
        val height = canvas.height.toFloat()
        val unit = minOf(width, height)

        paint.color = Color.rgb(240, 155, 87)
        canvas.drawCircle(width * 0.78f, height * 0.2f, unit * 0.09f, paint)

        paint.color = Color.rgb(112, 88, 130)
        val backMountain = Path().apply {
            moveTo(0f, height * 0.7f)
            lineTo(width * 0.3f, height * 0.38f)
            lineTo(width * 0.58f, height * 0.7f)
            close()
        }
        canvas.drawPath(backMountain, paint)

        paint.color = Color.rgb(61, 66, 102)
        val frontMountain = Path().apply {
            moveTo(width * 0.28f, height * 0.72f)
            lineTo(width * 0.64f, height * 0.32f)
            lineTo(width, height * 0.72f)
            close()
        }
        canvas.drawPath(frontMountain, paint)

        paint.color = Color.rgb(55, 91, 93)
        canvas.drawRect(0f, height * 0.7f, width, height, paint)

        paint.color = Color.rgb(255, 247, 232)
        val cloudY = height * 0.23f
        canvas.drawCircle(width * 0.18f, cloudY, unit * 0.035f, paint)
        canvas.drawCircle(width * 0.23f, cloudY - unit * 0.02f, unit * 0.05f, paint)
        canvas.drawCircle(width * 0.29f, cloudY, unit * 0.038f, paint)

        paint.style = Paint.Style.STROKE
        paint.strokeWidth = unit * 0.008f
        paint.color = Color.rgb(23, 33, 60)
        canvas.drawLine(0f, height * 0.7f, width, height * 0.7f, paint)

        paint.style = Paint.Style.FILL
        paint.typeface = Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD)
        paint.textSize = unit * 0.055f
        paint.color = Color.rgb(23, 33, 60)
        canvas.drawText("Рассвет в горах", width * 0.06f, height * 0.09f, paint)

        val labelX = width * 0.52f
        val labelY = height * 0.18f
        paint.typeface = Typeface.create(Typeface.SANS_SERIF, Typeface.NORMAL)
        paint.textSize = unit * 0.033f
        canvas.save()
        canvas.rotate(-18f, labelX, labelY)
        canvas.drawText("Воздушный шар", labelX, labelY, paint)
        canvas.restore()

        val balloonWidth = unit * 0.2f
        val balloonHeight = balloonWidth * balloon.height / balloon.width
        val balloonRect = RectF(
            width * 0.13f,
            height * 0.35f,
            width * 0.13f + balloonWidth,
            height * 0.35f + balloonHeight,
        )
        canvas.drawBitmap(balloon, null, balloonRect, paint)
    }
}
