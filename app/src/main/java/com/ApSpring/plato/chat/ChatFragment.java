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
    private RecyclerView recyclerView;

    private FriendsAdapter friendAdapter;


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
        friendAdapter = new FriendsAdapter(MainPage.chatList, getContext());
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
    public void onStart() {
        super.onStart();
       // loadChats();
    }

    private void loadChats() {

        MainPage.netThread.sendMessage("chatList"+MainActivity.username);
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String allChats;
        allChats = MainPage.netThread.getSMessage();


        allChats = allChats.substring(1);


        String[] strings = {};
        if (!allChats.isEmpty()) {
            strings = allChats.split("\\+");
        }

        for (int i = 0; i < strings.length; i++) {
            boolean beenInListBefore = false;
            if (!strings[i].equals("")) {
                if (MainPage.friendsList.size() != 0) {
                    for (ExampleFriend ex : MainPage.friendsList) {
                        if (ex.getUsername().equals(strings[i])) {
                            beenInListBefore = true;
                            break;
                        }
                    }
                    if (!beenInListBefore)
                        MainPage.chatList.add(new ExampleFriend(R.drawable.ic_profile, strings[i]));
                }
                friendAdapter.notifyDataSetChanged();
                friendAdapter.notifyItemChanged(MainPage.chatList.size() - 1);
            }
        }

    }
}
