package com.gy.allen.marerls.util;

import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import com.gy.allen.marerls.R;

/**
 * Created by allen on 17/10/26.
 */

public class AppBarUtil {

    public interface TitleOnClickListener {
        void onTitleClick(View v);
    }

    public static void initAppBar(AppCompatActivity activity, String title) {
        activity.setSupportActionBar((Toolbar) activity.findViewById(R.id.toolbar));
        final ActionBar ab = activity.getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setTitle("");
        ((TextView) activity.findViewById(R.id.appbar_title)).setText(title);
    }

    public static void initAppBar(Fragment fragment, View view, String title) {
        AppCompatActivity activity = (AppCompatActivity) fragment.getActivity();
        activity.setSupportActionBar((Toolbar) view.findViewById(R.id.toolbar));
        final ActionBar ab = activity.getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setTitle(title);
    }

    public static void initAppBar(AppCompatActivity activity, String title, int drawableId) {
        ((Toolbar) activity.findViewById(R.id.toolbar)).setNavigationIcon(drawableId);
        activity.setSupportActionBar((Toolbar) activity.findViewById(R.id.toolbar));
        final ActionBar ab = activity.getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setTitle("");
        ((TextView) activity.findViewById(R.id.appbar_title)).setText(title);
    }

    public static void initMDAppBar(AppCompatActivity activity, String title) {
        activity.setSupportActionBar((Toolbar) activity.findViewById(R.id.toolbar));
        final ActionBar ab = activity.getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setTitle(title);
    }


    public static int getActionBarHeight(AppCompatActivity context) {
        int result = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            TypedValue tv = new TypedValue();
            context.getTheme().resolveAttribute(android.support.v7.appcompat.R.attr.actionBarSize, tv, true);
            result = TypedValue.complexToDimensionPixelSize(tv.data, context.getResources().getDisplayMetrics());
        }
        // 在5.0之上，融入到顶部状态栏
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            result = result + (int) ScreenUtils.dp2px(context, 24);
//        }
        return result;
    }

}
