package com.ApSpring.plato.friends;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.ApSpring.plato.MainActivity;
import com.ApSpring.plato.MainPage;
import com.ApSpring.plato.R;
import com.ApSpring.plato.chat.ChatScreenActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class FriendsFragment extends Fragment {

    private View v;
    private RecyclerView recyclerView;
    private FriendsAdapter friendAdapter;
    private FloatingActionButton fab;
    public static final int ADD_FRIEND = 1;
    private SwipeRefreshLayout srl;



    public FriendsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_friends, container, false);
        recyclerView = v.findViewById(R.id.friendsRecyclerView);
        friendAdapter = new FriendsAdapter(MainPage.friendsList, getContext());
        recyclerView.setAdapter(friendAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        srl=v.findViewById(R.id.refreshFriends);
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadFriends();
                friendAdapter.notifyDataSetChanged();
                srl.setRefreshing(false);
            }
        });

        friendAdapter.setOnItemClickListener(new FriendsAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position) {
                String friendsUsername = MainPage.friendsList.get(position).getUsername();
                Intent intent = new Intent(getActivity(), ChatScreenActivity.class);
                intent.putExtra("friendUsername", friendsUsername);
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

        MainPage.netThread.sendMessage("friendList" + MainActivity.username);
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String allFriend;

        allFriend = MainPage.netThread.getSMessage();
        System.out.println("load friends 112");

        if (allFriend != null) {
            allFriend = allFriend.substring(1);
        }

        String[] strings = {};
        if (!allFriend.isEmpty()) {
            strings = allFriend.split("\\+");
        }

        for (int i = 0; i < strings.length; i++) {
            if (!strings[i].equals("")) {
                if (!beenInList(strings[i])) {
                    MainPage.friendsList.add(new ExampleFriend(R.drawable.ic_profile, strings[i]));
                    friendAdapter.notifyDataSetChanged();
                    friendAdapter.notifyItemChanged(MainPage.friendsList.size() - 1);
                }
            }

        }

    }

    private boolean beenInList(String username) {
        for (int i = 0; i < MainPage.friendsList.size(); i++) {
            if (MainPage.friendsList.get(i).getUsername().equals(username)) {
                return true;
            }
        }
        return false;
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
                    String [] friendsUsernameParts=friendsUsername.split("\\+");
                    if(beenInList(friendsUsernameParts[1])){
                        Toast.makeText(getActivity(), friendsUsernameParts[0], Toast.LENGTH_SHORT).show();
                    }else {
                        MainPage.friendsList.add(new ExampleFriend(R.drawable.ic_profile, friendsUsernameParts[1]));
                        friendAdapter.notifyDataSetChanged();
                        friendAdapter.notifyItemChanged(MainPage.friendsList.size() - 1);
                    }

                } else {
                    MainPage.friendsList.add(new ExampleFriend(R.drawable.ic_profile, friendsUsername));
                    friendAdapter.notifyDataSetChanged();
                }
            }
        }

    }
}
