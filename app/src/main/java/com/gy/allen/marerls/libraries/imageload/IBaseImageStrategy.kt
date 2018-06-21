package com.gy.allen.marerls.libraries.imageload

import android.content.Context
import android.widget.ImageView
import com.gy.allen.marerls.libraries.ImageConfig

interface IBaseImageStrategy {
    fun display(context: Context, imageConfig: ImageConfig)

    fun clean(context: Context, imageView: ImageView)
}