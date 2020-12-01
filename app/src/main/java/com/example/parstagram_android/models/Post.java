package com.example.parstagram_android.models;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.Date;

@ParseClassName("Post")
public class Post extends ParseObject {

    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_USER = "user";
    public static final String KEY_CREATED_AT = "createdAt";
    public static final String KEY_LIKES = "likes";
    public static final String KEY_COMMENTS = "comments";
    public static final String KEY_SHARES = "shares";
    public static final String KEY_SAVES = "saves";

    public String getDescription() {
        return getString(KEY_DESCRIPTION);
    }

    public void setDescription(String description) {
        put(KEY_DESCRIPTION, description);
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

    public int getNumLikes() {
        return getInt(KEY_LIKES);
    }

    public int getNumComments() {
        return getInt(KEY_COMMENTS);
    }

    public int getNumShares() {
        return getInt(KEY_SHARES);
    }

    public int getNumSaves() {
        return getInt(KEY_SAVES);
    }
}
