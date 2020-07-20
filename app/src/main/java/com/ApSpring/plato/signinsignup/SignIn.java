package com.ApSpring.plato.signinsignup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ApSpring.plato.MainActivity;
import com.ApSpring.plato.MainPage;
import com.ApSpring.plato.NetworkHandlerThread;
import com.ApSpring.plato.R;

import java.io.IOException;
import java.net.Socket;

public class SignIn extends AppCompatActivity {
    EditText inputUsername, inputPassWord;
    Button signInButton;
    private NetworkHandlerThread netThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        init();
        netThread = new NetworkHandlerThread();
        netThread.start();


        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = inputUsername.getText().toString().trim();
                String passWord = inputPassWord.getText().toString().trim();

                netThread.sendMessage("signInButton:");
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                netThread.sendMessage(username);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                netThread.sendMessage(passWord);


                if (isValidInputs(username, passWord)) {

                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    while (netThread.getServerMessage().equals("")) {
                    }
                    String serverMessage = netThread.getServerMessage();
                    Toast.makeText(SignIn.this, serverMessage, Toast.LENGTH_SHORT).show();
                    if (serverMessage.startsWith("ok")) {
                        MainActivity.username = username;
                        Intent intent = new Intent(SignIn.this, MainPage.class);
                        startActivity(intent);
                    }else if (serverMessage.equals("wrongPassword")){
                        serverMessage = "";
                        inputPassWord.setError("Wrong password");
                    }
                    else if (serverMessage.equals("wrongUsername")){
                        serverMessage = "";
                        inputUsername.setError("Wrong Username");
                    }
                }
            }
        });
    }




    private boolean isValidInputs(String username, String passWord) {
        if (username.isEmpty()) {
            inputUsername.setError("Username is empty");
            return false;
        }
        if (passWord.isEmpty()) {
            inputPassWord.setError("Password is empty");
            return false;
        }
        /*username isn't exist : */

        /* if the user is found and the password is incorrect*/
        return true;
    }
    private void init() {
        inputUsername = findViewById(R.id.signInUsername);
        inputPassWord = findViewById(R.id.signInUserPassword);
        signInButton = findViewById(R.id.signInBtn);
    }
}
