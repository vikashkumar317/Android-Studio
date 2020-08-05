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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private BottomNavigationView navigationView;
    private ActionBar actionBar;
    public final static String CURRENT_FRAGMENT = "cuurentFragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        navigationView =  findViewById(R.id.navigation);

        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();

        if(savedInstanceState != null){
            actionBar.setTitle(savedInstanceState.getString("Title"));
            loadFragment(getSupportFragmentManager().getFragment(savedInstanceState, CURRENT_FRAGMENT));
        }
        else{
            actionBar.setTitle("Home");
            loadFragment(new HomeFragment());
        }

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

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.frameContainer);
        getSupportFragmentManager().putFragment(outState, CURRENT_FRAGMENT, currentFragment);
        outState.putString("Title", (String) actionBar.getTitle());
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameContainer, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}