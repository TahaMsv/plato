package com.ApSpring.plato.profile;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ApSpring.plato.R;

public class ProfilePage extends AppCompatActivity {
    String[] accountProfileTitle;
    String[] accountProfileDescription;
    RecyclerView accountRecyclerView;
    accountRecyclerViewAdapter adapter;
    TextView test;
    public boolean firstClicked = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        accountRecyclerView = findViewById(R.id.account_recyclerView);

        test = findViewById(R.id.testText);

        accountProfileTitle = getResources().getStringArray(R.array.Account);
        accountProfileDescription = getResources().getStringArray(R.array.description);


        adapter = new accountRecyclerViewAdapter(this, accountProfileTitle, accountProfileDescription);
        accountRecyclerView.setAdapter(adapter);
        accountRecyclerView.setLayoutManager(new LinearLayoutManager(this));


    }

    public static void changeItem(int position){

    }


}
