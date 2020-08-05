package com.example.learntablayout;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private BottomNavigationView navigationView;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        navigationView =  findViewById(R.id.navigation);

        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setTitle("Home");
        loadFragment(new HomeFragment());
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.home1:
                        actionBar.setTitle("Home");
                        loadFragment(new HomeFragment());
                        return true;
                    case R.id.gift:
                        actionBar.setTitle("My Gift");
                        loadFragment(new GiftFragment());
                        return true;
                    case R.id.cart:
                        actionBar.setTitle("Cart");
                        loadFragment(new CartFragment());
                        return true;
                    case R.id.profile:
                        actionBar.setTitle("Profile");
                        loadFragment(new ProfileFragment());
                        return true;
                    case R.id.setting:
                        actionBar.setTitle("Setting");
                        loadFragment(new SettingFragment());
                        return true;
                }
                return false;
            }
        });
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameContainer, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}