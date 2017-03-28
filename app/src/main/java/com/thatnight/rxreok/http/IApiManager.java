package com.thatnight.rxreok.http;


import com.thatnight.rxreok.bean.GirlList;
import com.thatnight.rxreok.bean.HealthList;
import com.thatnight.rxreok.bean.NewList;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Time:2017.2.20 22:03
 * Created By:ThatNight
 */

public interface IApiManager {


    @GET("index")
    rx.Observable<NewList> getNews(@Query("key") String key);


    @GET("meinv/")
    Observable<GirlList> getGirl(@Query("key")String key,@Query("num")int num);



    @GET("News")
    Observable<HealthList> getHealth(@Query("key")String key, @Query("page")int page,@Query("classify")int classify,@Query("rows")int rows,@Query("id")int id);
}
