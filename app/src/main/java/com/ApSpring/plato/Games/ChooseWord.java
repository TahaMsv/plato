package com.ApSpring.plato.Games;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ApSpring.plato.MainActivity;
import com.ApSpring.plato.NetworkHandlerThread;
import com.ApSpring.plato.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ChooseWord extends AppCompatActivity {
    ArrayList<String> words;
    RadioGroup radioGroup;
    RadioButton radioButton;
    Button btnApply;
    NetworkHandlerThread netThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_word);
        btnApply = findViewById(R.id.choose_word_button);
        radioGroup = findViewById(R.id.choose_word_radio);
        words = new ArrayList<>();
        netThread.start();
        try {
            String currWord = "";
            Scanner wordInput = new Scanner(getAssets().open("hangman_database"));
            while (wordInput.hasNext()) {
                currWord = wordInput.nextLine();
                words.add(currWord);
            }
            Collections.shuffle(words);

            setText();

            btnApply.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int radioId = radioGroup.getCheckedRadioButtonId();
                    radioButton = findViewById(radioId);
                    netThread.sendMessage("chosenWordHangMan+" + MainActivity.username + "+" + radioButton.getText());
                }
            });


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    void setText() {
        RadioButton[] rBtns = new RadioButton[10];
        rBtns[0] = findViewById(R.id.wordOne);
        rBtns[1] = findViewById(R.id.wordTwo);
        rBtns[2] = findViewById(R.id.wordThree);
        rBtns[3] = findViewById(R.id.wordFour);
        rBtns[4] = findViewById(R.id.wordFive);
        rBtns[5] = findViewById(R.id.wordSix);
        rBtns[6] = findViewById(R.id.wordSeven);
        rBtns[7] = findViewById(R.id.wordEight);
        rBtns[8] = findViewById(R.id.wordNine);
        rBtns[9] = findViewById(R.id.wordTen);

        for (int i = 0; i < 10; i++) {
            rBtns[i].setText(words.get(i));
        }
    }

    public void checkButton(View view) {
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        Toast.makeText(this, "you choosed " + radioButton.getText(), Toast.LENGTH_SHORT).show();
    }
}
