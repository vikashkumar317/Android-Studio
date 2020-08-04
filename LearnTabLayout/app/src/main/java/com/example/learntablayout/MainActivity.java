package com.example.learntablayout;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        tabLayout = findViewById(R.id.tabs);
        viewPager = findViewById(R.id.viewPager);

        setSupportActionBar(toolbar);
        setUpViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        setUpIcon();
    }

    private void setUpIcon(){
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_cake);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_alarm);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_camera);
        tabLayout.getTabAt(3).setIcon(R.drawable.ic_call);
        tabLayout.getTabAt(4).setIcon(R.drawable.ic_fingerprint);
    }

    private void setUpViewPager(ViewPager viewPager) {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new FirstFragment(), "BirthDay");
        viewPagerAdapter.addFragment(new SecondFragment(), "Alarm");
        viewPagerAdapter.addFragment(new ThirdFragment(), "Camera");
        viewPagerAdapter.addFragment(new FourthFragment(), "Call");
        viewPagerAdapter.addFragment(new FifthFragment(), "FingerPrint");
        viewPager.setAdapter(viewPagerAdapter);
    }
}