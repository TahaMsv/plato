package com.ApSpring.plato.chat;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ApSpring.plato.MainActivity;
import com.ApSpring.plato.NetworkHandlerThread;
import com.ApSpring.plato.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChatScreenActivity extends AppCompatActivity {

    ImageButton sendButton;
    EditText inputMessage;
    RecyclerView recyclerView;
    MessageAdapter messageAdapter;
    List<Chat> mChat;
    NetworkHandlerThread netThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_screen);


        final String friendUsername = getIntent().getStringExtra("friendUsername");

        mChat = new ArrayList<>();
        sendButton = findViewById(R.id.sendButton);
        inputMessage = findViewById(R.id.text_send);
        recyclerView = findViewById(R.id.chatMessages);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        messageAdapter = new MessageAdapter(mChat, this);
        recyclerView.setAdapter(messageAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        netThread = new NetworkHandlerThread();
        netThread.start();
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = inputMessage.getText().toString().trim();
                inputMessage.setText("");
                Date date=new Date();
                String currentTime=date.getHours()+":"+date.getMinutes();
                Chat chat = new Chat(message, "you", "me",currentTime);

                netThread.sendMessage("chatSenderMessage+" + MainActivity.username + "+"
                        + friendUsername + "+" + message+"+"+currentTime);

                mChat.add(chat);
                messageAdapter.notifyDataSetChanged();
                messageAdapter.notifyItemChanged(mChat.size() - 1);
            }
        });

        try {
            readMessage(friendUsername);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void readMessage(String friendUsername) throws InterruptedException {
        String s = "loadMessages+" + friendUsername + "+" + MainActivity.username;
        Thread.sleep(500);
        netThread.sendMessage(s);
        System.out.println();
        String messages = netThread.getSMessage();
        String[] messageSplit = {};
        if (!messages.equals(" ")) {
            messageSplit = messages.split("\\+");
        }
        for (int i = 1; i < messageSplit.length; i++) {
            String currentMessage = messageSplit[i];
            if (!currentMessage.isEmpty()) {
                String[] data = currentMessage.split(","); // data[0]=sender  ,  data[1]=receiver  , data[2]=message  , data[3]=time
                if (data[1].equals(friendUsername)) {
                    Chat chat = new Chat(data[2], "you", "me",data[3]);
                    mChat.add(chat);
                    messageAdapter.notifyDataSetChanged();
                    messageAdapter.notifyItemChanged(mChat.size() - 1);
                } else {
                    Chat chat = new Chat(data[2], "me", "you",data[3]);
                    mChat.add(chat);
                    messageAdapter.notifyDataSetChanged();
                    messageAdapter.notifyItemChanged(mChat.size() - 1);
                }
            }
        }
    }

}
