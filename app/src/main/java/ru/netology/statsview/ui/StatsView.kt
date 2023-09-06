package ru.netology.statsview.ui

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import android.view.animation.OvershootInterpolator
import androidx.core.content.withStyledAttributes
import ru.netology.statsview.R
import ru.netology.statsview.utils.AndroidUtils
import ru.netology.statsview.utils.AndroidUtils.convertToOneHundredPercent
import kotlin.math.min
import kotlin.random.Random

class StatsView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0,
) : View(
    context,
    attributeSet,
    defStyleAttr,
    defStyleRes,
) {
    private var textSize = AndroidUtils.dp(context, 20).toFloat()
    private var lineWidth = AndroidUtils.dp(context, 5)
    private var colors = emptyList<Int>()
    private var progress = 0F
    private var valueAnimator: ValueAnimator? = null
    private var radius = 0F
    private var center = PointF()
    private var oval = RectF()
    private var fullCircleDegrees = 360F

    init {
        context.withStyledAttributes(attributeSet, R.styleable.StatsView) {
            textSize = getDimension(R.styleable.StatsView_textSize, textSize)
            lineWidth = getDimension(R.styleable.StatsView_lineWidth, lineWidth.toFloat()).toInt()
            colors = listOf(
                getColor(R.styleable.StatsView_color1, getRandomColor()),
                getColor(R.styleable.StatsView_color2, getRandomColor()),
                getColor(R.styleable.StatsView_color3, getRandomColor()),
                getColor(R.styleable.StatsView_color4, getRandomColor()),
            )
        }
    }

    var data: List<Float> = emptyList()
        set(value) {
            field = value
            update()
        }




    private var paint = Paint(
        Paint.ANTI_ALIAS_FLAG
    ).apply {
        strokeWidth = lineWidth.toFloat()
        style = Paint.Style.STROKE
        strokeJoin = Paint.Join.ROUND
        strokeCap = Paint.Cap.ROUND
    }
    private var textPaint = Paint(
        Paint.ANTI_ALIAS_FLAG
    ).apply {
        textSize = this@StatsView.textSize
        style = Paint.Style.FILL
        textAlign = Paint.Align.CENTER
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        radius = min(w, h) / 2F - lineWidth
        center = PointF(w / 2F, h / 2F)
        oval = RectF(
            center.x - radius,
            center.y - radius,
            center.x + radius,
            center.y + radius,
        )
    }

    override fun onDraw(canvas: Canvas) {
        if (data.isEmpty()) {
            return
        }

        var startAngle = -90F + progress * 360
        data.forEachIndexed { index, datum ->
            if (data.isNotEmpty()) {
                val angle =
                    (datum / data.max().times(data.count())) * fullCircleDegrees

                //val angle = datum * 360F
                paint.color = colors.getOrElse(index) { getRandomColor() }
                //canvas.drawArc(oval, startAngle, angle, false, paint)
                canvas.drawArc(oval, startAngle, angle * progress, false, paint)
                startAngle += angle
            }
        }
        val percentText = if (data.isNotEmpty()) {
            convertToOneHundredPercent(data)
        } else null
        canvas.drawText(

            //"%.2f%%".format(data.sum() * 100),
            "%.2f%%".format(percentText),
            center.x,
            center.y + textPaint.textSize / 4,
            textPaint
        )
        if (percentText == 100F) {
            paint.color = colors[0]
            canvas.drawArc(oval, startAngle, 1F, false, paint)
        }


    }
    private fun update() {
        valueAnimator?.let {
            it.removeAllListeners()
            it.cancel()
        }
        progress = 0F
        valueAnimator = ValueAnimator.ofFloat(0F, 1F).apply {
            addUpdateListener { anim ->
                progress = anim.animatedValue as Float
                invalidate()
            }
            duration = 2000
            interpolator = LinearInterpolator()
        }.also {
            it.start()
        }
    }


    private fun getRandomColor() = Random.nextInt(0xFF000000.toInt(), 0xFFFFFFFF.toInt())
}