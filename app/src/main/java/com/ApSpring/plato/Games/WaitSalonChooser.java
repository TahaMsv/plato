package com.ApSpring.plato.Games;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.ApSpring.plato.NetworkHandlerThread;
import com.ApSpring.plato.R;

public class WaitSalonChooser extends AppCompatActivity {
    NetworkHandlerThread netThread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait_salon_chooser);
        netThread = new NetworkHandlerThread();
        netThread.start();
        netThread.sendMessage("hangmanChooserWait");

        Thread receiverMsg = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    String serverMsg = netThread.getSMessage();
                    if (serverMsg.equals("finishedPlayingHangman")){
                        Intent intent = new Intent(WaitSalonChooser.this, RankedFragmant.class);
                        startActivity(intent);
                        break;
                    }
                }
            }
        });
        receiverMsg.start();
    }
}
