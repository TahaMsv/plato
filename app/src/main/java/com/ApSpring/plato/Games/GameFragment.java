package com.ApSpring.plato.Games;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ApSpring.plato.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class GameFragment extends Fragment {
    Button xo_button;
    View view;
    public GameFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_game, container, false);
        xo_button = view.findViewById(R.id.X_OButton);
        xo_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TicTocToe.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
