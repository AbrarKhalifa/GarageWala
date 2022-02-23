package com.abrarkhalifa.indstar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.Toast;

import com.abrarkhalifa.indstar.databinding.ActivitySignUpBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.InputStream;
import java.util.Objects;

public class sign_up extends AppCompatActivity implements View.OnClickListener {
    ActivitySignUpBinding binding;
    FirebaseAuth auth;
    Uri imageUri;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        binding.signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signupMethod();
            }
        });
        binding.googleSignup.setOnClickListener(this);
        binding.facebookSignup.setOnClickListener(this);
        binding.alreadyacc.setOnClickListener(this);

        binding.browseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dexter.withContext(getApplicationContext())
                        .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                                Intent intent = new Intent();
                                intent.setType("image/*");
                                intent.setAction(Intent.ACTION_GET_CONTENT);
                                startActivityForResult(intent, 33);

                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                                permissionToken.continuePermissionRequest();
                            }
                        }).check();

            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 33 && resultCode == RESULT_OK) {
            assert data != null;
            imageUri = data.getData();
            binding.setImage.setImageURI(imageUri);

        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signup: {
                signupMethod();
                break;
            }
            case R.id.google_signup: {
                googleSignup();
                break;
            }
            case R.id.facebook_signup: {
                facebookSignup();
                break;
            }
            case R.id.alreadyacc: {
                startActivity(new Intent(getApplicationContext(), sign_in.class));
                finish();
                break;
            }
        }

    }


    public String getExtention() {
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(getContentResolver().getType(imageUri));
    }

    private void signupMethod() {
        String username = binding.usernameSignup.getText().toString();
        String email = binding.emailSignup.getText().toString();
        String password = binding.passwordSignup.getText().toString();
        if (!TextUtils.isEmpty(username) || !TextUtils.isEmpty(email) || !TextUtils.isEmpty(password) ){
            binding.progressbar.setVisibility(View.VISIBLE);

            storageReference = firebaseStorage.getReference("Images/"+System.currentTimeMillis()+"."+getExtention());
            storageReference.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Toast.makeText(sign_up.this, "image upload successful", Toast.LENGTH_SHORT).show();
                            }
                    });
                }
            });
            auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        AuthModel model = new AuthModel();
                        model.setUsername(username);
                        model.setEmail(email);
                        model.setPassword(password);
                        model.setImageuri(imageUri.toString());
                        String id = task.getResult().getUser().getUid();

                        firebaseDatabase.getReference().child("Users").child(id).setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                binding.progressbar.setVisibility(View.INVISIBLE);
                                binding.usernameSignup.setText("");
                                binding.emailSignup.setText("");
                                binding.passwordSignup.setText("");
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                finish();
                                Toast.makeText(sign_up.this, "Successfully signup", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                binding.progressbar.setVisibility(View.INVISIBLE);

                                Toast.makeText(sign_up.this, "signup failed", Toast.LENGTH_SHORT).show();
                            }
                        });


                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    binding.progressbar.setVisibility(View.INVISIBLE);
                    Toast.makeText(sign_up.this, "failed to upload", Toast.LENGTH_SHORT).show();

                }
            });


        }else {
            Toast.makeText(this, "fill all the details", Toast.LENGTH_SHORT).show();
        }

    }

    private void facebookSignup() {

    }

    private void googleSignup() {
    }
}