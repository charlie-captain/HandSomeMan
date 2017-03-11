package com.thatnight.rxreok.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Time:2017.3.11 19:42
 * Created By:ThatNight
 */

public class OutSideFragment extends BaseFragment {

    private boolean isFinish = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void onLazy() {
        if (!isVisiable || !isFinish) {
            return;
        }
        // TODO: 2017.3.11 加载数据

    }
}
