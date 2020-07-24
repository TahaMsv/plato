package com.ApSpring.plato.Games;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.ApSpring.plato.NetworkHandlerThread;
import com.ApSpring.plato.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class GameFragment extends Fragment {
    CircleImageView xo_button, hangman_button;
    View view;
//    public static NetworkHandlerThread netThread;

    public GameFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

//        netThread = new NetworkHandlerThread();
//        netThread.start();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_game, container, false);

        ViewPager viewPager = view.findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        TabLayout tabs = view.findViewById(R.id.result_tabs);
        tabs.setupWithViewPager(viewPager);

//        xo_button = view.findViewById(R.id.X_OButton);
//        xo_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), TicTocToe.class);
//                startActivity(intent);
//            }
//        });
//        hangman_button = view.findViewById(R.id.HangMan_Button);
//
//        hangman_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), Hangman.class);
//                startActivity(intent);
//            }
//        });
        return view;
    }

    private void setupViewPager(ViewPager viewPager){
        Adapter adapter = new Adapter(getChildFragmentManager());
        adapter.addFragment(new CasualGame(), "Offline");
        adapter.addFragment(new RankedFragment(), "Ranked");
        adapter.addFragment(new LeaderBoardFragment(), "Leader Board");
        viewPager.setAdapter(adapter);
    }

    static class Adapter extends FragmentPagerAdapter{

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public Adapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title){
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
