package com.thatnight.rxreok.presenter;

import android.content.Context;

import com.thatnight.rxreok.view.IMainView;

/**
 * Time:2017.2.20 22:39
 * Created By:ThatNight
 */

public class DataPresenter {



    private IMainView mMainView;
    private Context mContext;

    public DataPresenter(Context context,IMainView mMainView) {
        this.mMainView = mMainView;
        mContext=context;
    }





}
