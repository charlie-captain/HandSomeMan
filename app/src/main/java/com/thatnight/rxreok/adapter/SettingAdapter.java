package com.thatnight.rxreok.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Switch;
import android.widget.TextView;

import com.thatnight.rxreok.R;

import java.util.List;

/**
 * Time:2017.3.26 14:06
 * Created By:ThatNight
 */

public class SettingAdapter extends BaseAdapter {

    private LayoutInflater mLayoutInflater;
    private List<String> mTitle;
    private List<String> mTag;

    public SettingAdapter(Context context, List<String> tag, List<String> title) {
        mLayoutInflater = LayoutInflater.from(context);
        mTitle = title;
        mTag = tag;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mLayoutInflater.inflate(R.layout.item_settings, null);
            viewHolder.mTextView = (TextView) convertView.findViewById(R.id.item_settings_about);
            viewHolder.mSwitch = (Switch) convertView.findViewById(R.id.item_settings_switch);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if ("text".equals(mTag.get(position))) {;
            viewHolder.mSwitch.setVisibility(View.GONE);
        } else if ("switch".equals(mTag.get(position))) {
            viewHolder.mSwitch.setVisibility(View.VISIBLE);
        }
        viewHolder.mTextView.setText(mTitle.get(position));
        return convertView;
    }

    public class ViewHolder {
        public TextView mTextView;
        public Switch mSwitch;
    }
}
