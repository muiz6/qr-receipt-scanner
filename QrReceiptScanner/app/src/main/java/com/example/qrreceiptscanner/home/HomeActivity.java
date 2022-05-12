package com.example.qrreceiptscanner.home;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qrreceiptscanner.MainActivity;
import com.example.qrreceiptscanner.MyApp;
import com.example.qrreceiptscanner.Params;
import com.example.qrreceiptscanner.R;
import com.example.qrreceiptscanner.SearchTab;
import com.example.qrreceiptscanner.scannerView;

import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class HomeActivity extends AppCompatActivity {
	SharedPreferences.Editor prefEditor;
	Disposable receiptDisposable;
	MyApp app;
	RecyclerView rv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		prefEditor = getSharedPreferences(Params.MY_SHARED_PREFS, MODE_PRIVATE).edit();
		rv = findViewById(R.id.home_rv);
		app = (MyApp) getApplication();
	}

	@Override
	protected void onResume() {
		super.onResume();
		app.getReceipts().subscribe(new Observer<List<Map<String, Object>>>() {
			@Override
			public void onSubscribe(@NonNull Disposable d) {
				receiptDisposable = d;
			}

			@Override
			public void onNext(@NonNull List<Map<String, Object>> receipts) {
				rv.setAdapter(new HomeRvAdapter(receipts));
				rv.getAdapter().notifyDataSetChanged();
			}

			@Override
			public void onError(@NonNull Throwable e) {}

			@Override
			public void onComplete() {}
		});
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (receiptDisposable != null) {
			receiptDisposable.dispose();
			receiptDisposable = null;
		}
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
