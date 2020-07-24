package com.ApSpring.plato.Games;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ApSpring.plato.MainPage;
import com.ApSpring.plato.R;
import com.ApSpring.plato.RankAdapter;
import com.ApSpring.plato.friends.FriendsAdapter;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class LeaderBoardFragment extends Fragment {
    View v;
    private RecyclerView recyclerView;
    private RankAdapter rankAdapter;

    public LeaderBoardFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_leader_board, container, false);

        recyclerView = v.findViewById(R.id.rankRecyclerView);
        rankAdapter = new RankAdapter(MainPage.topPlayerList, getContext());
        recyclerView.setAdapter(rankAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        loadPlayers();
    }

    private void loadPlayers() {
    }
}
