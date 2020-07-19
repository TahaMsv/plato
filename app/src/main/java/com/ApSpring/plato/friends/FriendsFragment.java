package com.ApSpring.plato.friends;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ApSpring.plato.MainActivity;
import com.ApSpring.plato.MainPage;
import com.ApSpring.plato.NetworkHandlerThread;
import com.ApSpring.plato.R;
import com.ApSpring.plato.chat.ChatScreenActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class FriendsFragment extends Fragment {

    View v;
    RecyclerView recyclerView;
    List<ExampleFriend> friendsList;
    FriendsAdapter friendAdapter;
    FloatingActionButton fab;
    public static final int ADD_FRIEND = 1;

    private NetworkHandlerThread netThread;


    public FriendsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_friends, container, false);
        recyclerView = v.findViewById(R.id.friendsRecyclerView);
        friendsList = new ArrayList<ExampleFriend>();
        friendAdapter = new FriendsAdapter(friendsList, getContext());
        recyclerView.setAdapter(friendAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        friendAdapter.setOnItemClickListener(new FriendsAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position) {
                MainPage.netThread.sendMessage("chatScreen" + friendsList.get(position).getUsername());
                Intent intent = new Intent(getActivity(), ChatScreenActivity.class);
//                startActivityForResult(intent, ADD_FRIEND);
                startActivity(intent);
            }
        });
        fab = v.findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddFriend.class);
                startActivityForResult(intent, ADD_FRIEND);
            }
        });
        RecyclerView.OnScrollListener scrollListener = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                switch (newState) {
                    case RecyclerView.SCROLL_STATE_IDLE:
                        fab.show();
                        break;
                    default:
                        fab.hide();
                        break;
                }
                super.onScrollStateChanged(recyclerView, newState);
            }
        };
        recyclerView.clearOnScrollListeners();
        recyclerView.addOnScrollListener(scrollListener);


        return v;
    }


    @Override
    public void onStart() {
        super.onStart();
        loadFriends();
    }


    private void loadFriends() {

        MainPage.netThread.sendMessage("friendList");

        String allFriend = "";
        while (MainPage.netThread.getServerMessage().equals("")){

        }
        allFriend = MainPage.netThread.getServerMessage();
        allFriend = allFriend.substring(1);
        String[] strings = {};
            strings = allFriend.split("\\+");


        for (int i = 0; i < strings.length; i++) {
            if (!strings[i].equals("")) {
                friendsList.add(new ExampleFriend(R.drawable.ic_profile, strings[i]));
            }
        }
        friendAdapter.notifyDataSetChanged();

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            if (extras != null) {
                String friendsUsername = extras.getString("friendsUsername");
                if (friendsUsername.contains("not found")) {
                    Toast.makeText(getActivity(), friendsUsername, Toast.LENGTH_SHORT).show();
                } else if (friendsUsername.contains("already has been add")) {
                    Toast.makeText(getActivity(), friendsUsername, Toast.LENGTH_SHORT).show();
                } else {
                    friendsList.add(new ExampleFriend(R.drawable.ic_profile, friendsUsername));
                }
            }
        }

    }
}
