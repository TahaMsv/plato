package com.ApSpring.plato.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ApSpring.plato.R;

public class ChangeUserName extends AppCompatActivity {
    EditText newUsername;
    Button submitUsername;
    String userUsername;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_user_name);
        newUsername = findViewById(R.id.userInputNewUserName);
        submitUsername = findViewById(R.id.newUserNameSubmit);

        submitUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userUsername = newUsername.getText().toString();
                Intent intent = new Intent();
                intent.putExtra("username", userUsername);
                startActivity(intent);
            }
        });
    }
}
