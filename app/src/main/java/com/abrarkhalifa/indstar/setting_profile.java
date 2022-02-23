package com.abrarkhalifa.indstar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.abrarkhalifa.indstar.databinding.ActivitySettingProfileBinding;
import com.google.firebase.auth.FirebaseAuth;

public class setting_profile extends AppCompatActivity {
   private ActivitySettingProfileBinding binding;
   private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySettingProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth = FirebaseAuth.getInstance();




    }
}