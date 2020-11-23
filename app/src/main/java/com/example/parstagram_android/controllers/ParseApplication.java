package com.example.parstagram_android.controllers;

import android.app.Application;

import com.example.parstagram_android.models.Post;
import com.parse.Parse;
import com.parse.ParseObject;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.networkInterceptors().add(httpLoggingInterceptor);

        ParseObject.registerSubclass(Post.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("E3NttAmR0bBAhxqLELM2K0rPDwNbNgfVaD7hV3HN")
                .clientKey("62CB1LWfQPdZW3my1mKsrHyKkQSvC70Waoz0xpFJ")
                .server("https://parseapi.back4app.com").build()
        );
    }
}
