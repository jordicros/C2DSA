package com.jordicros997.dsasegoncontrol;

import android.widget.ProgressBar;

import com.fasterxml.jackson.annotation.JacksonAnnotation;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by jordi on 29/05/2018.
 */

public class Singleton {
    private static Singleton ourInstance;
    private User user;
    private GithubAPI service ;
    private ProgressBar progrBar;
    public static Singleton getInstance() {
        if(ourInstance==null)
        {
            ourInstance = new Singleton();
            return ourInstance;
        }

        else return ourInstance;

    }
    private Singleton()
    {
        this.user = new User();
        Retrofit retr = new Retrofit.Builder().baseUrl("https://api.github.com/")
                .client(new OkHttpClient())
                .addConverterFactory(JacksonConverterFactory.create()).build();
        this.service = retr.create(GithubAPI.class);
    }
    public User getUser(){
        return this.user;
    }

    public GithubAPI getService(){
        return this.service;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
