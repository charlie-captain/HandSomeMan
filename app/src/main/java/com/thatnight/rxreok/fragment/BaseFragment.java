package com.thatnight.rxreok.fragment;

import android.support.v4.app.Fragment;

/**
 * Time:2017.3.11 19:36
 * Created By:ThatNight
 */

public abstract class BaseFragment extends Fragment {

    protected boolean isVisiable=false;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(getUserVisibleHint()){
            isVisiable=true;
            onVisiable();
        }else{
            isVisiable=false;
            onInvisiable();
        }
    }

    protected void onVisiable() {
        onLazy();
    }

    protected abstract void onLazy();


    protected void onInvisiable() {
    }
}
