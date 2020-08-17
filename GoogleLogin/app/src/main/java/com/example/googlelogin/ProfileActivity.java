package com.example.googlelogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;

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
    }

    @Override
    protected void onResume() {
        super.onResume();
        emailTV.setText(mAuth.getCurrentUser().getEmail());
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AccessToken accessToken = AccessToken.getCurrentAccessToken();
                if(accessToken != null && !accessToken.isExpired()){
                    LoginManager.getInstance().logOut();
                }
                mAuth.signOut();
                startLoginActivity();
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
}