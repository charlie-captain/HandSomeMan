package com.thatnight.rxreok.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.thatnight.rxreok.R;
import com.thatnight.rxreok.bean.User;
import com.thatnight.rxreok.fragment.MainFragment;
import com.thatnight.rxreok.fragment.MeFragment;
import com.thatnight.rxreok.fragment.SettingFragment;
import com.thatnight.rxreok.view.IMainView;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity implements IMainView, NavigationView.OnNavigationItemSelectedListener {


    private static final String TAG = "MainActivity";
    @InjectView(R.id.nv_main)
    NavigationView mNvMain;
    @InjectView(R.id.dl_main)
    DrawerLayout mDlMain;

    private FragmentManager mFragmentManager;
    private FragmentTransaction mTransaction;
    private MainFragment mainFragment;
    private MeFragment meFragment;
    private SettingFragment settingFragment;
    private Fragment mLastFragment;
    private LinearLayout mLlHeader;
    private ImageView mIvIcon;
    private TextView mTvName;
    private User mUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        initData();
        initDrawer();
    }

    private void initData() {
        mLlHeader = (LinearLayout) mNvMain.getHeaderView(0).findViewById(R.id.ll_nv_head);
        mIvIcon = (ImageView) mLlHeader.findViewById(R.id.iv_nv_icon);
        mTvName = (TextView) mLlHeader.findViewById(R.id.tv_nv_name);
        mUser = getIntent().getParcelableExtra("user");
        mTvName.setText(mUser.getUserName());
    }

    private void initDrawer() {

        mFragmentManager = getSupportFragmentManager();
        mTransaction = mFragmentManager.beginTransaction();
        mainFragment = new MainFragment();
        mLastFragment = mainFragment;
        mTransaction.add(R.id.fl_main, mainFragment).commit();
        mNvMain.setCheckedItem(R.id.item_home);
        mNvMain.setNavigationItemSelectedListener(this);
        mLlHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMe();
                mNvMain.setCheckedItem(R.id.item_me);
                mDlMain.closeDrawers();
            }
        });
    }


    @Override
    public void show() {

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_home:
                if (!item.isChecked()) {
                    showMain();
                }
                break;
            case R.id.item_me:
                if (!item.isChecked()) {
                    showMe();
                }
                break;
            case R.id.item_settings:
                if (!item.isChecked()) {
                    showSetting();
                }
                break;
            case R.id.item_exit:
                finish();
                return true;
            default:
                break;
        }
        item.setChecked(true);
        mDlMain.closeDrawers();
        return true;
    }

    private void showSetting() {
        if (settingFragment == null) {
            settingFragment = new SettingFragment();
        }
        mTransaction = mFragmentManager.beginTransaction();
        if (settingFragment.isAdded()) {
            mTransaction.hide(mLastFragment).show(settingFragment).commit();
        } else {
            mTransaction.hide(mLastFragment).add(R.id.fl_main, settingFragment).commit();
        }
        mLastFragment = settingFragment;
    }

    private void showMe() {
        if (meFragment == null) {
            meFragment = new MeFragment();
            Bundle bundle=new Bundle();
            bundle.putParcelable("user",mUser);
            meFragment.setArguments(bundle);
        }
        mTransaction = mFragmentManager.beginTransaction();
        if (meFragment.isAdded()) {
            mTransaction.hide(mLastFragment).show(meFragment).commit();
        } else {
            mTransaction.hide(mLastFragment).add(R.id.fl_main, meFragment).commit();
        }
        mLastFragment = meFragment;
    }

    private void showMain() {
        if (mainFragment == null) {
            mainFragment = new MainFragment();
        }
        mTransaction = mFragmentManager.beginTransaction();
        if (mainFragment.isAdded()) {
            mTransaction.hide(mLastFragment).show(mainFragment).commit();
        } else {
            mTransaction.hide(mLastFragment).add(R.id.fl_main, mainFragment).commit();
        }
        mLastFragment = mainFragment;
    }
}
