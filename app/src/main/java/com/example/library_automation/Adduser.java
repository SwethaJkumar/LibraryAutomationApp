package com.example.library_automation;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.library_automation.databinding.ActivityAdduserBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Adduser extends AppCompatActivity {
    ActivityAdduserBinding binding;
    //DatabaseReference databaseReference;
    FirebaseDatabase db;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdduserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        binding.adduser.setOnClickListener(view -> {
            String rollno = binding.rno1.getText().toString();
            String fname = binding.name1.getText().toString();
            String lname = binding.lname1.getText().toString();
            String emailid = binding.email1.getText().toString();
            String password=binding.pass1.getText().toString();
            String course = binding.Course1.getText().toString();
            String dept = binding.dept1.getText().toString();
            String year1 = binding.year1.getText().toString();
            int year = Integer.parseInt(year1);
            String contact = binding.Contact1.getText().toString();

            addUser(rollno,fname,lname,emailid,password,course,dept,year,contact);
        });
    }
    private void addUser(String rollno,String fname,String lname,String emailid,String password,String course,String dept,int year,String contact) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(emailid, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            HashMap<String, Object> User = new HashMap<>();
                            User.put("Roll no",rollno);
                            User.put("First Name",fname);
                            User.put("Last Name",lname);
                            User.put("Email",emailid);
                            User.put("Password",password);
                            User.put("Course",course);
                            User.put("Department",dept);
                            User.put("Year",year);
                            User.put("Contact",contact);

                            User user = new User(rollno,fname,lname,emailid,password,course,dept,year,contact);
                            db = FirebaseDatabase.getInstance();
                            reference = db.getReference("Users");
                            // databaseReference = FirebaseDatabase.getInstance().getReference("Users");
                            //databaseReference.child(rollno).updateChildren(User).addOnCompleteListener(new OnCompleteListener() {
                            // reference.child("Users").child(user.getRollno()).setValue(user).addOnCompleteListener(task -> {
                            reference.child(binding.rno1.getText().toString()).updateChildren(User).addOnCompleteListener(taskAdd -> {
                                // reference.addOnCompleteListener(new OnCompleteListener() {
                                if (taskAdd.isSuccessful()){
                                    binding.rno1.setText("");
                                    binding.name1.setText("");
                                    binding.lname1.setText("");
                                    binding.email1.setText("");
                                    binding.pass1.setText("");
                                    binding.Course1.setText("");
                                    binding.dept1.setText("");
                                    binding.year1.setText("");
                                    binding.Contact1.setText("");
                                    Toast.makeText(Adduser.this,"Successfuly Updated",Toast.LENGTH_SHORT).show();

                                }else {

                                    Toast.makeText(Adduser.this,"Failed to Update",Toast.LENGTH_SHORT).show();

                                }

                            });


                            Log.d(TAG, "createUserWithEmail:success");

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(Adduser.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
     //   String r=rollno;
       // if (!firstName.isEmpty() && !lastName.isEmpty() && !email.isEmpty() && !rollno.isEmpty()){

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