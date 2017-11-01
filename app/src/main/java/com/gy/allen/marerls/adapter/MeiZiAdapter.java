package com.gy.allen.marerls.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.gy.allen.marerls.R;
import com.gy.allen.marerls.data.GankMeiZiBean;
import com.gy.allen.marerls.interfaces.MeiZiListClickListener;
import com.gy.allen.marerls.viewholder.MeiZiHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by allen on 17/10/26.
 */

public class MeiZiAdapter extends RecyclerView.Adapter<MeiZiHolder> {

    private Context mContext;
    private List<GankMeiZiBean.ResultsBean> meizi = new ArrayList<>();
    private MeiZiListClickListener mMeiZiListClickListener;
    public MeiZiAdapter(Context mContext, List<GankMeiZiBean.ResultsBean> data, MeiZiListClickListener meiZiListClickListener){
        this.mContext = mContext;
        this.meizi = data;
        this.mMeiZiListClickListener = meiZiListClickListener;

    }
    @Override
    public MeiZiHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.meizi_item, null);
        return new MeiZiHolder(view, mMeiZiListClickListener);
    }

    @Override
    public void onBindViewHolder(MeiZiHolder holder, int position) {
//        holder.MeiZiIcon
        GankMeiZiBean.ResultsBean item = meizi.get(position);
        holder.bindListener();
        int length = 10;
        String createdAt = item.getCreatedAt();
        String desc = createdAt.length() > length ? createdAt.substring(0,length) : "未知";
        holder.MeiZiDesc.setText(desc);
        Glide.with(mContext)
                .load(item.getUrl())
                .centerCrop()
                .into(holder.MeiZiIcon)
                .getSize((width, height) -> {
                    if (!holder.card.isShown()) {
                        holder.card.setVisibility(View.VISIBLE);
                    }
                });
    }

    @Override
    public int getItemCount() {
        return meizi.size();
    }
}
