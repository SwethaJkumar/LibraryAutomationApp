package com.example.library_automation;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.library_automation.databinding.ActivityReaduserBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Readuser extends AppCompatActivity {
    ActivityReaduserBinding binding;
    DatabaseReference reference;
    FirebaseDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReaduserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        binding.readdataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = binding.etrollno.getText().toString();
                if (!username.isEmpty()){

                    readData(username);
                }else{

                    Toast.makeText(Readuser.this,"PLease Enter Roll number",Toast.LENGTH_SHORT).show();
                }

            }
        });
        binding.editfname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String rno=binding.tvrno.getText().toString();
                editfname(rno);
            }
        });
        binding.editlname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String rno=binding.tvrno.getText().toString();
                editlname(rno);
            }
        });
        binding.editemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String rno=binding.tvrno.getText().toString();
                editemail(rno);
            }
        });
        binding.editcourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String rno=binding.tvrno.getText().toString();
                editcourse(rno);
            }
        });
        binding.editdept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String rno=binding.tvrno.getText().toString();
                editdept(rno);
            }
        });
        binding.edityear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String rno=binding.tvrno.getText().toString();
                edityear(rno);
            }
        });
        binding.editcontact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String rno=binding.tvrno.getText().toString();
                editcontact(rno);
            }
        });
    }
    private void editfname(String rno) {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Edit First Name");
        LinearLayout linearLayout=new LinearLayout(this);
        final EditText etfname= new EditText(this);

        // write the email using which you registered
        etfname.setHint("First name");
        etfname.setMinEms(16);
        etfname.setInputType(InputType.TYPE_TEXT_VARIATION_WEB_EDIT_TEXT);
        linearLayout.addView(etfname);
        linearLayout.setPadding(10,10,10,10);
        builder.setView(linearLayout);

        // Click on Recover and a email will be sent to your registered email id
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String etfname1=etfname.getText().toString();
                Saveeditfname(rno,etfname1);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }
    private void Saveeditfname(String rno,String etfname1){
       // HashMap User = new HashMap();
       // Book.put("",etfname1);
        // Book book = new Book(bid,etpubl1);
        db = FirebaseDatabase.getInstance();
        reference = db.getReference("Users");
        // reference.child(bid).updateChildren(Book);
        reference.child(rno).child("First Name:").setValue(etfname1);
        // reference.child(bid).child("Book Name:").setValue(etbname1);
    }
    private void editlname(String rno) {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Edit Last Name");
        LinearLayout linearLayout=new LinearLayout(this);
        final EditText etlname= new EditText(this);

        // write the email using which you registered
        etlname.setHint("Last name");
        etlname.setMinEms(16);
        etlname.setInputType(InputType.TYPE_TEXT_VARIATION_WEB_EDIT_TEXT);
        linearLayout.addView(etlname);
        linearLayout.setPadding(10,10,10,10);
        builder.setView(linearLayout);

        // Click on Recover and a email will be sent to your registered email id
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String etlname1=etlname.getText().toString();
                Saveeditlname(rno,etlname1);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }
    private void Saveeditlname(String rno,String etlname1){
        //HashMap User = new HashMap();
        // Book.put("",etlname1);
        // Book book = new Book(bid,etpubl1);
        db = FirebaseDatabase.getInstance();
        reference = db.getReference("Users");
        // reference.child(bid).updateChildren(Book);
        reference.child(rno).child("Last Name:").setValue(etlname1);
        // reference.child(bid).child("Book Name:").setValue(etbname1);
    }
    private void editemail(String rno) {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Edit Email");
        LinearLayout linearLayout=new LinearLayout(this);
        final EditText etemail= new EditText(this);

        // write the email using which you registered
        etemail.setHint("Email");
        etemail.setMinEms(16);
        etemail.setInputType(InputType.TYPE_TEXT_VARIATION_WEB_EDIT_TEXT);
        linearLayout.addView(etemail);
        linearLayout.setPadding(10,10,10,10);
        builder.setView(linearLayout);

        // Click on Recover and a email will be sent to your registered email id
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String etemail1=etemail.getText().toString();
                Saveeditemail(rno,etemail1);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }
    private void Saveeditemail(String rno,String etemail1){
        //HashMap User = new HashMap();
        // Book.put("",etlname1);
        // Book book = new Book(bid,etpubl1);
        db = FirebaseDatabase.getInstance();
        reference = db.getReference("Users");
        // reference.child(bid).updateChildren(Book);
        reference.child(rno).child("Email:").setValue(etemail1);
        // reference.child(bid).child("Book Name:").setValue(etbname1);
    }
    private void editcourse(String rno) {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Edit Course");
        LinearLayout linearLayout=new LinearLayout(this);
        final EditText etcourse= new EditText(this);

        // write the email using which you registered
        etcourse.setHint("Course");
        etcourse.setMinEms(16);
        etcourse.setInputType(InputType.TYPE_TEXT_VARIATION_WEB_EDIT_TEXT);
        linearLayout.addView(etcourse);
        linearLayout.setPadding(10,10,10,10);
        builder.setView(linearLayout);

        // Click on Recover and a email will be sent to your registered email id
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String etcourse1=etcourse.getText().toString();
                Saveeditcourse(rno,etcourse1);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }
    private void Saveeditcourse(String rno,String etcourse1){
        //HashMap User = new HashMap();
        // Book.put("",etlname1);
        // Book book = new Book(bid,etpubl1);
        db = FirebaseDatabase.getInstance();
        reference = db.getReference("Users");
        // reference.child(bid).updateChildren(Book);
        reference.child(rno).child("Course:").setValue(etcourse1);
        // reference.child(bid).child("Book Name:").setValue(etbname1);
    }
    private void editdept(String rno) {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Edit Department");
        LinearLayout linearLayout=new LinearLayout(this);
        final EditText etdept= new EditText(this);

        // write the email using which you registered
        etdept.setHint("Department");
        etdept.setMinEms(16);
        etdept.setInputType(InputType.TYPE_TEXT_VARIATION_WEB_EDIT_TEXT);
        linearLayout.addView(etdept);
        linearLayout.setPadding(10,10,10,10);
        builder.setView(linearLayout);

        // Click on Recover and a email will be sent to your registered email id
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String etdept1=etdept.getText().toString();
                Saveeditdept(rno,etdept1);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }
    private void Saveeditdept(String rno,String etdept1){
        //HashMap User = new HashMap();
        // Book.put("",etlname1);
        // Book book = new Book(bid,etpubl1);
        db = FirebaseDatabase.getInstance();
        reference = db.getReference("Users");
        // reference.child(bid).updateChildren(Book);
        reference.child(rno).child("Department:").setValue(etdept1);
        // reference.child(bid).child("Book Name:").setValue(etbname1);
    }
    private void edityear(String rno) {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Edit Year");
        LinearLayout linearLayout=new LinearLayout(this);
        final EditText etyear= new EditText(this);

        // write the email using which you registered
        etyear.setHint("Year");
        etyear.setMinEms(16);
        etyear.setInputType(InputType.TYPE_TEXT_VARIATION_WEB_EDIT_TEXT);
        linearLayout.addView(etyear);
        linearLayout.setPadding(10,10,10,10);
        builder.setView(linearLayout);

        // Click on Recover and a email will be sent to your registered email id
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String etyear1=etyear.getText().toString();
                Saveedityear(rno,etyear1);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }
    private void Saveedityear(String rno,String etyear1){
        //HashMap User = new HashMap();
        // Book.put("",etlname1);
        // Book book = new Book(bid,etpubl1);
        db = FirebaseDatabase.getInstance();
        reference = db.getReference("Users");
        // reference.child(bid).updateChildren(Book);
        reference.child(rno).child("Year:").setValue(etyear1);
        // reference.child(bid).child("Book Name:").setValue(etbname1);
    }
    private void editcontact(String rno) {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Edit Contact");
        LinearLayout linearLayout=new LinearLayout(this);
        final EditText etcontact= new EditText(this);

        // write the email using which you registered
        etcontact.setHint("Contact");
        etcontact.setMinEms(16);
        etcontact.setInputType(InputType.TYPE_TEXT_VARIATION_WEB_EDIT_TEXT);
        linearLayout.addView(etcontact);
        linearLayout.setPadding(10,10,10,10);
        builder.setView(linearLayout);

        // Click on Recover and a email will be sent to your registered email id
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String etcontact1=etcontact.getText().toString();
                Saveeditcontact(rno,etcontact1);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }
    private void Saveeditcontact(String rno,String etcontact1){
        //HashMap User = new HashMap();
        // Book.put("",etlname1);
        // Book book = new Book(bid,etpubl1);
        db = FirebaseDatabase.getInstance();
        reference = db.getReference("Users");
        // reference.child(bid).updateChildren(Book);
        reference.child(rno).child("Contact:").setValue(etcontact1);
        // reference.child(bid).child("Book Name:").setValue(etbname1);
    }
    private void readData(String username) {

        reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.child(username).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                if (task.isSuccessful()){

                    if (task.getResult().exists()){

                        Toast.makeText(Readuser.this,"Successfully Read",Toast.LENGTH_SHORT).show();
                        DataSnapshot dataSnapshot = task.getResult();
                        String rollno = String.valueOf(dataSnapshot.child("Roll no").getValue());
                        String firstName = String.valueOf(dataSnapshot.child("First Name").getValue());
                        String lastName = String.valueOf(dataSnapshot.child("Last Name").getValue());
                        String email = String.valueOf(dataSnapshot.child("Email").getValue());
                        String course = String.valueOf(dataSnapshot.child("Course").getValue());
                        String dept = String.valueOf(dataSnapshot.child("Department").getValue());
                        String year = String.valueOf(dataSnapshot.child("Year").getValue());
                        String contact = String.valueOf(dataSnapshot.child("Contact").getValue());
                        binding.tvrno.setText(rollno);
                        binding.tvFirstName.setText(firstName);
                        binding.tvLastName.setText(lastName);
                        binding.tvEmail.setText(email);
                        binding.tvDept.setText(dept);
                        binding.tvCourse.setText(course);
                        binding.tvYear.setText(year);
                        binding.tvContact.setText(contact);


                    }else {

                        Toast.makeText(Readuser.this,"User Doesn't Exist",Toast.LENGTH_SHORT).show();

                    }


                }else {

                    Toast.makeText(Readuser.this,"Failed to read",Toast.LENGTH_SHORT).show();
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