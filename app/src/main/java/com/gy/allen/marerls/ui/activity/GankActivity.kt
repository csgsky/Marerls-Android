package com.gy.allen.marerls.ui.activity

import android.os.Bundle
import android.support.v4.view.ViewCompat
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.animation.DecelerateInterpolator
import com.bumptech.glide.Glide
import com.gy.allen.marerls.R
import com.gy.allen.marerls.util.Consts
import kotlinx.android.synthetic.main.activity_gank.*
import kotlinx.android.synthetic.main.toolbar.view.*
import kotlinx.android.synthetic.main.view_toolbar.*
import uk.co.senab.photoview.PhotoViewAttacher



class GankActivity : AppCompatActivity() {
    private var mImageUrl: String = ""
    private var mImageTitle: String = ""
    private var mPhotoViewAttacher: PhotoViewAttacher? = null
    private var mIsHidden: Boolean = false;
    private fun parseIntent() {
        mImageUrl = intent.getStringExtra(Consts.EXTRA_IMAGE_URL)
        mImageTitle = intent.getStringExtra(Consts.EXTRA_IMAGE_TITLE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gank);
        parseIntent();
        ViewCompat.setTransitionName(imageView, Consts.TRANSIT_PIC)
        Glide.with(this).load(mImageUrl).centerCrop().into(imageView);
        app_bar_layout.toolbar.setTitle(mImageTitle);
        setupPhotoAttacher();
    }

    private fun setupPhotoAttacher() {
        app_bar_layout.toolbar.setNavigationOnClickListener {  {
            finish()
        }}
        mPhotoViewAttacher = PhotoViewAttacher(imageView)
        mPhotoViewAttacher!!.setOnViewTapListener { view, x, y -> hideShowToolBar() }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item != null) {
            if (item.itemId == android.R.id.home){
                finish()
                return true;
            }

        }
        return super.onOptionsItemSelected(item)
    }

    private fun hideShowToolBar() {
        app_bar_layout.animate()
                .translationY(if (mIsHidden) 0.toFloat() else (-app_bar_layout.getHeight()).toFloat())
                .setInterpolator(DecelerateInterpolator(2f))
                .start()
        mIsHidden = !mIsHidden
    }


}
