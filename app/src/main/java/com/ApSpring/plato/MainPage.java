package com.ApSpring.plato;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.ApSpring.plato.Games.GameFragment;
import com.ApSpring.plato.chat.ChatFragment;
import com.ApSpring.plato.friends.ExampleFriend;
import com.ApSpring.plato.friends.FriendsFragment;
import com.ApSpring.plato.profile.ProfilePage;
import com.ApSpring.plato.settings.SettingFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainPage extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private TextView tv;
    private CircleImageView profileBtn;
    public static List<ExampleFriend> friendsList;
    public static List<ExampleFriend> chatList;


    private FrameLayout fl;

    public static NetworkHandlerThread netThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        tv = findViewById(R.id.textView);
        profileBtn = findViewById(R.id.profile_image_main_page);
        fl = findViewById(R.id.frameLayout);
        bottomNavigationView = findViewById(R.id.bottomNav);
        bottomNavigationView.setOnNavigationItemSelectedListener(bottomNavMethod);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment()).commit();
        tv.setText("Home");

        netThread = new NetworkHandlerThread();
        netThread.start();
        friendsList = new ArrayList<>();
        chatList = new ArrayList<>();
        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveToProfilePageIntent = new Intent(MainPage.this, ProfilePage.class);
                startActivity(moveToProfilePageIntent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

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

