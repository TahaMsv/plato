package com.ApSpring.plato.friends;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.ApSpring.plato.MainActivity;
import com.ApSpring.plato.NetworkHandlerThread;
import com.ApSpring.plato.R;

public class AddFriend extends AppCompatActivity {
    EditText inputUsername;
    Button addButton;
    private NetworkHandlerThread netThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);
        inputUsername = findViewById(R.id.addFriendInputUsername);
        addButton = findViewById(R.id.addFriendButton);
        netThread = new NetworkHandlerThread();
        netThread.start();


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = inputUsername.getText().toString().trim();
                if (username.isEmpty()) {
                    inputUsername.setError("Enter your friend's username");
                } else {
                    netThread.sendMessage("addFriend:+" + username + "+" + MainActivity.username);
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
//                    while (netThread.getServerMessage().equals("")) {
//                    }

                    String serverMessage = netThread.getSMessage();
                    if (serverMessage.startsWith("ok")) {
                        Intent intent = new Intent();
                        intent.putExtra("friendsUsername", username);
                        setResult(RESULT_OK, intent);
                        finish();
                    } else if (serverMessage.startsWith("err")) {
                        Intent intent = new Intent();
                        intent.putExtra("friendsUsername", "not found");
                        setResult(RESULT_OK, intent);
                        finish();
                    } else if (serverMessage.equals("this friend already exists")) {
                        Intent intent = new Intent();
                        intent.putExtra("friendsUsername", username + " already has been add");
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                }
            }
        });
    }
}
