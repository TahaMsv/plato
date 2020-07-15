package com.ApSpring.plato;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.ApSpring.plato.chat.ChatFragment;
import com.ApSpring.plato.friends.FriendsFragment;
import com.ApSpring.plato.profile.ProfilePage;
import com.ApSpring.plato.settings.SettingFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainPage extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private TextView tv;
    private Button profileBtn;
    private FrameLayout fl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        tv = findViewById(R.id.textView);
        profileBtn = findViewById(R.id.profileButton);
        fl = findViewById(R.id.frameLayout);
        bottomNavigationView = findViewById(R.id.bottomNav);
        bottomNavigationView.setOnNavigationItemSelectedListener(bottomNavMethod);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment()).commit();
        tv.setText("Home");

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveToProfilePageIntent = new Intent(MainPage.this, ProfilePage.class);
                startActivity(moveToProfilePageIntent);
            }
        });
    }

    private BottomNavigationView.OnNavigationItemSelectedListener bottomNavMethod = new
            BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment fragment = null;
                    switch (menuItem.getItemId()) {
                        case R.id.home:
                            fragment = new HomeFragment();
                            tv.setText("Home");
                            break;
                        case R.id.friends:
                            fragment = new FriendsFragment();
                            tv.setText("Friends");
                            break;
                        case R.id.chat:
                            fragment = new ChatFragment();
                            tv.setText("Chat");
                            break;
                        case R.id.game:
                            fragment = new GameFragment();
                            tv.setText("Game");
                            break;
                        case R.id.setting:
                            fragment = new SettingFragment();
                            tv.setText("Setting");
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();

                    return true;
                }
            };
}

