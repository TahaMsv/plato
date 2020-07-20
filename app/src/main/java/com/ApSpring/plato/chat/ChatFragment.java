package com.ApSpring.plato.chat;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ApSpring.plato.MainActivity;
import com.ApSpring.plato.MainPage;
import com.ApSpring.plato.NetworkHandlerThread;
import com.ApSpring.plato.friends.ExampleFriend;
import com.ApSpring.plato.friends.FriendsAdapter;
import com.ApSpring.plato.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChatFragment extends Fragment {


    View v;
    RecyclerView recyclerView;
    List<ExampleFriend> chatList;
    FriendsAdapter friendAdapter;
    private NetworkHandlerThread netThread;

    public ChatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_chat, container, false);

        recyclerView = v.findViewById(R.id.friendsRecyclerView);
        recyclerView.setAdapter(friendAdapter);
        chatList =new ArrayList<>();
        friendAdapter = new FriendsAdapter(chatList, getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        friendAdapter.setOnItemClickListener(new FriendsAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity(), ChatScreenActivity.class);
                startActivity(intent);
            }
        });
        return v;
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        chatList = new ArrayList<>();

    }

    @Override
    public void onStart() {
        super.onStart();
        loadChats();
    }

    private void loadChats() {

        MainPage.netThread.sendMessage("chatList");
        List<String> chats = MainPage.netThread.getServerList();

        for (int i = 0; i < chats.size(); i++) {
            chatList.add(new ExampleFriend(R.drawable.ic_profile, chats.get(i)));
        }

    }
}
