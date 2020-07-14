package com.ApSpring.plato;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

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
    public ChatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_friends, container, false);

        recyclerView = v.findViewById(R.id.friendsRecyclerView);
        friendAdapter =new FriendsAdapter(chatList,getContext());
        recyclerView.setAdapter(friendAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        chatList=new ArrayList<>();

        chatList.add(new ExampleFriend(R.drawable.ic_profile,"asdasd"));
        chatList.add(new ExampleFriend(R.drawable.ic_profile,"asdfsdsdasd"));
        chatList.add(new ExampleFriend(R.drawable.ic_profile,"asd"));
        chatList.add(new ExampleFriend(R.drawable.ic_profile,"asdassssssd"));
        chatList.add(new ExampleFriend(R.drawable.ic_profile,"asdassssssd"));
        chatList.add(new ExampleFriend(R.drawable.ic_profile,"asdassssssd"));
        chatList.add(new ExampleFriend(R.drawable.ic_profile,"asdassssssd"));
        chatList.add(new ExampleFriend(R.drawable.ic_profile,"asdassssssd"));
        chatList.add(new ExampleFriend(R.drawable.ic_profile,"asdassssssd"));
        chatList.add(new ExampleFriend(R.drawable.ic_profile,"asdassssssd"));
        chatList.add(new ExampleFriend(R.drawable.ic_profile,"asdsssd"));
        chatList.add(new ExampleFriend(R.drawable.ic_profile,"asdassssssd"));
        chatList.add(new ExampleFriend(R.drawable.ic_profile,"asdass"));
        chatList.add(new ExampleFriend(R.drawable.ic_profile,"asdassssssd"));
        chatList.add(new ExampleFriend(R.drawable.ic_profile,"asdssssd"));
        chatList.add(new ExampleFriend(R.drawable.ic_profile,"asdassssssd"));
        chatList.add(new ExampleFriend(R.drawable.ic_profile,"asdasssd"));
        chatList.add(new ExampleFriend(R.drawable.ic_profile,"assssssd"));
        chatList.add(new ExampleFriend(R.drawable.ic_profile,"asdassssssd"));
        chatList.add(new ExampleFriend(R.drawable.ic_profile,"asdasaad"));
        chatList.add(new ExampleFriend(R.drawable.ic_profile,"asdas vsdfvd"));
        chatList.add(new ExampleFriend(R.drawable.ic_profile,"as"));
        chatList.add(new ExampleFriend(R.drawable.ic_profile,"asasfasdasd"));
        chatList.add(new ExampleFriend(R.drawable.ic_profile,"asdasd"));
    }
}
