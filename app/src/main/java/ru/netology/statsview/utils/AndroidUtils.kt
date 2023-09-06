package ru.netology.statsview.utils

import android.content.Context
import kotlin.math.ceil

object AndroidUtils {

    fun dp(context: Context, dp: Int) =
        ceil(context.resources.displayMetrics.density * dp).toInt()

    // для удобства скину функции расчета % сюда
    fun convertToOneHundredPercent(list: List<Float>): Float =
        (list.sum() / list.max().times(list.count()) * 100F)


}