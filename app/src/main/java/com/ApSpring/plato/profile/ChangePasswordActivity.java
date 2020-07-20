package com.ApSpring.plato.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.ApSpring.plato.MainActivity;
import com.ApSpring.plato.NetworkHandlerThread;
import com.ApSpring.plato.R;

public class ChangePasswordActivity extends AppCompatActivity {

    EditText userInputEditTxt;
    Button userSubmitPassword;
    String userInputNewPass;
    NetworkHandlerThread netThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        userInputEditTxt = findViewById(R.id.userInputNewPassword);
        userSubmitPassword = findViewById(R.id.newPasswordSubmit);
        netThread = new NetworkHandlerThread();
        netThread.start();

        userSubmitPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userInputNewPass = userInputEditTxt.getText().toString();
                netThread.sendMessage("changePassword:+" + MainActivity.username + "+" + userInputNewPass);
                Intent intent = new Intent(ChangePasswordActivity.this, ProfilePage.class);
                startActivity(intent);
            }
        });
    }
}
