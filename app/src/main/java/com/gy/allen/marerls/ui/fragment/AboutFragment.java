package com.gy.allen.marerls.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.gy.allen.marerls.MainActivity;
import com.gy.allen.marerls.R;

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

    }

}
