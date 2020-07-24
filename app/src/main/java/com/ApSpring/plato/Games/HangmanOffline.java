package com.ApSpring.plato.Games;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ApSpring.plato.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class HangmanOffline extends AppCompatActivity {
    TextView txtWordToBeGuessed;
    String wordToBeGuessed;
    String wordDisplayedString;
    char[] wordDisplayedCharArr;
    ArrayList<String> hangManListOfWords;
    EditText hangManEdtInput;
    TextView txtLettersTried;
    String lettersTried;
    final String MESSAGE_WITH_LETTERS_TRIED = "Letters tried : ";
    TextView txtTriesLeft;
    String triedLeft;
    final String WINNING_MESSAGE = "you won!";
    final String LOSING_MESSAGE = "you lost!";
    Button hangManResetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hangman);
        initVars();

        InputStream wordInputStream = null;
        Scanner input = null;
        String aword = "";

        hangManResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initializeGame();
            }
        });

        try {
            wordInputStream = getAssets().open("hangman_database");
            input = new Scanner(wordInputStream);
            while (input.hasNext()) {

                aword = input.nextLine();
                hangManListOfWords.add(aword);
            }
        } catch (IOException e) {
            Toast.makeText(this, "e.getMessage()", Toast.LENGTH_SHORT).show();
        } finally {
            if (input != null)
                input.close();
            try {
                if (wordInputStream != null)
                    wordInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        initializeGame();

        hangManEdtInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0) {
                    checkIfLetterIsInWord(s.charAt(0));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void checkIfLetterIsInWord(char letter) {
        if (wordToBeGuessed.indexOf(letter) >= 0) {
            if (wordDisplayedString.indexOf(letter) < 0) {
                ///
                revealLetterInWord(letter);
                displayWordOnScreen();

                ///
                if (!wordDisplayedString.contains("_")) {
                    txtTriesLeft.setText(WINNING_MESSAGE);
                }
            }
        } else {
            decreaseAndDisplayTriesLeft();

            if (triedLeft.isEmpty()) {
                txtTriesLeft.setText(LOSING_MESSAGE);
                txtWordToBeGuessed.setText(wordToBeGuessed);
            }
        }

        if (lettersTried.indexOf(letter) < 0) {
            lettersTried += letter + ", ";
            String messageToBeDisplayed = MESSAGE_WITH_LETTERS_TRIED + lettersTried;
            txtLettersTried.setText(messageToBeDisplayed);
        }
    }

    public void decreaseAndDisplayTriesLeft() {
        if (!triedLeft.isEmpty()) {
            triedLeft = triedLeft.substring(0, triedLeft.length() - 2);
            txtTriesLeft.setText(triedLeft);
        }
    }
    public void initializeGame() {
        Collections.shuffle(hangManListOfWords);
        wordToBeGuessed = hangManListOfWords.get(0);
        hangManListOfWords.remove(0);
        wordDisplayedCharArr = wordToBeGuessed.toCharArray();

        for (int i = 1; i < wordDisplayedCharArr.length - 1; i++) {
            wordDisplayedCharArr[i] = '_';
        }

        revealLetterInWord(wordDisplayedCharArr[0]);
        revealLetterInWord(wordDisplayedCharArr[wordDisplayedCharArr.length - 1]);

        wordDisplayedString = String.valueOf(wordDisplayedCharArr);

        displayWordOnScreen();

        hangManEdtInput.setText("");

        lettersTried = " ";
        txtLettersTried.setText(MESSAGE_WITH_LETTERS_TRIED);

        triedLeft = " X X X X X";
        txtTriesLeft.setText(triedLeft);
    }

    public void displayWordOnScreen() {
        String formattedString = "";
        for (char val : wordDisplayedCharArr) {
            formattedString += val + " ";
        }
        txtWordToBeGuessed.setText(formattedString);

    }

    public void revealLetterInWord(char letter) {
        int indexOfLetter = wordToBeGuessed.indexOf(letter);
        ///loop if index is positive or 0
        while (indexOfLetter >= 0) {
            wordDisplayedCharArr[indexOfLetter] = wordToBeGuessed.charAt(indexOfLetter);
            indexOfLetter = wordToBeGuessed.indexOf(letter, indexOfLetter + 1);
        }
        wordDisplayedString = String.valueOf(wordDisplayedCharArr);
    }


    public void initVars() {
        hangManListOfWords = new ArrayList<String>();
        txtWordToBeGuessed = findViewById(R.id.wordToBeGuessed);
        hangManEdtInput = findViewById(R.id.userWordsGuesses);
        txtLettersTried = findViewById(R.id.lettersUsed);
        txtTriesLeft = findViewById(R.id.numbersRemain);
        hangManResetButton = findViewById(R.id.hangManReset);
    }
}