package com.example.implicitintent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ShareActionProvider;

public class MainActivity extends AppCompatActivity {

    Button WebsiteBtn, LocationBtn, ShareBtn;
    EditText WebsiteInfo, LocationInfo, ShareInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebsiteBtn = findViewById(R.id.WebsiteBtn);
        WebsiteInfo = findViewById(R.id.WebsiteET);
        LocationBtn = findViewById(R.id.LocationBtn);
        LocationInfo = findViewById(R.id.LocationET);
        ShareInfo = findViewById(R.id.ShareET);
        ShareBtn = findViewById(R.id.ShareBtn);

/*       /////////////////// First Method /////////////////////
        ShareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = ShareInfo.getText().toString();

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, text);
                intent.setType("text/plain");

                String title = getResources().getString(R.string.chooser_title);
                Intent chooser = Intent.createChooser(intent, title);

                if(intent.resolveActivity(getPackageManager())!=null){
                    startActivity(chooser);
                }
                else{
                    Log.d("ImplicitIntents", "Can't handle this intent!");
                }
            }
        });

 */

        ShareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = ShareInfo.getText().toString();
                ShareCompat.IntentBuilder.from(MainActivity.this).setType("text/plain").setChooserTitle("Choose App").setText(text).startChooser();
            }
        });

        LocationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Location = LocationInfo.getText().toString();
                Uri loc = Uri.parse("geo:0,0?q=" + Location);

                Intent intent = new Intent(Intent.ACTION_VIEW, loc);
                String title = getResources().getString(R.string.chooser_title);
                Intent chooser = Intent.createChooser(intent, title);

                if(intent.resolveActivity(getPackageManager())!=null){
                    startActivity(chooser);
                }
                else{
                    Log.d("ImplicitIntents", "Can't handle this intent!");
                }
            }
        });

        WebsiteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = WebsiteInfo.getText().toString();
                Uri webpage = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                String title = getResources().getString(R.string.chooser_title);
                Intent chooser = Intent.createChooser(intent, title);

                if(intent.resolveActivity(getPackageManager())!=null){
                    startActivity(chooser);
                }
                else{
                    Log.d("ImplicitIntents", "Can't handle this intent!");
                }
            }
        });
    }
}