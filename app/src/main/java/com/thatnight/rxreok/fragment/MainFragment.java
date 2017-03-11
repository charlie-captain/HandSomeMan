package com.thatnight.rxreok.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.thatnight.rxreok.R;
import com.thatnight.rxreok.adapter.RefreshRvAdapter;
import com.thatnight.rxreok.bean.NewList;
import com.thatnight.rxreok.bean.News;
import com.thatnight.rxreok.constant.Constant;
import com.thatnight.rxreok.http.ApiManager;

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

public class MainFragment extends BaseFragment {

    @InjectView(R.id.rv_main)
    RecyclerView mRvMain;
    private boolean isFinish = false;
    private RefreshRvAdapter mRefreshRvAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.inject(this, view);
        initView();
        isFinish = true;
        onLazy();
        return view;
    }

    private void initView() {
        mRefreshRvAdapter =new RefreshRvAdapter(getActivity());
        mRvMain.setLayoutManager(new LinearLayoutManager(mRvMain.getContext()));
        mRvMain.setAdapter(mRefreshRvAdapter);
    }

    @Override
    protected void onLazy() {
        if (!isVisiable || !isFinish) {
            return;
        }
        // TODO: 2017.3.11 加载数据
        getData(Constant.KEY,10);

    }

    public void getData(String key, int num) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        ApiManager apiManager = retrofit.create(ApiManager.class);
        apiManager.getNews(key,num)
                .subscribeOn(Schedulers.io())
                .map(new Func1<NewList, List<News>>() {
                    @Override
                    public List<News> call(NewList newList) {
                        List<News> news=new ArrayList<News>();
                        for(NewList.NewslistBean eachNews:newList.getNewslist()){
                            News everyNews=new News();
                            everyNews.setCtime(eachNews.getCtime());
                            everyNews.setDescription(eachNews.getDescription());
                            everyNews.setPicUrl(eachNews.getPicUrl());
                            everyNews.setTitle(eachNews.getTitle());
                            everyNews.setUrl(eachNews.getUrl());
                            news.add(everyNews);
                        }
                        return news;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<News>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getActivity(), "网络出了些问题", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(List<News> newses) {
                        mRefreshRvAdapter.addAll(newses);
                    }
                });


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
