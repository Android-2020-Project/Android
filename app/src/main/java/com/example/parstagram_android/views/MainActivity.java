package com.example.parstagram_android.views;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.parstagram_android.R;
import com.example.parstagram_android.models.Post;
import com.example.parstagram_android.views.fragments.HomeFragment;
import com.example.parstagram_android.views.fragments.LikesFragment;
import com.example.parstagram_android.views.fragments.PostFragment;
import com.example.parstagram_android.views.fragments.ProfileFragment;
import com.example.parstagram_android.views.fragments.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.File;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    final FragmentManager fragmentManager = getSupportFragmentManager();

    // define your fragments here
    final Fragment homeFragment = new HomeFragment();
    final Fragment searchFragment = new SearchFragment();
    final Fragment postFragment = new PostFragment();
    final Fragment likesFragment = new LikesFragment();
    final Fragment profileFragment = new ProfileFragment();

    public static final String TAG = "MainActivity";

    private BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);



        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment = null;
                switch (menuItem.getItemId()) {
                    case R.id.action_home:
                        fragment = homeFragment;
                        break;
                    case R.id.action_search:
                        Toast.makeText(MainActivity.this, "Search!", Toast.LENGTH_SHORT).show();
                        fragment = searchFragment;
                        break;
                    case R.id.action_post:
                        fragment = postFragment;
                        break;
                    case R.id.action_likes:
                        fragment = likesFragment;
                        break;
                    case R.id.action_profile:
                        fragment = profileFragment;
                        break;
                    default:
                        Toast.makeText(MainActivity.this, "Default!", Toast.LENGTH_SHORT).show();
                        break;
                }
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();
                return true;
            }
        });
        bottomNavigationView.setSelectedItemId(R.id.action_home);
    }
}