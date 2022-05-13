package com.example.qrreceiptscanner;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.qrreceiptscanner.home.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {

	private EditText useremail, username, password;
	Button button;

	FirebaseAuth FAuth;
	DatabaseReference databaseReference;
	FirebaseDatabase firebaseDatabase;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);

		useremail = findViewById(R.id.Email);
		username = findViewById(R.id.username);
		password = findViewById(R.id.userPassword);

		button = findViewById(R.id.btn);
		// Set on Click Listener on Registration button
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//registerNewUser();
				String email, upassword, user;
				email = useremail.getText().toString();
				upassword = password.getText().toString();
				user = username.getText().toString();

				if (TextUtils.isEmpty(email)) {
					useremail.requestFocus();
					Toast.makeText(getApplicationContext(),
							"Please enter email!!",
							Toast.LENGTH_LONG)
							.show();
					return;
				}
				if (TextUtils.isEmpty(user)) {
					username.requestFocus();
					Toast.makeText(getApplicationContext(),
							"Please enter user name!!",
							Toast.LENGTH_LONG)
							.show();
					return;
				}
				if (TextUtils.isEmpty(upassword)) {
					password.requestFocus();
					Toast.makeText(getApplicationContext(),
							"Please enter password!!",
							Toast.LENGTH_LONG)
							.show();
					return;
				}


				Toast.makeText(SignUpActivity.this, "User Has been added successfully", Toast.LENGTH_SHORT).show();
				final Intent intent = new Intent(SignUpActivity.this, loginScreen.class);
				startActivity(intent);
				finish();

				//registerUser(email, user, upassword);
			}
		});


	}


	public void registerUser(String email, String user, String password) {

		FAuth.createUserWithEmailAndPassword(email, password)
				.addOnCompleteListener(new OnCompleteListener<AuthResult>() {
					@Override
					public void onComplete(@NonNull Task<AuthResult> task) {

						if (task.isSuccessful()) {
							User user1 = new User(email, user, password);
							FirebaseDatabase.getInstance().getReference("users")
									.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
									.setValue(user1).addOnCompleteListener(new OnCompleteListener<Void>() {
								@Override
								public void onComplete(@NonNull Task<Void> task) {
									if (task.isSuccessful()) {
										Toast.makeText(SignUpActivity.this, "User Has been added successfully", Toast.LENGTH_SHORT).show();
									} else {
										Toast.makeText(SignUpActivity.this, "Fail to register User", Toast.LENGTH_SHORT).show();
									}
								}
							});
						}
					}
				});
	}


	public void onLogin(View vw) {
		final Intent intent = new Intent(this, loginScreen.class);
		startActivity(intent);
		finish();
	}

	public void exit(View vw) {
		finish();
	}


//	@Override
//	public void onClick(View v) {
//		switch (v.getId())
//		case: R.id.btn:
//	}
}