package com.example.library_automation;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.library_automation.databinding.ActivityDeleteuserBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Deleteuser extends AppCompatActivity {
    AlertDialog.Builder builder;
    DatabaseReference reference;
    //EditText rno,email;
    //Button del;
    ActivityDeleteuserBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_deleteuser);
       // rno = findViewById(R.id.delrno);
       // email = findViewById(R.id.delemail);
       // del = findViewById(R.id.deleteuser);
        binding = ActivityDeleteuserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        builder = new AlertDialog.Builder(this);
        binding.deleteuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String roll=binding.delrno.getText().toString();
                if (!roll.isEmpty()){
                    builder.setMessage("xyz") .setTitle("Delete User");

                    //Setting message manually and performing action on button click
                    builder.setMessage("Do you want to delete the user ?");
                      builder.setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    finish();
                                   // Toast.makeText(getApplicationContext(),"you choose yes action for alertbox",Toast.LENGTH_SHORT).show();
                                    deleteUser(roll);
                                }
                            })
                              .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                  public void onClick(DialogInterface dialog, int id) {
                                      //  Action for 'NO' Button
                                      dialog.cancel();
                                    //  Toast.makeText(getApplicationContext(),"you choose no action for alertbox", Toast.LENGTH_SHORT).show();
                                  }
                              });
                    //Creating dialog box
                    AlertDialog alert = builder.create();
                    //Setting the title manually
                    alert.setTitle("Delete User");
                    alert.show();
        }
        else{

                    Toast.makeText(Deleteuser.this,"Please Enter Roll no",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void deleteUser(String roll) {

        reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.child(roll).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()){
                    Toast.makeText(Deleteuser.this,"Successfully Deleted",Toast.LENGTH_SHORT).show();
                    binding.delrno.setText("");
                    binding.delemail.setText("");

                }else {

                    Toast.makeText(Deleteuser.this,"Failed",Toast.LENGTH_SHORT).show();


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