package com.example.parstagram_android.views.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.parse.ParseFile;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends Fragment {

    protected ImageView profileImage;
    protected TextView tvUsername;
    protected TextView tvEmail;
    protected TextView tvCaption;
    protected Button btnEditProfile;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        profileImage = view.findViewById(R.id.profileView_image);
        tvUsername = view.findViewById(R.id.tvProfileViewUserName);
        btnEditProfile = view.findViewById(R.id.editProfile);
        tvEmail = view.findViewById(R.id.tvProfileViewEmail);
        tvCaption = view.findViewById(R.id.tvProfileViewCaption);
        tvUsername.setText(ParseUser.getCurrentUser().getUsername());
        tvEmail.setText(ParseUser.getCurrentUser().getEmail());
        tvCaption.setText(ParseUser.getCurrentUser().getString("caption"));
        //tvCaption.setText("hello");
        ParseFile pImage = ParseUser.getCurrentUser().getParseFile("image");
        if (pImage != null)
            Glide.with(this).load(pImage.getUrl()).circleCrop().into(profileImage);

        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).editProfile();
            }
        });
    }

}