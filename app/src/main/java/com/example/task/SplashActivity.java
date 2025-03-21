package com.example.task;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_TIME = 2000;  // 2 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Check if the user is already signed in
        new Handler().postDelayed(() -> {
            GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

            if (account != null) {
                // User is already signed in → Navigate to MainActivity
                Toast.makeText(this, "Welcome back: " + account.getEmail(), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            } else {
                // User not signed in → Show intro screens
                startActivity(new Intent(SplashActivity.this, IntroActivity.class));
            }

            finish();  // Finish splash activity
        }, SPLASH_TIME);
    }
}
