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

        final String friendUsername = getIntent().getStringExtra("friendUsername");

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

                netThread.sendMessage("chatSenderMessage+" + friendUsername + "+" + message);

                mChat.add(chat);
                messageAdapter.notifyDataSetChanged();
                messageAdapter.notifyItemChanged(mChat.size() - 1);
            }
        });

      //  readMessage(friendUsername);

    }

    private void readMessage(String friendUsername) {
        while (netThread.getServerMessage().equals("")) {
        }
        String messages = netThread.getServerMessage();
        String[] messageSplit = messages.split("\\+");

        for (int i = 0; i < messageSplit.length; i++) {
            String currentMessage = messageSplit[i];
            if (!currentMessage.isEmpty()) {
                String[] data = currentMessage.split(","); // data[0]=sender  ,  data[1]=receiver  , data[2]=message
                if (data[0].equals(friendUsername)) {
                    Chat chat = new Chat(data[2], "me", "you");
                    mChat.add(chat);
                    messageAdapter.notifyDataSetChanged();
                    messageAdapter.notifyItemChanged(mChat.size() - 1);
                } else {
                    Chat chat = new Chat(data[2], "you", "me");
                    mChat.add(chat);
                    messageAdapter.notifyDataSetChanged();
                    messageAdapter.notifyItemChanged(mChat.size() - 1);
                }
            }
        }
    }

}
