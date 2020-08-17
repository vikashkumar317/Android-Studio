package com.example.calllog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    String[] strNumber = {"9709825732"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_CALL_LOG)){
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CALL_LOG}, 1);
            }
            else{
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CALL_LOG}, 1);
            }
        }
        else{
            TextView textView = findViewById(R.id.callTV);
            textView.setText(getCallDetails(strNumber));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG) == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
                TextView textView = findViewById(R.id.callTV);
                textView.setText(getCallDetails(strNumber));
            }
            else{
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private String getCallDetails(String[] strNumber){
        StringBuffer callDetails = new StringBuffer();
        Cursor managedCursor = getContentResolver().query(CallLog.Calls.CONTENT_URI, null, CallLog.Calls.LIMIT_PARAM_KEY, new String[]{"2"}, CallLog.Calls.DATE + " DESC");
        int number = managedCursor.getColumnIndex(CallLog.Calls.NUMBER);
        int type = managedCursor.getColumnIndex(CallLog.Calls.TYPE);
        int date = managedCursor.getColumnIndex(CallLog.Calls.DATE);
        int duration = managedCursor.getColumnIndex(CallLog.Calls.DURATION);
        callDetails.append("Call Details:\n");
            while (managedCursor.moveToNext()) {
                String phNumber = managedCursor.getString(number);
                String callType = managedCursor.getString(type);
                String callDate = managedCursor.getString(date);
                String nPresentation = managedCursor.getString(managedCursor.getColumnIndex(CallLog.Calls.NUMBER_PRESENTATION));
                String phone_id = managedCursor.getString(managedCursor.getColumnIndex(CallLog.Calls.PHONE_ACCOUNT_ID));
                Date callDayTime = new Date(Long.valueOf(callDate));
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yy HH:mm");
                String dateString = formatter.format(callDayTime);
                String callDuration = managedCursor.getString(duration);
                String dir = null;
                int dircode = Integer.parseInt(callType);
                switch (dircode) {
                    case CallLog.Calls.OUTGOING_TYPE:
                        dir = "OUTGOING CALL";
                        break;
                    case CallLog.Calls.INCOMING_TYPE:
                        dir = "INCOMING CALL";
                        break;
                    case CallLog.Calls.MISSED_TYPE:
                        dir = "MISSED CALL";
                        break;
                }
                callDetails.append("\nPhone Number: " + phNumber + "\nPresentation No: "+nPresentation+"\nPhone id: "+phone_id+ "\nCallType: " + dir + "\nCall Date: " + dateString + "\nCall Duration: " + callDuration + " s");
                callDetails.append("\n---------------------------------");
            }
        managedCursor.close();
        return callDetails.toString();
    }
}