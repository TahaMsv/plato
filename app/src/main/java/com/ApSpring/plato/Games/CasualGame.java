package com.ApSpring.plato.Games;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ApSpring.plato.MainPage;
import com.ApSpring.plato.R;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class CasualGame extends Fragment {

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
        return inflater.inflate(R.layout.fragment_casual_game, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}