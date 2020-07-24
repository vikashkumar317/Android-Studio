package com.example.learnintent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button click;
    private EditText ETMsg;
    public final static String EXTRA_MESSAGE = "com.example.learnintent.Message";
    public final static int TEXT_REQUEST = 1;
    TextView text;
    public final static String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG,"OnCreate()");
        click = findViewById(R.id.Btn1);
        ETMsg = findViewById(R.id.ET1);
        text = findViewById(R.id.TV1);

        if(savedInstanceState != null){
            text.setVisibility(View.VISIBLE);
            text.setText(savedInstanceState.getString("ReplyMsg"));
            Log.i(TAG,"Hello " + savedInstanceState.getString("ReplyMsg") );
        }

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                String message = ETMsg.getText().toString();
                intent.putExtra(EXTRA_MESSAGE, message);
                startActivityForResult(intent, TEXT_REQUEST);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        System.out.println("Hello World");
        if(text.getVisibility()==View.VISIBLE){
            outState.putString("ReplyMsg",text.getText().toString());
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG,"OnStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG,"OnResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG,"OnPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG,"OnStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"OnDestroy()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG,"OnRestart()");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==TEXT_REQUEST){
            if(resultCode==RESULT_OK){
                String msg = data.getStringExtra(SecondActivity.REPLY_MESSAGE);
                text.setVisibility(View.VISIBLE);
                text.setText(msg);
            }
        }
    }
}