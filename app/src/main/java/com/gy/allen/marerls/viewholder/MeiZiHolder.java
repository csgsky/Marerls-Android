package com.gy.allen.marerls.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.gy.allen.marerls.R;
import com.gy.allen.marerls.interfaces.MeiZiListClickListener;
import com.gy.allen.marerls.widget.RatioImageView;

/**
 * Created by allen on 17/10/26.
 */

public class MeiZiHolder extends RecyclerView.ViewHolder {

    public final TextView MeiZiDesc;
    public final RatioImageView MeiZiIcon;
    public View card;
    private MeiZiListClickListener listener;

    public MeiZiHolder(View itemView, MeiZiListClickListener listener) {
        super(itemView);
        card = itemView;
        MeiZiIcon = itemView.findViewById(R.id.item_meizi_img);
        MeiZiDesc = itemView.findViewById(R.id.tv_meizi_desc);
        this.listener = listener;
    }

    public void bindListener(){
        card.setOnClickListener(v -> {
            listener.onMeiZiClick(card, getAdapterPosition());
        });
    }
}
