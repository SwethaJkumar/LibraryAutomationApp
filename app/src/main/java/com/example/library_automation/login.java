package com.example.library_automation;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class login extends AppCompatActivity {
    private EditText userTextView, passwordTextView;
    private Button Btn;
    Button Btn1,Btn2;
    private ProgressBar progressbar;

    private FirebaseAuth mAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // taking instance of FirebaseAuth
        mAuth = FirebaseAuth.getInstance();

        // initialising all views through id defined above
        userTextView = findViewById(R.id.user);
        passwordTextView = findViewById(R.id.pass);
        Btn = findViewById(R.id.login);
       progressbar = findViewById(R.id.progressBar);

        // Set on Click Listener on Sign-in button
        Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                loginUserAccount();
            }
        });
        Btn1 = findViewById(R.id.guser);
        Btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(login.this, Menu_Activity.class);
                startActivity(intent);
            }
        });
        Btn2 = findViewById(R.id.admin);
        Btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(login.this, Admin_login.class);
                startActivity(intent);
            }
        });
    }

   private void loginUserAccount()
    {

        // show the visibility of progress bar to show loading
        progressbar.setVisibility(View.VISIBLE);

        // Take the value of two edit texts in Strings
        String email, password;
        email = userTextView.getText().toString();
        password = passwordTextView.getText().toString();

        // validations for input email and password
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Please enter email!!", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Please enter password!!", Toast.LENGTH_LONG).show();
            return;
        }
        // private ProgressBar progressbar;
        // signin existing user
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(
                    @NonNull Task<AuthResult> task)
            {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Login successful!!", Toast.LENGTH_LONG).show();

                    // hide the progress bar
                    // ProgressBar progressBar;
                    // progressBar.setVisibility(View.GONE);

                    // if sign-in is successful
                    // intent to home activity
                    Intent intent = new Intent(getApplicationContext(),Menu_Activity.class);
                    intent.putExtra("username",userTextView.getText().toString());
                    startActivity(intent);
                }

                else {

                    // sign-in failed
                    Toast.makeText(getApplicationContext(), "Login failed!!",Toast.LENGTH_LONG).show();

                    // hide the progress bar
                   progressbar.setVisibility(View.GONE);
                }
            }
        });
    }
}