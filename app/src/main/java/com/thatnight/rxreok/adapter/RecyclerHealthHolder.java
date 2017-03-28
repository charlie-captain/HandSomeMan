package com.thatnight.rxreok.adapter;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.thatnight.rxreok.R;
import com.thatnight.rxreok.bean.Health;

/**
 * Time:2017.3.11 22:59
 * Created By:ThatNight
 */

public class RecyclerHealthHolder extends BaseViewHolder<Health> {

    ImageView mImageView;
    TextView mTitle;
    TextView mTime;

    RecyclerHealthHolder(ViewGroup itemView) {
        super(itemView, R.layout.item_recyclerview_fun);
        mImageView = $(R.id.iv_image);
        mTime = $(R.id.tv_time);
        mTitle = $(R.id.tv_title);

    }

    @Override
    public void setData(final Health data) {
        mTitle.setText(data.getTitle());
        mTime.setText(String.valueOf(data.getTime()));
        Glide.with(getContext())
                .load(data.getImg())
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(mImageView);

    }
}
