package com.gy.allen.marerls.widget

import android.content.Context
import android.os.Build
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import com.bilibili.magicasakura.widgets.TintLinearLayout
import com.gy.allen.marerls.R

open class TickLinearLayout : TintLinearLayout  {
    private var rippleEnabled: Boolean = false

    constructor(context: Context): super(context)

    constructor(context: Context, attrs: AttributeSet): super(context, attrs) {
        init(context, attrs)
    }

    fun isRippleEnable(): Boolean {
        return rippleEnabled
    }

    private fun init(context: Context, attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TickLinearLayout)
        rippleEnabled = typedArray.getBoolean(R.styleable.TickLinearLayout_ll_enable_ripple, false)
        if (rippleEnabled) {
            setRippleForeground(context)
        }
        typedArray.recycle()
    }

    private fun setRippleForeground(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val ripple = intArrayOf(R.attr.selectableItemBackgroundBorderless)
            val typedArray = context.obtainStyledAttributes(ripple)
            val backgroundResource = typedArray.getResourceId(0, 0)
            typedArray.recycle()
            foreground = ContextCompat.getDrawable(context, backgroundResource)
        }
    }


}