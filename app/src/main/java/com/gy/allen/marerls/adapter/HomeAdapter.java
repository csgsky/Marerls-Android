package com.gy.allen.marerls.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.gy.allen.marerls.R;
import com.gy.allen.marerls.viewholder.HomeHolder;
import com.gy.allen.model.response.ThreatersResp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by allen on 18/1/15.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeHolder> {

    private Context mContext;
    private List<ThreatersResp.SubjectsBean> subject = new ArrayList<>();
    public HomeAdapter(Context context, List<ThreatersResp.SubjectsBean> subject) {
        this.mContext = context;
        this.subject = subject;
    }


    @Override
    public HomeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.home_item, null);
        return new HomeHolder(view);
    }

    @Override
    public void onBindViewHolder(HomeHolder holder, int position) {
        ThreatersResp.SubjectsBean item = subject.get(position);
        ThreatersResp.SubjectsBean.ImagesBean images = item.getImages();
        if (images.getMedium() != null && !TextUtils.isEmpty(images.getMedium())) {
            Glide.with(mContext)
                    .load(images.getMedium())
                    .centerCrop()
                    .into(holder.cover);
        }

        String title = item.getTitle();
        if (title != null && !TextUtils.isEmpty(title)) {
            holder.name.setText("《" + title + "》");
        }

        List<ThreatersResp.SubjectsBean.CastsBean> casts = item.getCasts();
        StringBuffer sb = new StringBuffer();
        if (casts != null && casts.size() > 0) {
            for (int i = 0; i <casts.size() ; i++) {
                if (i == casts.size() - 1) {
                    sb.append(casts.get(i).getName());
                } else {
                    sb.append(casts.get(i).getName() + ",");
                }
            }
            holder.casts.setText("主演：" + sb.toString());
        }

        String year = item.getYear();
        if (year != null && !TextUtils.isEmpty(year)){
            holder.year.setText(year);
        }

        List<ThreatersResp.SubjectsBean.DirectorsBean> directors = item.getDirectors();
        if (directors != null  && directors.size() > 0) {
//            StringBuffer stringBuffer = new StringBuffer();
//            for (int i = 0; i < directors.size(); i++) {
//                stringBuffer.append(directors.get(i).getName() + ",");
//            }
            holder.directors.setText("导演：" + directors.get(0).getName());
        }

    }

    @Override
    public int getItemCount() {
        return subject.size();
    }
}
