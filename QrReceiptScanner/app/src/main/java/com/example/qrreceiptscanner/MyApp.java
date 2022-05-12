package com.example.qrreceiptscanner;

import android.app.Application;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

public class MyApp extends Application {
	DBHelper dbHelper;
	BehaviorSubject<List<Map<String, Object>>> sbjReceipts = BehaviorSubject.create();

	@Override
	public void onCreate() {
		super.onCreate();
		dbHelper = new DBHelper(this);
	}

	public Observable<List<Map<String, Object>>> getReceipts() {
		if (!sbjReceipts.hasValue()) {
			sbjReceipts.onNext(dbHelper.readAllData());
		}
		return sbjReceipts;
	}

	public void notifyReceiptsChanged() {
		sbjReceipts.onNext(dbHelper.readAllData());
	}
}
