package com.abrarkhalifa.indstar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.abrarkhalifa.indstar.DrawerActivities.myAccount;
import com.abrarkhalifa.indstar.databinding.ActivityMainBinding;
import com.abrarkhalifa.indstar.fragmentes.garage_home;
import com.abrarkhalifa.indstar.fragmentes.track_record;
import com.abrarkhalifa.indstar.fragmentes.transaction_histories;
import com.abrarkhalifa.indstar.model.Users;
import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.HashMap;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    FirebaseAuth auth;
    FirebaseDatabase database;
    MeowBottomNavigation meowBottomNavigation;

    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    FrameLayout frameLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth = FirebaseAuth.getInstance();
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        database = FirebaseDatabase.getInstance();

        // Write a message to the database
        auth = FirebaseAuth.getInstance();


        // this code for drawer menu

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        nav = findViewById(R.id.navmenu);
        drawerLayout = findViewById(R.id.drawer);
        frameLayout = findViewById(R.id.frameLayout);

        toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        binding.menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBottomSheetDialog();
            }
        });

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


        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                switch (item.getItemId()){
                    case R.id.myAccount:

                       Intent myacc = new Intent(getApplicationContext(), myAccount.class);

                        startActivity(myacc);
                        Toast.makeText(getApplicationContext(), "Welcome To Home", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(GravityCompat.START);

                        break;
                    case R.id.payment:
                        Toast.makeText(getApplicationContext(), "Choose Payment Options", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.share:
                        shareApp();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.help:
                        Toast.makeText(getApplicationContext(), "How We Can Help You ? ", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.Logout:
                        Toast.makeText(getApplicationContext(), "Log Out", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        auth.signOut();
                        Intent intent = new Intent(MainActivity.this,sign_in.class);
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });

        database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                View headerView = nav.getHeaderView(0);
                ImageView img = headerView.findViewById(R.id.profileImage);
                TextView tv = (TextView) headerView.findViewById(R.id.username);

                String usr = tv.toString();
                Users users  = snapshot.getValue(Users.class);
                Picasso.get()
                        .load(users.getProfilePic())
                        .placeholder(R.drawable.users_profile)
                        .into(img);

                HashMap<String, Object> obj = new HashMap<>();
                obj.get(usr);
                assert users != null;
                tv.setText(users.getUserName());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
    private void loadFragment(Fragment fragment) {
        // replace fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,fragment).commit();

    }

    private void shareApp(){
        try {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "GarageWala");
            String shareMessage= "\nLet me recommend you this application\n\n";
            shareMessage = shareMessage + "https://drive.google.com/file/d/1i1xGRJxF5XyzledoI2HoVOQAZTt9gN5H/view?usp=sharing"
                    + BuildConfig.APPLICATION_ID +"\n\n";
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
            startActivity(Intent.createChooser(shareIntent, "choose one"));
        } catch(Exception e) {
            //e.toString();
        }
    }

    private void showBottomSheetDialog() {

        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.bottom_sheet_layout);

        ImageView back = bottomSheetDialog.findViewById(R.id.backBtn);
        TextView changeTheme = bottomSheetDialog.findViewById(R.id.change_theme);
        TextView rate = bottomSheetDialog.findViewById(R.id.rate_services);
        TextView languages = bottomSheetDialog.findViewById(R.id.languges_txt);
        TextView help = bottomSheetDialog.findViewById(R.id.help);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.dismiss();
            }
        });

        changeTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Change Theme", Toast.LENGTH_SHORT).show();
            }
        });

        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Rate Our Services", Toast.LENGTH_SHORT).show();
            }
        });

        languages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Choose Languages", Toast.LENGTH_SHORT).show();
            }
        });

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Help Us to Improve", Toast.LENGTH_SHORT).show();
            }
        });


        bottomSheetDialog.show();
    }
}