package com.example.qrreceiptscanner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class dummyActivity extends AppCompatActivity {
    public static TextView tView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tView = findViewById(R.id.textView);
        setContentView(R.layout.activity_dummy);
    }
}