package com.gy.allen.marerls.ui.fragment;

import android.Manifest;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.gy.allen.marerls.MainActivity;
import com.gy.allen.marerls.R;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;

/**
 * Created by allen on 17/10/25.
 */

public class AboutFragment extends Fragment {

    private View mView;
    private MainActivity activity;

    public static final int RESULT_CODE_CHOOSE = 100;
    private Button btn_choose_photo;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mView = View.inflate(getActivity(), R.layout.fragment_about, null);
        activity = (MainActivity) getActivity();
        initView();
        return mView;
    }

    private void initView() {
        btn_choose_photo = mView.findViewById(R.id.btn_choose_photo);
        btn_choose_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxPermissions permissions = new RxPermissions(activity);
                permissions.request(Manifest.permission.READ_EXTERNAL_STORAGE , Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .subscribe(
                            granted -> {
                                if (granted) {
                                    Matisse.from(activity)
                                            .choose(MimeType.allOf())
                                            .countable(true)
                                            .maxSelectable(9)
                                            .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                                            .thumbnailScale(0.5f)
                                            .imageEngine(new GlideEngine())
                                            .forResult(RESULT_CODE_CHOOSE);
                                }
                            }
                         );
            }
        });
    }

}
