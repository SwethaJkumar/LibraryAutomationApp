package com.example.library_automation;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.library_automation.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Admin_login extends AppCompatActivity {
    ActivityMainBinding binding;
    DatabaseReference reference;
    private FirebaseAuth mAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    EditText e1,e2,e3;
Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        mAuth = FirebaseAuth.getInstance();
        b1 = findViewById(R.id.alogin);
        e1 = findViewById(R.id.user);
        e2 = findViewById(R.id.pass);
        e3 = findViewById(R.id.pin);
        String pin = e3.getText().toString();
        String pinmatch = "admin123";
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // if (pin.length()==0) {
                //   Toast.makeText(getApplicationContext(), "invalid", Toast.LENGTH_SHORT).show();
                //  Intent intent = new Intent(getApplicationContext(), adminmenu.class);
                // startActivity(intent);
                b1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        loginAdminAccount();
                    }
                });
                //  }
                //  else
                //  {
                //     Toast.makeText(getApplicationContext(),"valid",Toast.LENGTH_SHORT).show();
                // }
            }
        });
    }
        private void loginAdminAccount()
        {

            // show the visibility of progress bar to show loading
           // progressbar.setVisibility(View.VISIBLE);

            // Take the value of two edit texts in Strings
            String email, password,pin,pin1;
            pin1="admin123";
            email = e1.getText().toString();
            password = e2.getText().toString();
            pin=e3.getText().toString();

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
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()&&(pin1.equals(pin))) {
                        Toast.makeText(getApplicationContext(), "Login successful!!", Toast.LENGTH_LONG).show();

                        // hide the progress bar
                        // ProgressBar progressBar;
                        // progressBar.setVisibility(View.GONE);

                        // if sign-in is successful
                        // intent to home activity
                        //   DataSnapshot dataSnapshot = task.getResult();
                        //  String firstName = String.valueOf(dataSnapshot.child("First Name:").getValue());
                        Intent intent = new Intent(getApplicationContext(),Adminhome.class);
                      //  intent.putExtra("Email", e1.getText().toString());
                        //  intent.putExtra("name",userTextView.getText().toString());
                        startActivity(intent);
                    } else {

                        // sign-in failed
                        Toast.makeText(getApplicationContext(), "Login failed!!", Toast.LENGTH_LONG).show();

                        // hide the progress bar
                       // progressbar.setVisibility(View.GONE);
                    }
                }
            });
        }
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
    }
