package com.example.parstagram_android.models;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Like")
public class Like extends ParseObject {

    private static final String KEY_POST = "post";
    private static final String KEY_USER = "user";
    public static final String KEY_CREATED_AT = "createdAt";

    public Like() {
    }

    public int getLike() {
        return 0;
    }
}
