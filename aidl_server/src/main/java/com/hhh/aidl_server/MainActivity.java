package com.hhh.aidl_server;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private static final String Tag = MainActivity.class.getSimpleName();
    private TextView data_text;
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.e(Tag,"onNewIntent()");
        showData(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        data_text = findViewById(R.id.data_text);
        Log.e(Tag,"onCreate()");
        showData(getIntent());
    }

    private void showData(Intent intent){
        String data = intent.getStringExtra("server_data");
        if(TextUtils.isEmpty(data))
            return;
        data_text.setText(data);
    }

}
