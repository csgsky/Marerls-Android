package com.gy.allen.marerls.ui.activity

import android.Manifest
import android.graphics.Bitmap
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.animation.DecelerateInterpolator
import android.widget.Toast
import com.bumptech.glide.Glide
import com.gy.allen.marerls.R
import com.gy.allen.marerls.util.BitmapUtils
import com.gy.allen.marerls.util.Consts
import com.gy.allen.marerls.util.Router
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_gank.*
import kotlinx.android.synthetic.main.toolbar.view.*
import kotlinx.android.synthetic.main.view_toolbar.*
import uk.co.senab.photoview.PhotoViewAttacher


class GankActivity : AppCompatActivity() {
    private var mImageUrl: String = ""
    private var mImageTitle: String = ""
    private var mPhotoViewAttacher: PhotoViewAttacher? = null
    private var mIsHidden: Boolean = false;
    private var mActivity: GankActivity? = null
    private fun parseIntent() {
        mImageUrl = intent.getStringExtra(Consts.EXTRA_IMAGE_URL)
        mImageTitle = intent.getStringExtra(Consts.EXTRA_IMAGE_TITLE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gank);
        parseIntent();
        initToolbar();
        mActivity = this
//        ViewCompat.setTransitionName(imageView, Consts.TRANSIT_PIC)
        Glide.with(this).load(mImageUrl).centerCrop().into(imageView)
        app_bar_layout.toolbar.title = mImageTitle
        setupPhotoAttacher();
    }

    private fun initToolbar() {
        app_bar_layout.toolbar.setNavigationOnClickListener({
            finish()
        })
        app_bar_layout.toolbar.inflateMenu(R.menu.menu_gank)
        app_bar_layout.toolbar.setNavigationIcon(R.drawable.toolbar_back);
        app_bar_layout.toolbar.setOnMenuItemClickListener({ item -> menuClick(item.itemId) })
    }

    private fun saveMeizi() {
        Thread(Runnable {
            kotlin.run {
                val bitmap = Glide.with(mActivity)
                        .load(mImageUrl)
                        .asBitmap()
                        .centerCrop()
                        .into(500, 500)
                        .get()
            }
        }).start()
    }

    private fun save() {
        Observable.just(mImageUrl)
                .map { url ->
                    return@map Glide.with(mActivity)
                            .load(url)
                            .asBitmap()
                            .centerCrop()
                            .into(500, 500)
                            .get()
                }
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ t: Bitmap? ->
                    if (t is Bitmap) {
                        RxPermissions(mActivity!!)
                                .request(android.Manifest.permission.READ_EXTERNAL_STORAGE,
                                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                .subscribe({ granted: Boolean? ->
                                    if (granted!!) {
                                        BitmapUtils.saveImage(mActivity, t)
                                        Toast.makeText(mActivity, "收藏成功", Toast.LENGTH_SHORT).show()
                                    }
                                })
                    }
                })
    }

    private fun menuClick(id: Int): Boolean {
        when (id) {
            R.id.action_save -> {
                save()
                return true
            }
            R.id.action_gank -> {
                Router.toDailyGankA(this)
                return true
            }
        }
        return true
    }


    private fun setupPhotoAttacher() {
        mPhotoViewAttacher = PhotoViewAttacher(imageView)
        mPhotoViewAttacher!!.setOnViewTapListener { view, x, y -> hideShowToolBar() }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_gank, menu)
        return true;
    }

    private fun hideShowToolBar() {
        app_bar_layout.animate()
                .translationY(if (mIsHidden) 0.toFloat() else (-app_bar_layout.getHeight()).toFloat())
                .setInterpolator(DecelerateInterpolator(2f))
                .start()
        mIsHidden = !mIsHidden
    }


}


