package com.ApSpring.plato.signinsignup;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ApSpring.plato.MainActivity;
import com.ApSpring.plato.MainPage;
import com.ApSpring.plato.NetworkHandlerThread;
import com.ApSpring.plato.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.net.URL;

import de.hdodenhof.circleimageview.CircleImageView;

public class SignUp extends AppCompatActivity {
    EditText inputUserName, inputPassWord, inputPassWordRepeat;
    Button signUpButton;
    private NetworkHandlerThread netThread;
    boolean usernameIsFine = true;
    CircleImageView profileImage;
    FloatingActionButton chooseFromGallery;
    private static final int PICK_IMAGE=100;
    Uri imageUrl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        init();

        netThread = new NetworkHandlerThread();
        netThread.start();



        inputUserName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (!hasFocus) {
                    String username = inputUserName.getText().toString().trim();

                }

            }
        });
        inputPassWord.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String password = inputPassWord.getText().toString().trim();

                    if (password.isEmpty()) {
                        inputPassWord.setError("Enter your password");
                    } else if (password.length() <= 5) {
                        inputPassWord.setError("Must be more than 5 character");
                    } else {
                        netThread.sendMessage("signUpPassword:" + password);
                    }
                } else if (hasFocus) {
                    if (inputUserName.getText().toString().trim().isEmpty()) {
                        inputUserName.setError("Enter your username");
                    }
                }
            }
        });

        inputUserName.addTextChangedListener(signUpTextWatcher);
        inputPassWord.addTextChangedListener(signUpTextWatcher);
        inputPassWordRepeat.addTextChangedListener(signUpTextWatcher);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = inputUserName.getText().toString().trim();
                String password = inputPassWord.getText().toString().trim();
                String passwordRepeat = inputPassWordRepeat.getText().toString().trim();
                if (!username.isEmpty() && !password.isEmpty() && !passwordRepeat.isEmpty()) {
                    if (password.equals(passwordRepeat)) {

                        netThread.sendMessage("SignUpButton");
                        MainActivity.username = username;
                        Intent intent = new Intent(SignUp.this, MainPage.class);
                        startActivity(intent);

                    } else {
                        inputPassWordRepeat.setError("Not equal to the password");
                    }
                }
            }
        });
        chooseFromGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });


    }

    private void openGallery() {
        Intent gallery=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery,PICK_IMAGE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK && requestCode==PICK_IMAGE){
            imageUrl=data.getData();
            MainActivity.profileImageUri=imageUrl;
            profileImage.setImageURI(imageUrl);

        }
    }

    private TextWatcher signUpTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            boolean correctPassWordRepeat = false;
            String username = inputUserName.getText().toString().trim();
            String passWordRepeat = inputPassWordRepeat.getText().toString().trim();
            String password = inputPassWord.getText().toString().trim();
            if (username.isEmpty()) {
                inputUserName.setError("Enter your username");
            } else {
                netThread.sendMessage("signUPUsername:" + username);
            }
            String result = netThread.getSMessage();

            if (result.startsWith("correctUsername")) {
                Log.i("Server message", " OK ");
                usernameIsFine = true;
            } else if (result.startsWith("err:")) {
                Log.i("Server message", " Error ");
                inputUserName.setError("Username already taken");
                usernameIsFine = false;
            }

            if (!passWordRepeat.isEmpty() && !password.equals(passWordRepeat)) {
                inputPassWordRepeat.setError("Not equal to the password");
            } else {
                correctPassWordRepeat = true;
            }
            if (!username.isEmpty() && !password.isEmpty() && !passWordRepeat.isEmpty() && correctPassWordRepeat && usernameIsFine) {
                signUpButton.setEnabled(true);
                signUpButton.setBackgroundColor(0xFF00FF00);
            } else {
                signUpButton.setEnabled(false);
                signUpButton.setBackgroundColor(0xFFF07167);
            }

            if (password.isEmpty()) {
                inputPassWord.setError("Enter your password");
            }
            if (username.isEmpty()) {
                inputUserName.setError("Enter your username");
            }

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private void init() {
        inputUserName = findViewById(R.id.username);
        inputPassWord = findViewById(R.id.userPassword);
        inputPassWordRepeat = findViewById(R.id.userPasswordRepeat);
        signUpButton = findViewById(R.id.signUpBtn);
        chooseFromGallery=findViewById(R.id.choosePhotoFab);
        profileImage=findViewById(R.id.profile_image_signUp);
    }
}
