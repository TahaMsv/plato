package com.ApSpring.plato.Games;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.ApSpring.plato.MainPage;
import com.ApSpring.plato.R;

import java.util.Collections;

public class LeaderBoardActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RankAdapter rankAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_board);

        String game=getIntent().getStringExtra("game");
        String top = getIntent().getStringExtra("topList");




        if(game.equals("XO")){
            top = top.substring(7,top.length()-2);
            String[] peopleTop = top.split("\\+");
            for (String string:peopleTop) {
                String[]data = string.split(",");
                MainPage.topXOPlayerList.add(new Player(data[1], data[0], data[2]));
            }


            recyclerView = findViewById(R.id.rankRecyclerView);
            rankAdapter = new RankAdapter(MainPage.topXOPlayerList, LeaderBoardActivity.this);
            recyclerView.setAdapter(rankAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(LeaderBoardActivity.this));
            loadXoPlayers();
        }
        else if(game.equals("Hangman")){

            top = top.substring(10,top.length()-2);
            String[] peopleTop = top.split("\\+");
            for (String string:peopleTop) {
                String[]data = string.split(",");
                MainPage.topHangmanPlayerList.add(new Player(data[1], data[0], data[2]));
            }
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
