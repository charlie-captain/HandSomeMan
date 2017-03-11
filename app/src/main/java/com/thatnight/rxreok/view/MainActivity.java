package com.thatnight.rxreok.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.thatnight.rxreok.R;
import com.thatnight.rxreok.adapter.FragmentAdapter;
import com.thatnight.rxreok.fragment.BaseFragment;
import com.thatnight.rxreok.fragment.FunFragment;
import com.thatnight.rxreok.fragment.MainFragment;
import com.thatnight.rxreok.fragment.OutSideFragment;
import com.thatnight.rxreok.presenter.DataPresenter;

import java.util.Arrays;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity implements IMainView, NavigationView.OnNavigationItemSelectedListener {

    private final static int FRAGMENT_PAGE = 3;

    @InjectView(R.id.tlb_main)
    Toolbar mTlbMain;
    @InjectView(R.id.vp_main)
    ViewPager mVpMain;
    @InjectView(R.id.nv_main)
    NavigationView mNvMain;
    @InjectView(R.id.dl_main)
    DrawerLayout mDlMain;
    @InjectView(R.id.tl_main)
    TabLayout mTlMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        initDrawer();
        initViewPager();
        DataPresenter presenter = new DataPresenter(this, this);


    }

    private void initDrawer() {
        setSupportActionBar(mTlbMain);
        getSupportActionBar().setHomeButtonEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, mDlMain, mTlbMain,
//                R.string.navigation_drawer_open,
//                R.string.navigation_drawer_close);
//        mDlMain.setDrawerListener(toggle);
//        toggle.syncState();
        mNvMain.setNavigationItemSelectedListener(this);
    }

    private void initViewPager() {
        List<String> title = Arrays.asList("国内", "国际", "娱乐");

        List<BaseFragment> fragments = Arrays.asList(new MainFragment(), new OutSideFragment(), new FunFragment());
        for (int i = 0; i < FRAGMENT_PAGE; i++) {
            mTlMain.addTab(mTlMain.newTab().setText(title.get(i)));
        }

        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), fragments, title);
        mVpMain.setAdapter(fragmentAdapter);
        mTlMain.setupWithViewPager(mVpMain);
        mTlMain.setTabsFromPagerAdapter(fragmentAdapter);

    }

    @Override
    public void show() {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_home:
                mDlMain.openDrawer(GravityCompat.START);
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        item.setChecked(true);
        mDlMain.closeDrawers();
        return true;
    }
}
