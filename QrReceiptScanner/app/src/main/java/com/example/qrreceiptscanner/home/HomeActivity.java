package com.example.qrreceiptscanner.home;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qrreceiptscanner.R;

public class HomeActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		final RecyclerView rv = findViewById(R.id.home_rv);
		rv.setAdapter(new HomeRvAdapter());
	}
}