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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.parstagram_android.R;
import com.example.parstagram_android.controllers.PostsAdapter;
import com.example.parstagram_android.models.Post;
import com.example.parstagram_android.views.MainActivity;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;


public class ViewProfileFragment extends Fragment {

    public static final String TAG = "ProfileViewFragment";
    private RecyclerView rvProfile;
    protected PostsAdapter adapter;
    protected List<Post> allPosts;
    protected ImageView profileImage;
    protected TextView tvUsername;
    protected TextView tvEmail;
    protected TextView tvCaption;
    protected Button btnEditProfile;

    public ViewProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvProfile = view.findViewById(R.id.rvProfile);

        allPosts = new ArrayList<>();
        adapter = new PostsAdapter(getContext(), allPosts);
        rvProfile.setAdapter(adapter);
        rvProfile.setLayoutManager(new LinearLayoutManager(getContext()));
        profileImage = view.findViewById(R.id.profileView_image);
        tvUsername = view.findViewById(R.id.tvProfileViewUserName);
        btnEditProfile = view.findViewById(R.id.editProfile);
        tvEmail = view.findViewById(R.id.tvProfileViewEmail);
        tvCaption = view.findViewById(R.id.tvProfileViewCaption);
        tvUsername.setText(ParseUser.getCurrentUser().getUsername());
        tvEmail.setText(ParseUser.getCurrentUser().getEmail());
        tvCaption.setText(ParseUser.getCurrentUser().getString("caption"));
        //tvCaption.setText("hello");
        int radius = 60;
        ParseFile pImage = ParseUser.getCurrentUser().getParseFile("image");
        if (pImage != null)
            Glide.with(this).load(pImage.getUrl()).transform(new RoundedCorners(radius)).into(profileImage);
        queryPosts();

        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).editProfile();
            }
        });
    }

    protected void queryPosts() {
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        query.include(Post.KEY_USER);
        query.whereEqualTo(Post.KEY_USER, ParseUser.getCurrentUser());
        query.addDescendingOrder(Post.KEY_CREATED_AT);
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issue with getting posts");
                    return;
                }
                for (Post post : posts) {
                    Log.i(TAG, "post description: " + post.getDescription() + ", username: " + post.getUser().getUsername());
                    //Toast.makeText(getContext(), "Success!", Toast.LENGTH_SHORT).show();
                }
                allPosts.addAll(posts);
                adapter.notifyDataSetChanged();
            }
        });
    }
}