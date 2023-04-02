package com.android.core.platform

import android.content.Context
import android.content.res.Configuration.ORIENTATION_PORTRAIT
import android.content.res.Resources
import android.view.View
import kotlin.math.max
import kotlin.math.min

class AspectRatioHandler {

    fun getDimensionsThreeToFour(
        context: Context,
        totalLandScapeItems: Int,
        totalPortraitItems: Int
    ): Pair<Double, Double> {
        val orientation = context.resources.configuration.orientation
        val totalItems =
            if (orientation == ORIENTATION_PORTRAIT) totalPortraitItems else totalLandScapeItems
        val widthPercentage = (TOTAL_PERCENTAGE / totalItems).toDouble()
        val width =
            if (orientation == ORIENTATION_PORTRAIT) {
                getScreenWidthInPortrait()
            } else {
                getScreenWidthInLandscape()
            }
        val requiredWidth = (width * widthPercentage) / TOTAL_PERCENTAGE
        val requiredHeight = (requiredWidth / (ASPECT_RATIO_WIDTH_THREE / ASPECT_RATIO_HEIGHT_FOUR))
        return Pair(requiredWidth, requiredHeight)
    }

    private fun getScreenWidthInPortrait(): Int {
        return min(getScreenWidth(), getScreenHeight())
    }

    private fun getScreenWidthInLandscape(): Int {
        return max(getScreenWidth(), getScreenHeight())
    }

    private fun getScreenWidth(): Int {
        val metrics = Resources.getSystem().displayMetrics
        return metrics.widthPixels
    }

    private fun getScreenHeight(): Int {
        val metrics = Resources.getSystem().displayMetrics
        return metrics.heightPixels
    }

    fun setViewSize(view: View?, width: Double, height: Double) {
        view?.layoutParams?.width = width.toInt()
        view?.layoutParams?.height = height.toInt()
        view?.requestLayout()
    }

    companion object {
        private const val ASPECT_RATIO_WIDTH_THREE = 3.0
        private const val ASPECT_RATIO_HEIGHT_FOUR = 4.0
        private const val TOTAL_PERCENTAGE = 100
    }
}


