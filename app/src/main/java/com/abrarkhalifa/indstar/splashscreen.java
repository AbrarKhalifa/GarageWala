package com.abrarkhalifa.indstar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.abrarkhalifa.indstar.databinding.ActivitySplashscreenBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class splashscreen extends AppCompatActivity {
    ActivitySplashscreenBinding binding;
    FirebaseAuth auth;
    FirebaseUser currentuser;
    Animation fadein;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        binding = ActivitySplashscreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth = FirebaseAuth.getInstance();

        fadein = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.main_logo_trans);
        binding.mainLogo.startAnimation(fadein);
        binding.tv.startAnimation(fadein);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (auth.getCurrentUser() != null){
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    finish();
                }else {
                    Intent intent = new Intent(getApplicationContext(),sign_up.class);
                    startActivity(intent);
                }
                finish();
            }
        },4000);



    }
}