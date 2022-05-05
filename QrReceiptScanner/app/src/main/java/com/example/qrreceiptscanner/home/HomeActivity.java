package com.example.qrreceiptscanner.home;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qrreceiptscanner.DBHelper;
import com.example.qrreceiptscanner.MainActivity;
import com.example.qrreceiptscanner.Params;
import com.example.qrreceiptscanner.R;
import com.example.qrreceiptscanner.SearchTab;
import com.example.qrreceiptscanner.scannerView;

public class HomeActivity extends AppCompatActivity {
	SharedPreferences.Editor prefEditor;
	DBHelper db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		prefEditor = getSharedPreferences(Params.MY_SHARED_PREFS, MODE_PRIVATE).edit();

		db = new DBHelper(this);

		final RecyclerView rv = findViewById(R.id.home_rv);
		rv.setAdapter(new HomeRvAdapter(db.readAllData()));
	}

	public void onScan(View vw) {
		final Intent intent = new Intent(this, scannerView.class);
		startActivity(intent);
	}

	public void onSearch(View vw) {
		final Intent intent = new Intent(this, SearchTab.class);
		startActivity(intent);
	}

	public void onSignOut(View vw) {
		prefEditor.remove(Params.USER_EMAIL);
		prefEditor.commit();
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		finish();
	}
}
