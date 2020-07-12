package com.ApSpring.plato;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainPage extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        bottomNavigationView = findViewById(R.id.bottomNav);
        bottomNavigationView.setOnNavigationItemSelectedListener(bottomNavMethod);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener bottomNavMethod = new
            BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment fragment = null;
                    switch (menuItem.getItemId()) {
                        case R.id.home:
                            fragment = new HomeFragment();
                            break;
                        case R.id.people:
                            fragment = new PeopleFragment();
                            break;
                        case R.id.chat:
                            fragment = new ChatFragment();
                            break;
                        case R.id.game:
                            fragment = new GameFragment();
                            break;
                        case R.id.profile:
                            fragment = new ProfileFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();

                    return true;
                }
            };
}

