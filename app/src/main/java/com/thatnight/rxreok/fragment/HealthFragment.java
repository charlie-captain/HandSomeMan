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
import com.thatnight.rxreok.R;
import com.thatnight.rxreok.adapter.RefreshHealthAdapter;
import com.thatnight.rxreok.bean.Health;
import com.thatnight.rxreok.bean.HealthList;
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

public class HealthFragment extends BaseFragment {

    @InjectView(R.id.rv_main)
    EasyRecyclerView mRvMain;
    private boolean isFinish = false;
    private RefreshHealthAdapter mRefreshHealthAdapter;

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
        mRefreshHealthAdapter = new RefreshHealthAdapter(getActivity());
        mRvMain.setLayoutManager(new LinearLayoutManager(mRvMain.getContext()));
        mRvMain.setAdapter(mRefreshHealthAdapter);
        mRvMain.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mRefreshHealthAdapter.clear();
                getData(Constant.KEY_HEALTH, 1, 2, 20, 1);
            }
        });
    }

    @Override
    protected void onLazy() {
        if (!isVisiable || !isFinish) {
            return;
        }
        // TODO: 2017.3.11 加载数据
        getData(Constant.KEY_HEALTH, 1, 2, 10, 1);

    }

    public void getData(String key, int page, int classify, int rows, int id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.URL_HEALTH)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        IApiManager apiManager = retrofit.create(IApiManager.class);
        apiManager.getHealth(key, page, classify, rows, id)
                .subscribeOn(Schedulers.io())
                .map(new Func1<HealthList, List<Health>>() {
                    @Override
                    public List<Health> call(HealthList healthList) {
                        List<Health> healths = new ArrayList<Health>();
                        for (HealthList.ResultBean resultBean : healthList.getResult()) {
                            Health eachHealth = new Health();
                            eachHealth.setTitle(resultBean.getTitle());
                            eachHealth.setTime(resultBean.getTime());
                            eachHealth.setImg(resultBean.getImg());
                            healths.add(eachHealth);
                        }
                        return healths;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Health>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getActivity(), "网络出了些问题", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(List<Health> healths) {
                        mRefreshHealthAdapter.addAll(healths);
                    }
                });


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
