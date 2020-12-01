package com.example.parstagram_android.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.parstagram_android.R;
import com.example.parstagram_android.views.fragments.EditProfileFragment;
import com.example.parstagram_android.views.fragments.HomeFragment;
import com.example.parstagram_android.views.fragments.LikesFragment;
import com.example.parstagram_android.views.fragments.MessagingFragment;
import com.example.parstagram_android.views.fragments.PostFragment;
import com.example.parstagram_android.views.fragments.ProfileFeedFragment;
import com.example.parstagram_android.views.fragments.ProfileFragment;
import com.example.parstagram_android.views.fragments.SearchFragment;
import com.example.parstagram_android.views.fragments.StoriesFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    final FragmentManager fragmentManager = getSupportFragmentManager();

    // define your fragments here
    final Fragment storiesFragment = new StoriesFragment();
    final Fragment homeFragment = new HomeFragment();
    final Fragment searchFragment = new SearchFragment();
    // TODO Search Results Fragment
    final Fragment postFragment = new PostFragment();
    final Fragment likesFragment = new LikesFragment();
    // TODO Other User's Profile Fragment
    final Fragment profileFragment = new ProfileFragment();
    final Fragment editProfileFragment = new EditProfileFragment();
    final Fragment profileFeedFragment = new ProfileFeedFragment();
    final Fragment messagingFragment = new MessagingFragment();

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
                Fragment topFragment = null;
                Fragment bottomFragment = null;
                switch (menuItem.getItemId()) {
                    case R.id.action_home:
                        topFragment = storiesFragment;
                        bottomFragment = homeFragment;
                        break;
                    case R.id.action_search:
                        Toast.makeText(MainActivity.this, "Search!", Toast.LENGTH_SHORT).show();
                        topFragment = storiesFragment;
                        bottomFragment = searchFragment;
                        break;
                    case R.id.action_post:
                        topFragment = storiesFragment;
                        bottomFragment = postFragment;
                        break;
                    case R.id.action_likes:
                        topFragment = storiesFragment;
                        bottomFragment = likesFragment;
                        break;
                    case R.id.action_profile:
                        topFragment = profileFragment;
                        bottomFragment = profileFeedFragment;
                        break;
                    default:
                        Toast.makeText(MainActivity.this, "Default!", Toast.LENGTH_SHORT).show();
                        break;
                }
                fragmentManager.beginTransaction().replace(R.id.Top_Container, topFragment).commit();
                fragmentManager.beginTransaction().replace(R.id.Bottom_Container, bottomFragment).commit();
                return true;
            }
        });
        bottomNavigationView.setSelectedItemId(R.id.action_home);
    }

    public void editProfile() {
        fragmentManager.beginTransaction().replace(R.id.Top_Container, editProfileFragment).commit();
    }

    public void doneEditing() {
        fragmentManager.beginTransaction().replace(R.id.Top_Container, profileFragment).commit();
    }

    public int likePost() {
        // TODO code for liking posts
        return 0;
    }

    public int unLikePost() {
        // TODO code for unliking posts
        return -1;
    }

    public int followUser() {
        // TODO code for following users
        return 0;
    }

    public int unFollowUser() {
        // TODO code for unfollowing users
        return -1;
    }

    public void messageUser() {
        // TODO code for messaging users
    }

    public void viewUserProfile() {
        // TODO code for viewing other user's profiles
        // make profile images and names clickable?
    }
}