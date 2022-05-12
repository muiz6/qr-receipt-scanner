package com.example.qrreceiptscanner;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class SignUpActivity extends AppCompatActivity {

	EditText email, name, cell;
	Button regbtn;

	FirebaseDatabase db = FirebaseDatabase.getInstance();
	DatabaseReference reference = db.getReference().child("users");

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);

		email = findViewById(R.id.user_email);
		name = findViewById(R.id.user_name);
		cell = findViewById(R.id.user_cellphone);

		regbtn = findViewById(R.id.btn);

		regbtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String Email = email.getText().toString();
				String Name = name.getText().toString();
				String Cell = cell.getText().toString();

				HashMap<String, String> usermap = new HashMap<>();
				usermap.put("name", Name);
				usermap.put("email", Email);
				usermap.put("cell", Cell);

				reference.push().setValue(usermap);
			}
		});
	}
}