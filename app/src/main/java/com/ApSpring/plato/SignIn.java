package com.ApSpring.plato;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignIn extends AppCompatActivity {
    EditText inputUsername, inputPassWord;
    Button signInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        init();

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = inputUsername.getText().toString().trim();
                String passWord = inputPassWord.getText().toString().trim();
                if (isValidInputs(username, passWord)) {

                }
            }
        });
    }


    private boolean isValidInputs(String username, String passWord) {
        if(username.isEmpty()){
            inputUsername.setError("Username is empty");
            return false;
        }
        if(passWord.isEmpty()){
            inputPassWord.setError("Password is empty");
            return false;
        }
        /*username isn't exist : */
        if(true){
            inputUsername.setError(username+" does not exist");
            return false;
        }
        /*  if the user is found and  the password is incorrect*/
        if(true){
            inputPassWord.setError("Wrong password");
            return  false;
        }
        return true;
    }

    private void init() {
        inputUsername = findViewById(R.id.signInUsername);
        inputPassWord = findViewById(R.id.signInUserPassword);
        signInButton = findViewById(R.id.signInBtn);
    }
}
