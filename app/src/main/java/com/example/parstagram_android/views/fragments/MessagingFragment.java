package com.example.parstagram_android.views.fragments;

import android.content.Context;
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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.parstagram_android.R;
import com.example.parstagram_android.controllers.ChatAdapter;
import com.example.parstagram_android.models.Message;
import com.example.parstagram_android.views.LoginActivity;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class MessagingFragment extends Fragment {

    static final String TAG = "MessagingFragment";
    static final String USER_ID_KEY = "userId";
    static final String BODY_KEY = "body";

    Context myContext;
    EditText etMessage;
    ImageButton btSend;
    RecyclerView rvChat;
    ArrayList<Message> messages;
    ChatAdapter chatAdapter;
    boolean msgFirstLoad;

    public MessagingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_messaging, container, false);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        myContext = context;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        startWithCurrentUser();
        ParseObject.registerSubclass(Message.class);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();

    }

    void startWithCurrentUser() {
        setupMessagePosting();
    }

    void setupMessagePosting() {
        etMessage = getView().findViewById(R.id.etMessage);
        btSend = getView().findViewById(R.id.btSend);
        rvChat = getView().findViewById(R.id.rvChat);
        messages = new ArrayList<>();
        msgFirstLoad = true;
        final String userId = ParseUser.getCurrentUser().getObjectId();
        chatAdapter = new ChatAdapter(myContext, userId, messages);
        rvChat.setAdapter(chatAdapter);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(myContext);
        rvChat.setLayoutManager(linearLayoutManager);
        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = etMessage.getText().toString();
                Message message = new Message();
                message.setBody(data);
                message.setUserId(ParseUser.getCurrentUser().getObjectId());
                message.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e == null)
                            Log.i(TAG, "Message was saved!");
                         else
                            Log.e(TAG, "Failed to save message", e);
                    }
                });
                etMessage.setText(null);
            }
        });
    }
}