package com.gy.allen.marerls.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.gy.allen.marerls.R

class GankActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gank)
        testKotlin("1", "2")
    }

    fun testKotlin(x: String, y: String) {
        Log.i("GankActivity", x+y)
    }

}
