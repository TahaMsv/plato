package com.ApSpring.plato;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListAdapter;

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

        sendButton=findViewById(R.id.sendButton);
        inputMessage=findViewById(R.id.text_send);
        recyclerView=findViewById(R.id.chatMessages);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

    }
    private void readMessage(String myId,String userId,String imageUrl){
        mChat =new ArrayList<>();

    }
}
