package com.ApSpring.plato.chat;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ApSpring.plato.R;

import java.util.ArrayList;
import java.util.List;

public class ChatScreenActivity extends AppCompatActivity {

    ImageButton sendButton;
    EditText inputMessage;
    RecyclerView recyclerView;
    MessageAdapter messageAdapter;
    List<Chat> mChat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_screen);
        mChat = new ArrayList<>();
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
                mChat.add(chat);
                messageAdapter.notifyDataSetChanged();
                messageAdapter.notifyItemChanged(mChat.size() - 1);
            }
        });

    }

    private void readMessage(String myId, String userId) {
        mChat = new ArrayList<>();

    }
}
