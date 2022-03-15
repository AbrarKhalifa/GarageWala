package com.abrarkhalifa.indstar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.abrarkhalifa.indstar.databinding.ActivityForgotBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotActivity extends AppCompatActivity {
    private ActivityForgotBinding binding;
    private FirebaseAuth auth;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityForgotBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();

        binding.backlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), sign_in.class));
                finish();

            }
        });

        binding.forgotpagepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = binding.forgotEmail.getText().toString().trim();
                if (TextUtils.isEmpty(email)) {
                    binding.forgotEmail.setError("required!");
                } else {
                    forgetPassword();
                }
            }

            private void forgetPassword() {
                binding.progressbarforgot.setVisibility(View.VISIBLE);
                auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            binding.progressbarforgot.setVisibility(View.INVISIBLE);

                            Toast.makeText(ForgotActivity.this, "check your email to reset password", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),sign_in.class));
                            finish();
                        }else {
                            binding.progressbarforgot.setVisibility(View.INVISIBLE);
                            Toast.makeText(ForgotActivity.this, "Error : "+task.getException().getMessage().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        binding.progressbarforgot.setVisibility(View.INVISIBLE);
                        Toast.makeText(ForgotActivity.this, "something went wrong", Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });
    }
}