package com.ApSpring.plato.profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.ApSpring.plato.R;

public class ProfilePage extends AppCompatActivity {
    String[] accountProfileTitle;
    String[] accountProfileDescription;
    RecyclerView accountRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        accountRecyclerView = findViewById(R.id.account_recyclerView);

        accountProfileTitle = getResources().getStringArray(R.array.Account);
        accountProfileDescription = getResources().getStringArray(R.array.description);


        accountRecyclerViewAdapter adapter = new accountRecyclerViewAdapter(this, accountProfileTitle, accountProfileDescription);
        accountRecyclerView.setAdapter(adapter);
        accountRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}
