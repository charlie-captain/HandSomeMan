package com.thatnight.rxreok.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.thatnight.rxreok.R;
import com.thatnight.rxreok.adapter.MeAdater;
import com.thatnight.rxreok.bean.User;

import java.util.Arrays;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Time:2017.3.18 19:38
 * Created By:ThatNight
 */

public class MeFragment extends Fragment {


    @InjectView(R.id.iv_me_icon)
    ImageView mIvMeIcon;
    @InjectView(R.id.lv_me)
    ListView mLvMe;
    @InjectView(R.id.tlb_me)
    Toolbar mTlbMe;

    private MeAdater mMeAdater;
    private User mUser;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me, container, false);
        ButterKnife.inject(this, view);
        initView();
        return view;

    }

    private void initView() {
        mUser = getArguments().getParcelable("user");
        mTlbMe.setTitle(mUser.getUserName());
        final List<String> title = Arrays.asList("个人信息", "浏览历史");
        mMeAdater = new MeAdater(getActivity(), title);
        mLvMe.setAdapter(mMeAdater);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mLvMe.setNestedScrollingEnabled(true);
        }
        mLvMe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "打开 " + title.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

}
