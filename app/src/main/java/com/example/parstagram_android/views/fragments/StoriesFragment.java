package com.example.parstagram_android.views.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.parstagram_android.R;
import com.example.parstagram_android.controllers.StoriesAdapter;
import com.example.parstagram_android.models.Following;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import java.util.ArrayList;
import java.util.List;

public class StoriesFragment extends Fragment {
    public static final String TAG = "StoriesFragment";
    private RecyclerView rvStories;
    protected StoriesAdapter adapter;
    protected List<Following> allFollowing;

    public StoriesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_stories, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvStories = view.findViewById(R.id.rvStories);

        allFollowing= new ArrayList<>();
        //adapter = new StoriesAdapter(getContext(), allFollowing);
        rvStories.setAdapter(adapter);
        rvStories.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        //queryFollowing();
    }

    protected void queryFollowing() {
       ParseQuery<Following> query = ParseQuery.getQuery(Following.class);
       query.whereContains(Following.KEY_USER, ParseUser.getCurrentUser().getObjectId());
       query.findInBackground(new FindCallback<Following>() {
           @Override
           public void done(List<Following> followings, ParseException e) {
               if(e != null)
                   Log.e(TAG, "There was an issue retrieving followers: " + e);

               if (!followings.isEmpty()) {
                   for (Following following : followings) {

                   }
               }
               allFollowing.addAll(followings);
               adapter.notifyDataSetChanged();
           }
       });
    }
}