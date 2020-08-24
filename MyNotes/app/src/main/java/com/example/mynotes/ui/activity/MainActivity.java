package com.example.mynotes.ui.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.customview.widget.Openable;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.mynotes.R;

public class MainActivity extends AppCompatActivity{

    private NavController navContoller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navContoller = Navigation.findNavController(this, R.id.noteFragment);
        NavigationUI.setupActionBarWithNavController(this, navContoller);
    }

    @Override
    public boolean onSupportNavigateUp() {
        super.onSupportNavigateUp();
        return NavigationUI.navigateUp(navContoller, (Openable) null);
    }

}