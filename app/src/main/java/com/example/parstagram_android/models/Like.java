package com.example.parstagram_android.models;

import com.parse.ParseClassName;

@ParseClassName("Like")
public class Like {

    private static final String KEY_POST = "post";
    private static final String KEY_USER = "user";
    public static final String KEY_CREATED_AT = "createdAt";

    public int getLike() {
        return 0;
    }
}
