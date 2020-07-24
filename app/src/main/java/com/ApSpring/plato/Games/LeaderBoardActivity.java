package com.ApSpring.plato.Games;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.ApSpring.plato.MainPage;
import com.ApSpring.plato.R;

public class LeaderBoardActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RankAdapter rankAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_board);

        String game=getIntent().getStringExtra("game");


        if(game.equals("XO")){
            recyclerView = findViewById(R.id.rankRecyclerView);
            rankAdapter = new RankAdapter(MainPage.topXOPlayerList, LeaderBoardActivity.this);
            recyclerView.setAdapter(rankAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(LeaderBoardActivity.this));
            loadXoPlayers();
        }
        else if(game.equals("Hangman")){
            recyclerView = findViewById(R.id.rankRecyclerView);
            rankAdapter = new RankAdapter(MainPage.topHangmanPlayerList, LeaderBoardActivity.this);
            recyclerView.setAdapter(rankAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(LeaderBoardActivity.this));
            loadHangmanPlayers();
        }


    }

    private void loadHangmanPlayers() {
    }

    private void loadXoPlayers() {
    }
}
