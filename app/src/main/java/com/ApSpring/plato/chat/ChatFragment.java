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
        v = inflater.inflate(R.layout.fragment_friends, container, false);

        recyclerView = v.findViewById(R.id.friendsRecyclerView);
        friendAdapter = new FriendsAdapter(chatList, getContext());
        recyclerView.setAdapter(friendAdapter);
        netThread = new NetworkHandlerThread();
        netThread.start();
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
        netThread.sendMessage("chatFragment");

        Toast.makeText(getContext(), "chat on start", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        chatList = new ArrayList<>();
        Toast.makeText(getActivity(), "" + MainActivity.counter, Toast.LENGTH_SHORT).show();
        chatList.add(new ExampleFriend(R.drawable.profile1, "asdasd"));
        chatList.add(new ExampleFriend(R.drawable.profile2, "asdfsdsdasd"));
        chatList.add(new ExampleFriend(R.drawable.profile3, "asd"));
        chatList.add(new ExampleFriend(R.drawable.profile4, "asdassssssd"));
        chatList.add(new ExampleFriend(R.drawable.profile5, "asdassssssd"));
        chatList.add(new ExampleFriend(R.drawable.profile6, "asdassssssd"));
        chatList.add(new ExampleFriend(R.drawable.profile7, "asdassssssd"));
        chatList.add(new ExampleFriend(R.drawable.profile8, "asdassssssd"));
        chatList.add(new ExampleFriend(R.drawable.profile9, "asdassssssd"));
        chatList.add(new ExampleFriend(R.drawable.profile10, "asdassssssd"));
        chatList.add(new ExampleFriend(R.drawable.profile1, "asdsssd"));
        chatList.add(new ExampleFriend(R.drawable.profile2, "asdassssssd"));
        chatList.add(new ExampleFriend(R.drawable.profile3, "asdass"));
        chatList.add(new ExampleFriend(R.drawable.profile4, "asdassssssd"));
        chatList.add(new ExampleFriend(R.drawable.profile4, "asdssssd"));
        chatList.add(new ExampleFriend(R.drawable.profile4, "asdassssssd"));
        chatList.add(new ExampleFriend(R.drawable.profile5, "asdasssd"));
        chatList.add(new ExampleFriend(R.drawable.profile6, "assssssd"));
        chatList.add(new ExampleFriend(R.drawable.profile7, "asdassssssd"));
        chatList.add(new ExampleFriend(R.drawable.profile8, "asdasaad"));
        chatList.add(new ExampleFriend(R.drawable.profile9, "asdas vsdfvd"));
        chatList.add(new ExampleFriend(R.drawable.profile1, "as"));
        chatList.add(new ExampleFriend(R.drawable.profile2, "asasfasdasd"));
        chatList.add(new ExampleFriend(R.drawable.profile3, "asdasd"));
    }
}
