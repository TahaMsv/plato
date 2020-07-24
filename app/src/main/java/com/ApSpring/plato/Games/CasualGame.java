package com.ApSpring.plato.Games;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ApSpring.plato.MainActivity;
import com.ApSpring.plato.MainPage;
import com.ApSpring.plato.R;

import java.net.Inet4Address;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class CasualGame extends Fragment {
    Button startNewXOCasualMode;
    Button startNewHangmanCasualMode;

    public CasualGame() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ranked_fragmant, container, false);

        startNewXOCasualMode = view.findViewById(R.id.rank_start_X_O);
        startNewHangmanCasualMode = view.findViewById(R.id.rank_start_hangman);

        startNewXOCasualMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),XOOffline.class);
                startActivity(intent);

            }
        });
        startNewHangmanCasualMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),HangmanOffline.class);
                startActivity(intent);
            }
        });


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
