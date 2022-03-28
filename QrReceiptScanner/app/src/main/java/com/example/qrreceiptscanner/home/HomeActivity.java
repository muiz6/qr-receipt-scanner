package com.example.qrreceiptscanner.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qrreceiptscanner.R;
import com.example.qrreceiptscanner.scannerView;

public class HomeActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		final RecyclerView rv = findViewById(R.id.home_rv);
		rv.setAdapter(new HomeRvAdapter());
	}

	public void onScan(View vw) {
		final Intent intent = new Intent(this, scannerView.class);
		startActivity(intent);
	}
}
