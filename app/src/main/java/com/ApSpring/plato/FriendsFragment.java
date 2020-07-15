package com.ApSpring.plato;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FriendsFragment extends Fragment {

    View v;
    RecyclerView recyclerView;
    List <ExampleFriend> friendsList;
    FriendsAdapter friendAdapter;
    FloatingActionButton fab;


    public FriendsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_friends, container, false);

        recyclerView = v.findViewById(R.id.friendsRecyclerView);
        friendAdapter =new FriendsAdapter(friendsList,getContext());
        recyclerView.setAdapter(friendAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        friendAdapter.setOnItemClickListener(new FriendsAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent=new Intent(getActivity(),ChatScreenActivity.class);
                startActivity(intent);
            }
        });
        fab = v.findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
        friendsList=new ArrayList<>();
        friendsList.add(new ExampleFriend(R.drawable.ic_profile,"asdasd"));
        friendsList.add(new ExampleFriend(R.drawable.ic_profile,"asdfsdsdasd"));
        friendsList.add(new ExampleFriend(R.drawable.ic_profile,"asd"));
        friendsList.add(new ExampleFriend(R.drawable.ic_profile,"asdassssssd"));
        friendsList.add(new ExampleFriend(R.drawable.ic_profile,"asdasasdad"));
        friendsList.add(new ExampleFriend(R.drawable.ic_profile,"asdas vsdfvd"));
        friendsList.add(new ExampleFriend(R.drawable.ic_profile,"as"));
        friendsList.add(new ExampleFriend(R.drawable.ic_profile,"asasfasdasd"));
        friendsList.add(new ExampleFriend(R.drawable.ic_profile,"asdasd"));

    }
}
