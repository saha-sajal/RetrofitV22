package com.week6.retrofitv22;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ResponseAPI {

    @GET("users")
    Call<List<UserResponse>> getUser(@Query("_sort") String sortColumn, @Query("_order") String orderType);

    @GET("users")
    Call<List<UserResponse>> getUser(@QueryMap Map<String, String> parameters);

    @GET("posts")
    Call<List<PostResponse>> getPost(@Query("userId") int id);
}
