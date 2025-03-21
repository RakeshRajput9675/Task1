package com.example.task;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.task.adapter.ImageAdapter;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import java.util.ArrayList;
import java.util.List;

public class IntroActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private Handler handler;
    private Runnable runnable;
    private int currentPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_intro);

        viewPager = findViewById(R.id.viewpager);

        // Add images to the list
        List<Integer> imageList1 = new ArrayList<>();
        imageList1.add(R.drawable.baby2);
        imageList1.add(R.drawable.goldcoin);
        imageList1.add(R.drawable.intro1);

        List<Integer> imageList2 = new ArrayList<>();
        imageList2.add(R.drawable.frame);
        imageList2.add(R.drawable.frame);
        imageList2.add(R.drawable.frame);

        ImageAdapter adapter = new ImageAdapter(imageList1, imageList2);
        viewPager.setAdapter(adapter);

        // Check if user is already signed in
        if (isUserLoggedIn()) {
            navigateToMainActivity();
        } else {
            startAutoSliding(imageList1.size());
        }
    }

    /**
     * Start auto-sliding images
     */
    private void startAutoSliding(int imageCount) {
        handler = new Handler(Looper.getMainLooper());

        runnable = new Runnable() {
            @Override
            public void run() {
                if (currentPosition < imageCount) {
                    viewPager.setCurrentItem(currentPosition++, true);
                    handler.postDelayed(this, 1500);  // 1.5 seconds delay
                } else {
                    navigateToLoginScreen();
                }
            }
        };

        handler.postDelayed(runnable, 1500);
    }

    /**
     * Check if user is already signed in
     */
    private boolean isUserLoggedIn() {
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        return account != null;  // User is signed in if account exists
    }

    /**
     * Navigate to MainActivity if already logged in
     */
    private void navigateToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * Navigate to Login Screen after sliding finishes
     */
    private void navigateToLoginScreen() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();  // Close the intro activity
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (handler != null) {
            handler.removeCallbacks(runnable);  // Prevent memory leaks
        }
    }
}
