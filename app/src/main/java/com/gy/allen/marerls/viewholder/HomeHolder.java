package com.gy.allen.marerls.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gy.allen.marerls.R;

/**
 * Created by allen on 18/1/15.
 */

public class HomeHolder extends RecyclerView.ViewHolder {

    public final TextView year;
    public final TextView name;
    public final ImageView cover;
    public final TextView casts;
    public final TextView directors;

    public HomeHolder(View itemView) {
        super(itemView);
        year = itemView.findViewById(R.id.years);
        name = itemView.findViewById(R.id.name);
        cover = itemView.findViewById(R.id.cover);
        casts = itemView.findViewById(R.id.casts);
        directors = itemView.findViewById(R.id.directors);
    }

}
