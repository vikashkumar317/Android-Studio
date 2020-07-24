package com.example.learnintent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    TextView text;
    Button btn;
    private EditText ETMsg;
    public static  String REPLY_MESSAGE = "com.example.learnintent.Message";
    public final static String TAG = SecondActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        text = findViewById(R.id.TV2);
        text.setVisibility(View.VISIBLE);
        text.setText(message);

        btn = findViewById(R.id.Btn2);
        ETMsg = findViewById(R.id.ET2);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                String msg = ETMsg.getText().toString();
                replyIntent.putExtra(REPLY_MESSAGE, msg);
                Log.i("Tag","EndSecondActivity()");
                setResult(RESULT_OK, replyIntent);
                finish();
            }
        });
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
}