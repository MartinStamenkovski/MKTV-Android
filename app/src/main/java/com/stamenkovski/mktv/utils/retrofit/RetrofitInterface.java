package com.stamenkovski.mktv.utils.retrofit;


import com.stamenkovski.mktv.models.PlayItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by martin on 2/7/18.
 */
public interface RetrofitInterface {

    @GET("mk/tv.json")
    Call<List<PlayItem>> getTV();

}


