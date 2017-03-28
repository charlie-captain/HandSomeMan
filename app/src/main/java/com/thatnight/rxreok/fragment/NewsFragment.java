package com.thatnight.rxreok.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.thatnight.rxreok.R;
import com.thatnight.rxreok.adapter.RefreshNewsAdapter;
import com.thatnight.rxreok.bean.NewList;
import com.thatnight.rxreok.bean.News;
import com.thatnight.rxreok.constant.Constant;
import com.thatnight.rxreok.http.IApiManager;
import com.thatnight.rxreok.ui.DetailsActivity;

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

public class NewsFragment extends BaseFragment {

    @InjectView(R.id.rv_main)
    EasyRecyclerView mRvMain;

    private boolean isFinish = false;
    private RefreshNewsAdapter mRefreshNewsAdapter;

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
        mRefreshNewsAdapter = new RefreshNewsAdapter(getActivity());
        mRvMain.setLayoutManager(new LinearLayoutManager(mRvMain.getContext()));
        mRvMain.setAdapter(mRefreshNewsAdapter);
        mRvMain.setItemAnimator(new DefaultItemAnimator());
        mRefreshNewsAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                ArrayList<String> stringData = new ArrayList<String>();
                News news = mRefreshNewsAdapter.getAllData().get(position);
                stringData.add(news.getThumbnail_pic_s());
                stringData.add(news.getUrl());
                Intent intent = new Intent(getActivity(), DetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("data", stringData);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        mRvMain.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mRefreshNewsAdapter.clear();
                getData(Constant.KEY_NEWS);
            }
        });
    }

    @Override
    protected void onLazy() {
        if (!isVisiable || !isFinish) {
            return;
        }
        // TODO: 2017.3.11 加载数据
        getData(Constant.KEY_NEWS);

    }

    public void getData(String key) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.URL_NEWS)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        IApiManager apiManager = retrofit.create(IApiManager.class);
        apiManager.getNews(key)
                .subscribeOn(Schedulers.io())
                .map(new Func1<NewList, List<News>>() {
                    @Override
                    public List<News> call(NewList newList) {
                        List<News> news = new ArrayList<News>();
                        for (NewList.ResultBean.DataBean eachNews : newList.getResult().getData()) {
                            News everyNews = new News();
                            everyNews.setDate(eachNews.getDate());
                            everyNews.setTitle(eachNews.getTitle());
                            everyNews.setThumbnail_pic_s(eachNews.getThumbnail_pic_s());
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
                        mRefreshNewsAdapter.addAll(newses);
                    }
                });


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
