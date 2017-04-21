package com.alc.github.challenge.service;

import com.alc.github.challenge.model.GitUserResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Path;

/**
 * Created by Gino Osahon on 20/04/2017.
 */
public interface GitApiService {

    @GET("search/users")
    Call<GitUserResponse> getLagosJavaUsers(@Query("q") String params);

    @GET("search/users/{id}")
    Call<GitUserResponse> getUserDetails(@Path("id") int id, @Query("api_key") String apiKey);
}
