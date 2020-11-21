package com.example.parstagram_android.models;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("User")
public class User extends ParseObject {

    public static final String KEY_EMAIL = "email";
    public static final String KEY_PWD = "password";
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
}
