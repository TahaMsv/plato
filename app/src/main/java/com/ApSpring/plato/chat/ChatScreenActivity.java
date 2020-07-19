package com.ApSpring.plato.chat;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ApSpring.plato.NetworkHandlerThread;
import com.ApSpring.plato.R;

import java.util.ArrayList;
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
        mChat = new ArrayList<>();
        netThread = new NetworkHandlerThread();
        sendButton = findViewById(R.id.sendButton);
        inputMessage = findViewById(R.id.text_send);
        recyclerView = findViewById(R.id.chatMessages);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        messageAdapter = new MessageAdapter(mChat, this);
        recyclerView.setAdapter(messageAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = inputMessage.getText().toString().trim();
                inputMessage.setText("");

                Chat chat = new Chat(message, "you", "me");

                netThread.sendMessage("chatSenderMessage+" + message);

                mChat.add(chat);
                messageAdapter.notifyDataSetChanged();
                messageAdapter.notifyItemChanged(mChat.size() - 1);
            }
        });

        readMessage();

    }

    private void readMessage() {
        while (netThread.getServerMessage().equals("")){

        }
        String[] messageSplit;
        String otherPersonMessage = netThread.getServerMessage();
        if (otherPersonMessage.startsWith("chatReceiveMessage")){
            while (!otherPersonMessage.equals("chatReceiveMessage+chatFinish")){
                messageSplit = otherPersonMessage.split("\\+");
                Chat chat = new Chat(messageSplit[1], "me", "you");
                otherPersonMessage = netThread.getServerMessage();
                mChat.add(chat);
                messageAdapter.notifyDataSetChanged();
                messageAdapter.notifyItemChanged(mChat.size() - 1);
            }
        }
    }
}
