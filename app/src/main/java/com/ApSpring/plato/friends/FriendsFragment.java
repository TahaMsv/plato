package com.ApSpring.plato.friends;

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
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        v = inflater.inflate(R.layout.fragment_friends, container, false);
        Toast.makeText(getActivity(), "57", Toast.LENGTH_SHORT).show();
        netThread = new NetworkHandlerThread();
        netThread.start();
        recyclerView = v.findViewById(R.id.friendsRecyclerView);
        friendsList = new ArrayList<>();
        friendAdapter = new FriendsAdapter(friendsList, getContext());
        recyclerView.setAdapter(friendAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        friendAdapter.setOnItemClickListener(new FriendsAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity(), ChatScreenActivity.class);
                startActivityForResult(intent, ADD_FRIEND);
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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        netThread.sendMessage("friendList");
        friendsList = loadFriends();
    }


    private ArrayList<ExampleFriend> loadFriends() {
        ArrayList<ExampleFriend> friendsList = new ArrayList<>();
        List<String> names = new ArrayList<>();

        names = netThread.getServerList();


        for (int i = 0; i < names.size(); i++) {
            friendsList.add(new ExampleFriend(R.drawable.ic_profile, names.get(i)));
        }
        return friendsList;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            if (extras != null) {
                String friendsUsername = extras.getString("friendsUsername");
                friendsList.add(new ExampleFriend(R.drawable.ic_profile, friendsUsername));
            }
        }

    }
}
