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
		final String userEmail = sharedPref.getString(Params.USER_EMAIL, "");

		Intent intent;
		if (!userEmail.equals("")) {
			intent = new Intent(this, HomeActivity.class);
		} else {
			intent = new Intent(this, loginScreen.class);
		}

		startActivity(intent);
		finish();
	}
}
