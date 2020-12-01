package com.example.parstagram_android.models;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("Following")
public class Following extends ParseObject {
    public static final String KEY_USER = "username";
    public static final String KEY_USER_FOLLOWED = "userFollowed";

    public void setUser(ParseUser parseUser) {
        put(KEY_USER, parseUser);
    }

    public ParseUser getUser() {
        return getParseUser(KEY_USER);
    }

    public void setUserFollowed(ParseUser parseUser) { put(KEY_USER_FOLLOWED, parseUser); }

    public ParseUser getUserFollowed() { return getParseUser(KEY_USER_FOLLOWED); }

    public ParseFile getImage() { return getParseFile(User.KEY_IMAGE); };
}
