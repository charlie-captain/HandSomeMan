package com.thatnight.rxreok.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.thatnight.rxreok.R;
import com.thatnight.rxreok.adapter.SettingAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Time:2017.3.18 19:39
 * Created By:ThatNight
 */

public class SettingFragment extends Fragment {


    @InjectView(R.id.tlb_main)
    Toolbar mTlbMain;
    @InjectView(R.id.lv_setttings)
    ListView mLvSetttings;
    private SettingAdapter mSettingAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        ButterKnife.inject(this, view);
        initView();
        return view;
    }

    private void initView() {
        mTlbMain.setTitle("设置");
        final List<String> title = new ArrayList<>();
        List<String> tag = new ArrayList<>();
        tag.add("switch");
        title.add("夜间模式");
        tag.add("text");
        title.add("关于");
        tag.add("text");
        title.add("反馈");
        mSettingAdapter = new SettingAdapter(getActivity(), tag, title);
        mLvSetttings.setAdapter(mSettingAdapter);

        mLvSetttings.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SettingAdapter.ViewHolder viewHolder = (SettingAdapter.ViewHolder) view.getTag();
                if (View.VISIBLE == viewHolder.mSwitch.getVisibility()) {       //若有开关
                    viewHolder.mSwitch.toggle();
                    if (viewHolder.mSwitch.isChecked()) {
                        Toast.makeText(getActivity(), title.get(position) + "打开", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), title.get(position) + "关闭", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    switch (title.get(position)) {
                        case "关于":
                            Toast.makeText(getActivity(), title.get(position) + "打开", Toast.LENGTH_SHORT).show();
                            break;
                        case "反馈":
                            Toast.makeText(getActivity(), title.get(position) + "打开", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            }
        });


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
