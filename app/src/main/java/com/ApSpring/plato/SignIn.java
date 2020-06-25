package com.ApSpring.plato;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class SignIn extends AppCompatActivity {
    EditText inputUsername,inputPassWord;
    Button signInButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        init();
    }

    private void init() {
        inputUsername=findViewById(R.id.signInUsername);
        inputPassWord=findViewById(R.id.signInUserPassword);
        signInButton=findViewById(R.id.signInBtn);
    }
}
