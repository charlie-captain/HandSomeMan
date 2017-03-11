package com.thatnight.rxreok.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.thatnight.rxreok.bean.News;

/**
 * Time:2017.3.11 20:20
 * Created By:ThatNight
 */

public class RefreshRvAdapter extends RecyclerArrayAdapter<News> {
    private Context mContext;

    public RefreshRvAdapter(Context context) {
        super(context);
        mContext=context;
    }


    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerHolder(parent);
    }
}
