package com.ApSpring.plato;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {
    EditText inputUserName,inputPassWord, inputPassWordRepeat;
    Button signUpButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        init();
        inputPassWord.addTextChangedListener(signUpTextWatcher);
        inputPassWordRepeat.addTextChangedListener(signUpTextWatcher);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=inputUserName.getText().toString().trim();
                String password=inputPassWord.getText().toString().trim();
                String passwordRepeat=inputPassWordRepeat.getText().toString().trim();
                Toast.makeText(SignUp.this,"It's ok",Toast.LENGTH_LONG).show();
            }
        });

    }

    private TextWatcher signUpTextWatcher=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String username=inputUserName.getText().toString().trim();
            String password=inputPassWord.getText().toString().trim();
            String passWordRepeat= inputPassWordRepeat.getText().toString().trim();
            boolean correctPassWordRepeat=false;
            if(username.isEmpty()){
                inputUserName.setError("Enter  your username");
            }
            /*
            set error when user enter the duplicate username
            this part will implemented later
            */
            if(password.isEmpty()){
                inputPassWord.setError("Enter your password");
            }
            else if(password.length()<=5){
                inputPassWord.setError("Must be more than 5 character");
            }
            if( !passWordRepeat.isEmpty() && !password.equals(passWordRepeat)){
                inputPassWordRepeat.setError("Not equal to the password");
            }
            else{
                correctPassWordRepeat=true;
            }
            if(!username.isEmpty() && !password.isEmpty() && !passWordRepeat.isEmpty() && correctPassWordRepeat){
                signUpButton.setEnabled(true);
                signUpButton.setBackgroundColor(0xFF00FF00);
            }
            else{
                signUpButton.setEnabled(false);
                signUpButton.setBackgroundColor(0xFFF07167);
            }

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    private void init() {
        inputUserName=findViewById(R.id.username);
        inputPassWord=findViewById(R.id.userPassword);
        inputPassWordRepeat =findViewById(R.id.userPasswordRepeat);
        signUpButton=findViewById(R.id.signUpBtn);
    }
}
