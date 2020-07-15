package com.ApSpring.plato.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ApSpring.plato.R;

public class ChangeBioActivity extends AppCompatActivity {

    EditText newBio;
    Button submitBio;
    String userBio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_bio);
        newBio = findViewById(R.id.userInputNewBio);
        submitBio = findViewById(R.id.newBioSubmit);

        submitBio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userBio = newBio.getText().toString();
                Intent intent = new Intent(ChangeBioActivity.this, ProfilePage.class);

                intent.putExtra("Bio", userBio);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }
}
