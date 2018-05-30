package com.jordicros997.dsasegoncontrol;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by jordi on 29/05/2018.
 */

public interface GithubAPI {
    @GET("users/{user}")
    Call<User> user(@Path("user") String user);
    @GET("users/{user}/followers")
    Call<List<User>> llistaFollowers(@Path("user") String user);
}
