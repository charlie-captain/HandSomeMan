package com.thatnight.rxreok.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.thatnight.rxreok.R;

import java.util.List;

/**
 * Time:2017.3.26 17:08
 * Created By:ThatNight
 */

public class MeAdater extends BaseAdapter {

    private List<String> mTitle;
    private LayoutInflater mLayoutInflater;

    public MeAdater(Context context, List<String> title) {
        mLayoutInflater = LayoutInflater.from(context);
        mTitle = title;
    }

    @Override
    public int getCount() {
        return mTitle.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (view == null) {
            view = mLayoutInflater.inflate(R.layout.item_me, null);
            viewHolder = new ViewHolder();
            viewHolder.mTextView = (TextView) view.findViewById(R.id.tv_me_title);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.mTextView.setText(mTitle.get(position));
        return view;
    }

    class ViewHolder {
        TextView mTextView;
    }
}
