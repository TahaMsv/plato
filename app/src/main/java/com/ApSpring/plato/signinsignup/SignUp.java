package com.ApSpring.plato.signinsignup;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ApSpring.plato.MainPage;
import com.ApSpring.plato.NetworkHandlerThread;
import com.ApSpring.plato.R;

public class SignUp extends AppCompatActivity {
    EditText inputUserName, inputPassWord, inputPassWordRepeat;
    Button signUpButton;
    private NetworkHandlerThread netThread;
    boolean everyThingIsFine = false;


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
                    if (username.isEmpty()) {
                        inputUserName.setError("Enter  your username");
                    }else {
                        netThread.sendMessage("username:"+username);
                    }
                    while (netThread.getServerMessage().equals("")) {
                    }
                    if (netThread.getServerMessage().startsWith("OK")) {
                        everyThingIsFine = true;
                    } else if (netThread.getServerMessage().startsWith("err")) {
                        inputUserName.setError("Username already taken");
                    }
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
                    }
                    else {
                        netThread.sendMessage("password:"+password);
                    }
                } else if (hasFocus) {
                    if (inputUserName.getText().toString().trim().isEmpty()) {
                        inputUserName.setError("Enter  your username");
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
                password = "$x" + password;
                netThread.sendMessage(password);
                if (everyThingIsFine) {
                    Intent intent = new Intent(SignUp.this, MainPage.class);
                    startActivity(intent);
                }
            }
        });


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

            if (!passWordRepeat.isEmpty() && !password.equals(passWordRepeat)) {
                inputPassWordRepeat.setError("Not equal to the password");
            } else {
                correctPassWordRepeat = true;
                }
                if (!username.isEmpty() && !password.isEmpty() && !passWordRepeat.isEmpty() && correctPassWordRepeat) {
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
    }
}
