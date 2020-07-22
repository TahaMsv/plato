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
        Button startNewXORankMode = view.findViewById(R.id.rank_start_X_O);
        Button startNewHangmanRankMode = view.findViewById(R.id.rank_start_hangman);
        startNewXORankMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameFragment.netThread.sendMessage("gameXORank" + MainActivity.username);
            }
        });
        startNewHangmanRankMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameFragment.netThread.sendMessage("gameHangmanRank" + MainActivity.username);
            }
        });
        readMessageFromServerToStartGame();
        return view;
    }

    private void readMessageFromServerToStartGame() {
        String serverMessage = GameFragment.netThread.getSMessage();
        if (serverMessage.equals("startXO")) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Toast.makeText(getActivity(), serverMessage, Toast.LENGTH_SHORT).show();
        }
    }
}
