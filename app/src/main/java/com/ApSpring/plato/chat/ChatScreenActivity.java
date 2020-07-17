package com.ApSpring.plato.chat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.ApSpring.plato.MessageAdapter;
import com.ApSpring.plato.R;
import com.ApSpring.plato.chat.Chat;

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
        mChat =new ArrayList<>();
        sendButton=findViewById(R.id.sendButton);
        inputMessage=findViewById(R.id.text_send);
        recyclerView = findViewById(R.id.chatMessages);
        messageAdapter =new MessageAdapter (mChat,this);
        recyclerView.setAdapter(messageAdapter);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message=inputMessage.getText().toString().trim();
                Chat chat=new Chat(message,"you","me");
                mChat.add(chat);
            }
        });

    }
    private void readMessage(String myId,String userId){
        mChat =new ArrayList<>();

    }
}
