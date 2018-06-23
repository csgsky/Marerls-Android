package com.gy.allen.marerls.mvp.presenter;

import android.graphics.Bitmap;

import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.gy.allen.marerls.R;
import com.gy.allen.marerls.contract.NetMusicCategoryContract;
import com.gy.allen.marerls.libraries.ImageConfig;
import com.gy.allen.marerls.libraries.ImageLoader;
import com.gy.allen.marerls.mvp.BaseRxPresenter;
import com.gy.allen.model.CategoryBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import static com.gy.allen.model.net.NetConstants.TYPE_CLASSIC;
import static com.gy.allen.model.net.NetConstants.TYPE_HOT;
import static com.gy.allen.model.net.NetConstants.TYPE_LOVE;
import static com.gy.allen.model.net.NetConstants.TYPE_MOVIE;
import static com.gy.allen.model.net.NetConstants.TYPE_NETWORK;
import static com.gy.allen.model.net.NetConstants.TYPE_NEW;
import static com.gy.allen.model.net.NetConstants.TYPE_ROCK;
import static com.gy.allen.model.net.NetConstants.TYPE_WESTITE;

public class NetMusicCategoryPresenter extends BaseRxPresenter<NetMusicCategoryContract.View> implements NetMusicCategoryContract.Presenter {


    private HashMap<String, Integer> mColorMap = new HashMap<>();

    @Inject
    NetMusicCategoryPresenter() {}

    @Override
    public void loadData() {
        int[] types = {
                TYPE_NEW, TYPE_HOT, TYPE_ROCK, TYPE_WESTITE,
                TYPE_CLASSIC, TYPE_LOVE, TYPE_MOVIE, TYPE_NETWORK};
        String[] titles = getView().context().getResources().getStringArray(R.array.CategoryArr);
        String[] urls = getView().context().getResources().getStringArray(R.array.CategorySongUrlArr);
        List<CategoryBean> categoryBeans = new ArrayList<>();
        for (int i = 0; i < types.length; i++) {
            CategoryBean categoryBean = new CategoryBean();
            categoryBean.type = types[i];
            categoryBean.title = titles[i];
            categoryBean.imgUrl = urls[i];
            categoryBeans.add(categoryBean);
        }
        getView().loadCategoryData(categoryBeans);
    }

    @Override
    public void setCategoryColor(String imgUrl) {
        if (getView() == null) {
            return;
        }
        ImageLoader.getInstance().display(getView().context(),
                new ImageConfig.Builder()
                        .url(imgUrl)
                        .asBitmap(true)
                        .placeholder(R.drawable.ic_default_horizontal)
                        .crossFade(500)
                        .isRound(false)
                        .intoTarget(new SimpleTarget<Bitmap>() {
                            @Override
                            public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                                getView().setCategoryBitmap(resource);
                                getView().setCategoryColor(getView().context().getResources().getColor(R.color.black));
                            }
                        })
        .build());
    }
}
