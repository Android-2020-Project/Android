package com.example.parstagram_android.views.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.parstagram_android.R;
import com.example.parstagram_android.controllers.PostsAdapter;
import com.example.parstagram_android.models.Post;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;


public class ProfileFragment extends Fragment {

    public static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 23;
    public static final String TAG = "ProfileFragment";
    EditText etPass;
    EditText etPhone;
    EditText etEmail;
    EditText etCaption;
    ImageButton ibProfilePic;
    Button btnSubmit;
    TextView tvUsername;
    private File photoFile;


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ibProfilePic = view.findViewById(R.id.profile_image);
        etCaption = view.findViewById(R.id.profile_image_caption);
        etPass = view.findViewById(R.id.etPass);
        etPhone = view.findViewById(R.id.etPhone);
        etEmail = view.findViewById(R.id.etEmail);
        btnSubmit = view.findViewById(R.id.profile_submit);
        tvUsername = view.findViewById(R.id.tvUserName);
//TODO: set tvUsername
        String currentUser = ParseUser.getCurrentUser().getUsername();
//        if(ParseUser.getCurrentUser().getUsername() != null) {
//            tvUsername.setText(ParseUser.getCurrentUser().getUsername());
//        }
        int radius = 60;
        ParseFile pImage = ParseUser.getCurrentUser().getParseFile("image");
        if (pImage != null)
            Glide.with(this).load(pImage.getUrl()).transform(new RoundedCorners(radius)).into(ibProfilePic);
        //tvUserName.setText(post.().getUsername());

        ibProfilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchCamera();
                //updateUser();
            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateUser();
            }
        });
    }

    protected void updateUser() {
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            // Other attributes than "email" will remain unchanged!
            currentUser.put("image", new ParseFile(photoFile));

            // Saves the object.
            // Notice that the SaveCallback is totally optional!
            currentUser.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    // Here you can handle errors, if thrown. Otherwise, "e" should be null
                }
            });
        }
    }

    private void launchCamera() {
        // create Intent to take a picture and return control to the calling application
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Create a File reference for future access
        photoFile = getPhotoFileUri("photo.jpg");

        // wrap File object into a content provider
        // required for API >= 24
        // See https://guides.codepath.com/android/Sharing-Content-with-Intents#sharing-files-with-api-24-or-higher
        Uri fileProvider = FileProvider.getUriForFile(getContext(), "com.codepath.fileprovider", photoFile);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileProvider);

        // If you call startActivityForResult() using an intent that no app can handle, your app will crash.
        // So as long as the result is not null, it's safe to use the intent.
        if (intent.resolveActivity(getContext().getPackageManager()) != null) {
            // Start the image capture intent to take photo
            startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
        }
    }

    public File getPhotoFileUri(String fileName) {
        // Get safe storage directory for photos
        // Use `getExternalFilesDir` on Context to access package-specific directories.
        // This way, we don't need to request external read/write runtime permissions.
        File mediaStorageDir = new File(getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES), TAG);

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists() && !mediaStorageDir.mkdirs()){
            Log.d(TAG, "failed to create directory");
        }

        // Return the file target for the photo based on filename
        File file = new File(mediaStorageDir.getPath() + File.separator + fileName);

        return file;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Bitmap takenImage = BitmapFactory.decodeFile(photoFile.getAbsolutePath());
                ibProfilePic.setImageBitmap(takenImage);
            } else { // Result was a failure
                Toast.makeText(getContext(), "Picture wasn't taken!", Toast.LENGTH_SHORT).show();
            }
        }
    }


    public void getCurrentUser() {
        // After login, Parse will cache it on disk, so
        // we don't need to login every time we open this
        // application
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            tvUsername.setText(currentUser.getUsername());
        } else {
            // show the signup or login screen
            Log.i(TAG, "Issues getting username");
            return;
        }
    }



//    @Override
//    protected void queryPosts() {
//        super.queryPosts();
//        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
//        query.include(Post.KEY_USER);
//        query.whereEqualTo(Post.KEY_USER, ParseUser.getCurrentUser());
//        query.addDescendingOrder(Post.KEY_CREATED_AT);
//        query.findInBackground(new FindCallback<Post>() {
//            @Override
//            public void done(List<Post> posts, ParseException e) {
//                if (e != null) {
//                    Log.e(TAG, "Issue with getting posts");
//                    return;
//                }
//                for (Post post : posts) {
//                    Log.i(TAG, "post description: " + post.getDescription() + ", username: " + post.getUser().getUsername());
//                    //Toast.makeText(getContext(), "Success!", Toast.LENGTH_SHORT).show();
//                }
//                allPosts.addAll(posts);
//                adapter.notifyDataSetChanged();
//            }
//        });
   //}
}