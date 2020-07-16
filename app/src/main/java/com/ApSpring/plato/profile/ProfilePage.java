package com.ApSpring.plato.profile;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ApSpring.plato.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfilePage extends AppCompatActivity {
    FloatingActionButton changePhoto;
    Button changeUserName;
    Button changePassWord;
    CircleImageView profileImage;
    TextView username;

    public static final int CHANGE_USERNAME = 1;
    public static final int CHANGE_PASSWORD = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);
        init();

        changeUserName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfilePage.this, ChangeUserName.class);
                startActivityForResult(intent, CHANGE_USERNAME);
            }
        });
        changePassWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfilePage.this, ChangePasswordActivity.class);
                startActivityForResult(intent, CHANGE_PASSWORD);
            }
        });
        changePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

    private void init() {
        changePassWord = findViewById(R.id.changePasswordBtn);
        changeUserName = findViewById(R.id.changeUsernameBtn);
        changePhoto = findViewById(R.id.changePhotoFab);
        username = findViewById(R.id.usernameTextView);
        profileImage = findViewById(R.id.profile_image);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            if (extras != null) {
                String newUsername = extras.getString("newUsername");
                username.setText(newUsername);
            }
        }
        if (requestCode == 2 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            if (extras != null) {

            }
        }
    }
}
