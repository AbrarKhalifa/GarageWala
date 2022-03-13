package com.abrarkhalifa.indstar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;


import com.abrarkhalifa.indstar.databinding.ActivityMainBinding;
import com.abrarkhalifa.indstar.fragmentes.garage_home;
import com.abrarkhalifa.indstar.fragmentes.track_record;
import com.abrarkhalifa.indstar.fragmentes.transaction_histories;
import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    FirebaseAuth auth;
    MeowBottomNavigation meowBottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth = FirebaseAuth.getInstance();
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        meowBottomNavigation = findViewById(R.id.bottom_navigation);

        // add menu iteams
        meowBottomNavigation.add(new MeowBottomNavigation.Model(1,R.drawable.ic_garage_home));
        meowBottomNavigation.add(new MeowBottomNavigation.Model(2,R.drawable.ic_tracking_center));
        meowBottomNavigation.add(new MeowBottomNavigation.Model(3,R.drawable.ic_transaction_history));

        meowBottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                Fragment fragment = null;
                // check conditon
                switch (item.getId()){
                    case 1:
                        // when id is 1 it means it is a home

                        fragment = new garage_home();
//
                        break;
                    case 2:
                        // when id is 2 it means it is a notification
                        fragment = new track_record();
                        break;
                    case 3:
                        // when id is 3 it means it is a info
                        fragment = new transaction_histories();
                        break;
                }
                // load fragment
                loadFragment(fragment);

            }
        });
        //set notification count
        //set home fragment initially selected
        meowBottomNavigation.show(1,true);

        meowBottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {

            }
        });
        meowBottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {

            }
        });


    }
    private void loadFragment(Fragment fragment) {
        // replace fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,fragment).commit();

    }




}