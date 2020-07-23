package com.ApSpring.plato.Games;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.ApSpring.plato.MainActivity;
import com.ApSpring.plato.NetworkHandlerThread;
import com.ApSpring.plato.R;

public class WaitSalon extends AppCompatActivity {
    NetworkHandlerThread netThread;
    public static String otherPlayerChosenWord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait_salon);
        netThread.start();
        otherPlayerChosenWord = "";
        netThread.sendMessage("waitingRoomForHangman" + MainActivity.username);
        Thread readMessage = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    String serverMsg = netThread.getSMessage();
                    if (serverMsg.startsWith("hangmanWordPlay")){
                        otherPlayerChosenWord = serverMsg.substring(15);
                        break;
                    }
                }
            }
        });
        readMessage.start();
        if (!otherPlayerChosenWord.equals("")){
            Intent intent = new Intent(this, Hangman.class);
            startActivity(intent);
        }
    }
}
