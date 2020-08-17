package com.example.flashlight;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SeekBar flashlightSB;
    TextView flashlightTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flashlightSB = findViewById(R.id.flashlightSB);
        flashlightTV = findViewById(R.id.flashlightTV);
    }
}