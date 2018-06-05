package com.gy.allen.marerls.widget

import android.content.Context
import android.util.AttributeSet
import com.bilibili.magicasakura.widgets.TintTextView

open class TickTextView: TintTextView {
    constructor(context: Context): super(context)
    constructor(context: Context, attrs: AttributeSet): super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, def: Int): super(context, attrs, def)
}