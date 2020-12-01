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

import java.util.List;


public class SearchFragment extends Fragment {
    private RecyclerView searchrv;
    protected SearchAdapter adapter;
    protected List<User> allUsers;
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
        ParseQuery<User> query = ParseQuery.getQuery(User.class);
        query.whereContains("username", userET.getText().toString());
        query.findInBackground(new FindCallback<User>() {
            @Override
            public void done(List<User> users, ParseException e) {
                for (User user: users){
                }
                allUsers.addAll(users);
                adapter.notifyDataSetChanged();
            }
        });
    }
}