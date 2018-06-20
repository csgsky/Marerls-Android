package com.gy.allen.marerls.util

import android.content.Context
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.WindowManager
import java.lang.Exception

class ScreenUtils {
    companion object {
        /**
         * 获取屏幕宽度
         */
        fun getScreenWidth(context: Context): Int {
            val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val outMetrics = DisplayMetrics()
            windowManager.defaultDisplay.getMetrics(outMetrics)
            return outMetrics.widthPixels
        }

        /**
         * 获取屏幕高度
         */
        fun getScreenHeight(context: Context):Int {
            val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val outMetrics = DisplayMetrics()
            windowManager.defaultDisplay.getMetrics(outMetrics)
            return outMetrics.heightPixels
        }

        /**
         * 获取状态栏高度
         */
        fun getStatusBarHeight(context: Context): Int {
            var statusHeight: Int = -1
            try {
                val clazz = Class.forName("com.android.internal.R\$dimen")
                val instance = clazz.newInstance()
                val height = Integer.parseInt(clazz.getField("status_bar_height").get(instance).toString())
                statusHeight = context.resources.getDimensionPixelOffset(height)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return statusHeight
        }
        /**
         * 获取 ActionBar 高度
         */
        fun getActionBarHeight(context: Context): Int {
            val tv = TypedValue()
            if (context.theme.resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
                return TypedValue.complexToDimensionPixelSize(tv.data, context.resources.displayMetrics)
            }
            return 0
        }




    }
}