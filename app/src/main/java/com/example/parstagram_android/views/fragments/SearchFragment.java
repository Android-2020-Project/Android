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
import android.widget.EditText;

import com.example.parstagram_android.R;
import com.example.parstagram_android.controllers.SearchAdapter;
import com.example.parstagram_android.models.User;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;


public class SearchFragment extends Fragment {
    private RecyclerView searchrv;
    protected SearchAdapter adapter;
    protected List<ParseUser> allUsers;
    protected EditText userET;
    protected Button searchButton;


    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        allUsers = new ArrayList<>();
        searchButton = view.findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                searchUsers(v);
            }
        });
        userET = view.findViewById(R.id.searchET);
        searchrv = view.findViewById(R.id.searchrv);
        adapter = new SearchAdapter(getContext(), allUsers);
        searchrv.setAdapter(adapter);
        searchrv.setLayoutManager(new LinearLayoutManager(getContext()));

    }

    public void searchUsers(View v){
        /*
        ParseQuery<User> query = ParseQuery.getQuery(User.class);
        query.whereContains("username", userET.getText().toString());
        query.findInBackground(new FindCallback<User>() {
            @Override
            public void done(List<User> users, ParseException e) {
                System.out.println(users.get(0).toString());
                for (User user: users){
                }
                allUsers.addAll(users);
                adapter.notifyDataSetChanged();
            }
        });
         */
        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.whereContains("username",  userET.getText().toString());
        query.findInBackground(new FindCallback<ParseUser>() {
            public void done(List<ParseUser> users, ParseException e) {
                allUsers.clear();
                if (e == null) {
                    // The query was successful, returns the users that matches
                    // the criterias.
                    for(ParseUser user : users) {
                        System.out.println(user.getUsername());
                    }
                    allUsers.addAll(users);
                    adapter.notifyDataSetChanged();
                } else {
                    // Something went wrong.
                }
            }
        });
    }
}