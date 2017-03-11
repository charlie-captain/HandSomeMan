package com.thatnight.rxreok.http;


import com.thatnight.rxreok.bean.NewList;

import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Time:2017.2.20 22:03
 * Created By:ThatNight
 */

public interface ApiManager {


    @GET("social/")
    rx.Observable<NewList> getNews(@Query("key") String key, @Query("num") int num);
}
