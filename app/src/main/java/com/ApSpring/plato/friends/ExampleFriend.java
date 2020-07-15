package com.ApSpring.plato.friends;

import android.widget.ImageView;
import android.widget.TextView;

public class ExampleFriend {
    private int profileImageId;
    private  String username;

    public ExampleFriend(int profileImageId, String username) {
        this.profileImageId = profileImageId;
        this.username = username;
    }

    public int getProfileImageId() {
        return profileImageId;
    }

    public String getUsername() {
        return username;
    }

    public void setProfileImageId(int profileImageId) {
        this.profileImageId = profileImageId;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
