package com.ApSpring.plato.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.ApSpring.plato.MainActivity;
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
                startActivity(intent);
            }
        });

        if(MainActivity.profileImageUri!=null){
            profileImage.setImageURI(MainActivity.profileImageUri);
        }

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
        username.setText(MainActivity.username);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CHANGE_USERNAME && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            if (extras != null) {
                String newUsername = extras.getString("newUsername");
                MainActivity.username = newUsername;
                username.setText(newUsername);
            }
        }
    }
}
