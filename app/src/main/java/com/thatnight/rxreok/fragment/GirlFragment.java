package com.thatnight.rxreok.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.thatnight.rxreok.R;
import com.thatnight.rxreok.adapter.RefreshGirlAdapter;
import com.thatnight.rxreok.bean.Girl;
import com.thatnight.rxreok.bean.GirlList;
import com.thatnight.rxreok.constant.Constant;
import com.thatnight.rxreok.http.IApiManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Time:2017.3.11 19:42
 * Created By:ThatNight
 */

public class GirlFragment extends BaseFragment {

    @InjectView(R.id.rv_main)
    EasyRecyclerView mRvMain;
    private RefreshGirlAdapter mRefresh;
    private boolean isFinish = false;
    private int num = 10;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base, container, false);
        ButterKnife.inject(this, view);
        initView();
        isFinish = true;
        onLazy();
        return view;
    }

    private void initView() {
        mRefresh = new RefreshGirlAdapter(getActivity());
        mRvMain.setLayoutManager(new LinearLayoutManager(mRvMain.getContext()));
        mRvMain.setAdapter(mRefresh);
        mRefresh.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        });

        mRvMain.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mRefresh.clear();
                getData();
            }
        });
    }

    @Override
    protected void onLazy() {
        if (!isVisiable || !isFinish) {
            return;
        }
        // TODO: 2017.3.11 加载数据
        getData();
    }

    private void getData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.URL_GIRL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        IApiManager apiManager = retrofit.create(IApiManager.class);
        apiManager.getGirl(Constant.KEY_GIRL, num)
                .subscribeOn(Schedulers.io())
                .map(new Func1<GirlList, List<Girl>>() {
                    @Override
                    public List<Girl> call(GirlList girlList) {
                        List<Girl> girls = new ArrayList<Girl>();
                        for (GirlList.NewslistBean girl : girlList.getNewslist()) {
                            Girl eachGirl = new Girl();
                            eachGirl.setTitle(girl.getTitle());
                            eachGirl.setCtime(girl.getCtime());
                            eachGirl.setPicUrl(girl.getPicUrl());
                            eachGirl.setUrl(girl.getUrl());
                            girls.add(eachGirl);
                        }
                        return girls;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Girl>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getActivity(), "网络出了些问题", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(List<Girl> girls) {
                        mRefresh.addAll(girls);
                    }
                });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

}
