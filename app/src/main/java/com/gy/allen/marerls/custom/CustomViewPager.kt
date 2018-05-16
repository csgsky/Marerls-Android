package com.gy.allen.marerls.custom

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent

/**
 * Created by allen on 18/5/10.
 */
open class CustomViewPager: ViewPager {

    constructor(context: Context): super(context)

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)

    private val result: Boolean = false

    override fun setCurrentItem(item: Int, smoothScroll: Boolean) {
        super.setCurrentItem(item, smoothScroll)
    }

    override fun setCurrentItem(item: Int) {
        super.setCurrentItem(item, false)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return if (result) {
            super.onInterceptTouchEvent(ev)
        } else {
            false
        }
    }

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        return if (result) {
             super.onTouchEvent(ev)
        } else {
             false
        }
    }
}