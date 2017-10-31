package com.gy.allen.marerls.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.gy.allen.marerls.R;
import com.gy.allen.marerls.data.GankMeiZiBean;
import com.gy.allen.marerls.viewholder.MeiZiHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by allen on 17/10/26.
 */

public class MeiZiAdapter extends RecyclerView.Adapter<MeiZiHolder> {

    private Context mContext;
    private List<GankMeiZiBean.ResultsBean> meizi = new ArrayList<>();
    public MeiZiAdapter(Context mContext, List<GankMeiZiBean.ResultsBean> data){
        this.mContext = mContext;
        this.meizi = data;

    }
    @Override
    public MeiZiHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.meizi_item, null);
        return new MeiZiHolder(view);
    }

    @Override
    public void onBindViewHolder(MeiZiHolder holder, int position) {
//        holder.MeiZiIcon
        GankMeiZiBean.ResultsBean item = meizi.get(position);
        holder.MeiZiDesc.setText(item.getDesc());
        Glide.with(mContext).load(item.getUrl()).into(holder.MeiZiIcon);

    }

    @Override
    public int getItemCount() {
        return meizi.size();
    }
}
