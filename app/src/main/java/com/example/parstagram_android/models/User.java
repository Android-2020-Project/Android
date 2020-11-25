package com.example.parstagram_android.models;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.Date;

@ParseClassName("User")
public class User extends ParseObject {

    public static final String KEY_EMAIL = "email";
    public static final String KEY_PWD = "password";
    public static final String KEY_PHONE = "phone";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_USER = "username";
    public static final String KEY_POSTS = "numPosts";
    public static final String KEY_FOLLOWING = "numFollowing";
    public static final String KEY_FOLLOWERS = "numFollowers";
    public static final String KEY_CREATED_AT = "createdAt";

    public String getEmail() {
        return getString(KEY_EMAIL);
    }

    public void setEmail(String email) {
        put(KEY_EMAIL, email);
    }

    public void setPhone(String phone){
        put(KEY_PHONE, phone);
    }

    public ParseFile getImage() {
        return getParseFile(KEY_IMAGE);
    }

    public void setImage(ParseFile parseFile) {
        put(KEY_IMAGE, parseFile);
    }

    public ParseUser getUser() {
        return getParseUser(KEY_USER);
    }

    public void setUser(ParseUser parseUser) {
        put(KEY_USER, parseUser);
    }

    public int getPosts() { return getInt(KEY_POSTS); }

    public int getFollowing() { return getInt(KEY_FOLLOWING); }

    public int getFollowers() { return getInt(KEY_FOLLOWERS); }
}
