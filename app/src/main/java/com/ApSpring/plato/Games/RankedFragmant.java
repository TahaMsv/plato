package com.ApSpring.plato.Games;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.ApSpring.plato.MainActivity;
import com.ApSpring.plato.MainPage;
import com.ApSpring.plato.R;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class RankedFragmant extends Fragment {
    private Button startNewXORankMode;
    private Button startNewHangmanRankMode;

    public RankedFragmant() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ranked_fragmant, container, false);
        startNewXORankMode = view.findViewById(R.id.rank_start_X_O);
        startNewHangmanRankMode = view.findViewById(R.id.rank_start_hangman);
        startNewXORankMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainPage.netThread.sendMessage("gameXORank" + MainActivity.username);
            }
        });
        startNewHangmanRankMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainPage.netThread.sendMessage("gameHangmanRank" + MainActivity.username);
            }
        });
        readMessageFromServerToStartGame();
        return view;
    }

    private void readMessageFromServerToStartGame(){
//        while (GameFragment.netThread.getServerMessage().equals(""));

        String serverMessage = MainPage.netThread.getServerMessage();
        if (serverMessage.equals("startXO")){
            Toast.makeText(getActivity(), serverMessage, Toast.LENGTH_SHORT).show();
        }
        else if (serverMessage.equals("startHangman")){

        }
    }
}
