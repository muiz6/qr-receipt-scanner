package com.example.qrreceiptscanner;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.qrreceiptscanner.home.HomeActivity;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final SharedPreferences sharedPref = getSharedPreferences(Params.MY_SHARED_PREFS,
				MODE_PRIVATE);
		final int userId = sharedPref.getInt(Params.USER_ID, -1);
		Intent intent;
		if (userId != -1) {
			intent = new Intent(this, HomeActivity.class);
		} else {
			intent = new Intent(this, loginScreen.class);
		}
		startActivity(intent);
	}
}
