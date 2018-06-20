package com.gy.allen.marerls.util

import android.content.Context
import android.util.TypedValue

class DensityUtils {
    companion object {

        /**
         * dp 转 px
         * @param context context
         * @param dpVal dp
         * @return px
         */
        fun dp2px(context: Context, dpVal: Float): Int {
            return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                    dpVal, context.resources.displayMetrics).toInt()
        }


        /**
         * sp 转 px
         * @param context context
         * @param spValue sp
         * @return px
         */
        fun sp2px(context: Context, spValue: Float): Int {
            return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                    spValue, context.resources.displayMetrics).toInt()
        }
    }
}