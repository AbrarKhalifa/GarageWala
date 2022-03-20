package com.abrarkhalifa.indstar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.abrarkhalifa.indstar.databinding.ActivityCountDownBinding;
import com.abrarkhalifa.indstar.databinding.ActivityMainBinding;

import java.util.Locale;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class countDown extends AppCompatActivity {
    public ActivityCountDownBinding binding;
    int restProgress = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCountDownBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbarExcercise);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        binding.toolbarExcercise.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        setRestProgressBar();


    }
    private void setRestProgressBar(){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (restProgress >= 0){
                    binding.tvTimer.setText(""+restProgress);
                    binding.progressBar.setProgress(restProgress);
                    restProgress--;
                    handler.postDelayed(this,500);
                }else {
                    handler.removeCallbacks(this);
                }

            }
        },500);


    }


}