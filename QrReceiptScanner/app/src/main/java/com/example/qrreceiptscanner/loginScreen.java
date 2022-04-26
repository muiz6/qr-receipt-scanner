package com.example.qrreceiptscanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class loginScreen extends AppCompatActivity {
    private EditText email;
    private EditText password;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        TextView textView = findViewById(R.id.register_text_view);
        String text = "New To Logistics? Register";
        SpannableString ss = new SpannableString(text);
        ClickableSpan clickableSpan1 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                System.out.println("CLICKED");
            }
        };

        ss.setSpan(new ForegroundColorSpan(Color.BLUE),17,26, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);

        textView.setText(ss);
        textView.setMovementMethod(LinkMovementMethod.getInstance());

        email = findViewById(R.id.emailText);
        password = findViewById(R.id.passwordText);

        auth = FirebaseAuth.getInstance();

    }

    public void login(View view) {
        String sEmail = email.getText().toString();
        String sPassword = password.getText().toString();
        System.out.println(sEmail + " " + sPassword);
        if(sEmail.isEmpty() || sPassword.isEmpty()){
            Toast toast = Toast.makeText(getApplicationContext(), "empty fields", Toast.LENGTH_SHORT);
            toast.show();
        }else{
            auth.signInWithEmailAndPassword(sEmail,sPassword).addOnCompleteListener(this, new OnCompleteListener<AuthResult>(){
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()) {
                        // there was an error
                        if (password.length() < 6) {
                            Toast toast = Toast.makeText(getApplicationContext(), "short password", Toast.LENGTH_SHORT);
                            toast.show();
                        } else {
                            Toast toast = Toast.makeText(getApplicationContext(), "auth failed", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    } else {
                        Toast toast = Toast.makeText(getApplicationContext(), "login, go to next screen", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
            });
        }
    }
}