package com.example.parstagram_android.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.parstagram_android.R;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class CreateUser extends AppCompatActivity {
    private EditText emailET;
    private EditText phoneET;
    private EditText usernameET;
    private EditText passwordET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        emailET = findViewById(R.id.etEmail);
        phoneET = findViewById(R.id.etPhoneNum);
        usernameET = findViewById(R.id.etUsername);
        passwordET = findViewById(R.id.etPassword);
    }

    public void CreateUserBtn(View v){
        ParseUser user = new ParseUser();
        user.setUsername(usernameET.getText().toString());
        user.setPassword(passwordET.getText().toString());
        user.setEmail(emailET.getText().toString());
        Number number = Double.parseDouble(phoneET.getText().toString());//this was such a pain to figure out do not try to use integer here it WILL NOT work -Stone
        user.put("phone", number);
        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    Toast.makeText(CreateUser.this, "New user registered! Please log in.", Toast.LENGTH_SHORT).show();
                    //Intent intent = new Intent(CreateUser.this, LoginActivity.class);
                    //startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(CreateUser.this, "Issue with Creating New User", Toast.LENGTH_SHORT).show();
                    System.out.println("________________________________________________________\n");
                    System.out.println(e);
                }
            }
        });
    }
    public void goBack(View v){
        finish();
    }
}