package com.gy.allen.marerls.util;

import android.app.Activity;
import android.content.Intent;

import com.gy.allen.marerls.ui.activity.DailyGankA;

/**
 * Created by allen on 18/2/26.
 */

public class Router {
    public static void toDailyGankA(Activity activity) {
        activity.startActivity(new Intent(activity, DailyGankA.class));
    }
}
