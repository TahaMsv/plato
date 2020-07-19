package com.ApSpring.plato.settings;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ApSpring.plato.MainActivity;
import com.ApSpring.plato.R;
import com.ApSpring.plato.settings.AboutUs;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends Fragment {

    Button logOut;
    Button aboutUs;
    View view;

    public SettingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_setting, container, false);
        logOut = view.findViewById(R.id.logOut);
        aboutUs = view.findViewById(R.id.aboutUs);

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder b = new AlertDialog.Builder(getActivity());
                b.setMessage("Are you sure you want to log out ?")
                        .setCancelable(true)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(getActivity(), MainActivity.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("No", null);
                b.show();

            }
        });

        aboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), AboutUs.class);
                startActivity(intent);

            }
        });

        return view;
    }
}
