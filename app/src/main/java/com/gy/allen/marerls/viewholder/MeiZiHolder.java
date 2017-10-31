package com.gy.allen.marerls.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gy.allen.marerls.R;

/**
 * Created by allen on 17/10/26.
 */

public class MeiZiHolder extends RecyclerView.ViewHolder {

    public final TextView MeiZiDesc;

    public final ImageView MeiZiIcon;

    public MeiZiHolder(View itemView) {
        super(itemView);
        MeiZiIcon = itemView.findViewById(R.id.item_meizi_img);
        MeiZiDesc = itemView.findViewById(R.id.tv_meizi_desc);
    }
}
