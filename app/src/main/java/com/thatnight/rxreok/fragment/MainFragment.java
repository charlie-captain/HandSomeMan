package com.thatnight.rxreok.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thatnight.rxreok.R;
import com.thatnight.rxreok.adapter.FragmentAdapter;

import java.util.Arrays;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Time:2017.3.18 18:00
 * Created By:ThatNight
 */

public class MainFragment extends Fragment {

    private final static int FRAGMENT_PAGE = 3;

    @InjectView(R.id.tlb_main)
    Toolbar mTlbMain;
    @InjectView(R.id.tl_main)
    TabLayout mTlMain;
    @InjectView(R.id.abl_main)
    AppBarLayout mAblMain;
    @InjectView(R.id.vp_main)
    ViewPager mVpMain;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.inject(this, view);
        initView();
        initViewPager();
        return view;

    }

    private void initView() {
        mTlbMain.setTitle("男人帮");
    }

    private void initViewPager() {
        List<String> title = Arrays.asList("新闻", "美女", "健康");

        List<BaseFragment> fragments = Arrays.asList(new NewsFragment(), new GirlFragment(), new HealthFragment());

        for (int i = 0; i < FRAGMENT_PAGE; i++) {
            mTlMain.addTab(mTlMain.newTab().setText(title.get(i)));
        }

        FragmentAdapter fragmentAdapter = new FragmentAdapter(getChildFragmentManager(), fragments, title);
        mVpMain.setAdapter(fragmentAdapter);
        mTlMain.setupWithViewPager(mVpMain);
        mTlMain.setTabsFromPagerAdapter(fragmentAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
