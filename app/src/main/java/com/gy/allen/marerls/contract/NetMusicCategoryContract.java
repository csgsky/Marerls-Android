package com.gy.allen.marerls.contract;

import android.content.Context;
import android.graphics.Bitmap;

import com.gy.allen.marerls.base.IBaseView;
import com.gy.allen.model.CategoryBean;

import java.util.List;

public interface NetMusicCategoryContract {
    interface Presenter{
        void loadData();
        void setCategoryColor(String imgUrl);
    }
    interface View extends IBaseView {
        Context context();
        void loadCategoryData(List<CategoryBean> categoryBeen);
        void setCategoryBitmap(Bitmap bitmap);
        void setCategoryColor(int color);
    }
}
