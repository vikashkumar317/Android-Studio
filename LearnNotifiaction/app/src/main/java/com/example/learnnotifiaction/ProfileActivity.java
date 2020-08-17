package com.example.learnnotifiaction;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;

public class ProfileActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private TextView emailTV;
    private Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mAuth = FirebaseAuth.getInstance();
        emailTV = findViewById(R.id.emailTV);
        logout = findViewById(R.id.logout);
        emailTV.setText(mAuth.getCurrentUser().getEmail());
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                startLoginActivity();
            }
        });

        FirebaseMessaging.getInstance().subscribeToTopic("weather")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "Successfully Subscribed";
                        if (!task.isSuccessful()) {
                            msg = task.getException().getMessage();
                        }
                        Toast.makeText(ProfileActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });

        // Get firebase Cloud Messaging (FCM) Token
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if(task.isSuccessful()){
                            saveToken(task.getResult().getToken());
                        }
                        else{
                            Toast.makeText(ProfileActivity.this, "Some Error Occurred", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(mAuth.getCurrentUser() == null){
            startLoginActivity();
        }
    }

    private void startLoginActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void saveToken(String token) {
        String email = mAuth.getCurrentUser().getEmail();
        User user = new User(email, token);
        DatabaseReference dbUser = FirebaseDatabase.getInstance().getReference("user");
        dbUser.child(mAuth.getCurrentUser().getUid())
                .setValue(user)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(ProfileActivity.this, "Token Saved", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(ProfileActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}