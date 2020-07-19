package com.ApSpring.plato;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;

import com.ApSpring.plato.signinsignup.SignIn;
import com.ApSpring.plato.signinsignup.SignUp;

public class MainActivity extends AppCompatActivity {
    Button signUpButton;
    Button signInButton;
    public static int counter=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signInButton = findViewById(R.id.firstPageSignInButton);
        signUpButton = findViewById(R.id.firstPageSignUpButton);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignUp.class);
                startActivity(intent);
            }
        });
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, SignIn.class);
                startActivity(intent);
            }
        });
    }

}
