package com.abrarkhalifa.indstar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.abrarkhalifa.indstar.databinding.ActivityMainBinding;
import com.abrarkhalifa.indstar.fragmentss.browseImage;
import com.abrarkhalifa.indstar.fragmentss.home;
import com.abrarkhalifa.indstar.fragmentss.profile;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth = FirebaseAuth.getInstance();
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // this is for fragment
       getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new home()).commit();

        binding.bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment chat = null;
                switch (item.getItemId()) {
                    case R.id.home_screen:
                        chat = new home();
                        break;
                    case R.id.addImg_screen:
                        chat = new browseImage();
                        break;
                    case R.id.profile_screen:
                        chat = new profile();
                        break;
                }
               getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,chat).commit();
                return true;
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mymenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        if (item.getItemId() == R.id.logout) {
            auth.signOut();
            startActivity(new Intent(getApplicationContext(), sign_in.class));
            finish();
        }
        return super.onOptionsItemSelected(item);

    }

    // for back alert box


    @Override
    public void onBackPressed() {

        DialogPlus dialogPlus = DialogPlus.newDialog(MainActivity.this)
                .setContentBackgroundResource(R.color.transparent)
                .setContentHolder(new ViewHolder(R.layout.alert_dialoguebox_custom))
                .setGravity(Gravity.CENTER)
                .setExpanded(true, 1000)
                .setCancelable(true)
                .create();
        View view = dialogPlus.getHolderView();
        Button btnNo = view.findViewById(R.id.btnNo);
        Button btnExit = view.findViewById(R.id.btnExit);
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogPlus.dismiss();
            }
        });
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
            }
        });

        dialogPlus.show();
    }
}