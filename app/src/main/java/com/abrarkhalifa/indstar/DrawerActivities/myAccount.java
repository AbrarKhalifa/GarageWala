package com.abrarkhalifa.indstar.DrawerActivities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.abrarkhalifa.indstar.R;
import com.abrarkhalifa.indstar.databinding.ActivityMyAccountBinding;
import com.abrarkhalifa.indstar.model.Users;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class myAccount extends AppCompatActivity {
    FirebaseDatabase database;
    FirebaseAuth auth;
    FirebaseStorage storage;
    ActivityMyAccountBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMyAccountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();

        setSupportActionBar(binding.toolbar2);
        if(getSupportActionBar()!= null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        binding.toolbar2.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        binding.toolbar2.setTitle("");


        database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Users users  = snapshot.getValue(Users.class);
                Picasso.get()
                        .load(users.getProfilePic())
                        .placeholder(R.drawable.ic_male_user)
                        .into(binding.user);

                String email = binding.showMail.toString();
                String password = binding.changePasswordEt.toString();
                HashMap<String, Object> obj = new HashMap<>();
                obj.get(email);
                obj.get(password);

                binding.showMail.setText(users.getEmail());
                binding.changePasswordEt.setText(users.getPassword());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        binding.userAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,33);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data.getData() != null){
            Uri sFile = data.getData();
            binding.user.setImageURI(sFile);

            final StorageReference reference = storage.getReference().child("Images").child(FirebaseAuth.getInstance().getUid());
            reference.putFile(sFile).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                    reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(@NonNull Uri uri) {
                            database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid()).child("Images").setValue(sFile.toString());
                            Toast.makeText(getApplicationContext(), "profile picture updated", Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            });
        }
    }
}