package com.example.qrreceiptscanner;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class OrderReceipt extends AppCompatActivity {
	public TextView OrderNotView;
	public TextView EmpNtView;
	public TextView DatetView;
	public TextView PN1tView;
	public TextView PP1tView;
	public TextView PN2tView;
	public TextView PP2tView;
	public TextView SubTtView;
	public TextView DiscountView;
	public TextView TaxtView;
	public TextView TotaltView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order_receipt);

		OrderNotView = findViewById(R.id.OrderNumber);
		EmpNtView = findViewById(R.id.EmpName);
		DatetView = findViewById(R.id.OrderDataValue);

		PN1tView = findViewById(R.id.ProductN1);
		PP1tView = findViewById(R.id.ProductP1);
		PN2tView = findViewById(R.id.ProductN2);
		PP2tView = findViewById(R.id.ProductP2);

		SubTtView = findViewById(R.id.Subtotal);
		DiscountView = findViewById(R.id.Discount);
		TaxtView = findViewById(R.id.Tax);
		TotaltView = findViewById(R.id.TotalPrice);

		String contents = getIntent().getStringExtra("SCAN_RESULT");

//		tstView.setText(contents);

		String string = contents;
		String[] parts = string.split(",");
		String part1 = parts[0];
		String part2 = parts[1];
		String part3 = parts[2];
		String part4 = parts[3];
		String part5 = parts[4];
		String part6 = parts[5];
		String part7 = parts[6];
		String part8 = parts[7];
		String part9 = parts[8];
		String part10 = parts[9];
		String part11 = parts[10];

		OrderNotView.setText(parts[0]);
		EmpNtView.setText(parts[1]);
		DatetView.setText(parts[2]);

		PN1tView.setText(parts[3]);
		PP1tView.setText(parts[4]);
		PN2tView.setText(parts[5]);
		PP2tView.setText(parts[6]);

		SubTtView.setText(parts[7]);
		DiscountView.setText(parts[8]);
		TaxtView.setText(parts[9]);
		TotaltView.setText(parts[10]);
	}
}
