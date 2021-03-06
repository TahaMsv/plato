package com.ApSpring.plato.Games;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ApSpring.plato.MainPage;
import com.ApSpring.plato.R;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class LeaderBoardFragment extends Fragment {
    View v;
    Button XoTopPlayersButton;
    Button HangmanTopPlayersButton;

    private static final int Xo=1;
    private static final int Hangman_CODE=2;
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
        XoTopPlayersButton=v.findViewById(R.id.showXoTopPlayers);
        HangmanTopPlayersButton=v.findViewById(R.id.showHangmanTopPlayers);

        XoTopPlayersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainPage.netThread.sendMessage("gameLeaderBoardXO");
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                String serverMessage = MainPage.netThread.getSMessage();

                if (serverMessage.startsWith("top10XO")) {
                    Intent intent = new Intent(getActivity(), LeaderBoardActivity.class);
                    intent.putExtra("game", "XO");
                    intent.putExtra("topList", serverMessage);
                    startActivityForResult(intent,Xo);
                }
            }
        });

        HangmanTopPlayersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainPage.netThread.sendMessage("gameLeaderBoardHangman");
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                String serverMessage = MainPage.netThread.getSMessage();
                if (serverMessage.startsWith("top10Hangman")) {
                    Intent intent = new Intent(getActivity(), LeaderBoardActivity.class);
                    intent.putExtra("game", "Hangman");
                    intent.putExtra("topList", serverMessage);
                    startActivityForResult(intent,Hangman_CODE);
                }
            }
        });

        
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();

    }


}
