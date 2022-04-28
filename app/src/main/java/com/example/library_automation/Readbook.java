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

import com.example.library_automation.databinding.ActivityReadbookBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Readbook extends AppCompatActivity {
    ActivityReadbookBinding binding;
    FirebaseDatabase db;
    DatabaseReference reference;
   // Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReadbookBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        binding.readbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String bid = binding.etbid.getText().toString();
                if (!bid.isEmpty()){
                    readData(bid);
                }else{
                    Toast.makeText(Readbook.this,"PLease Enter Book Id",Toast.LENGTH_SHORT).show();
                }
            }
        });
        binding.editbname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String bid=binding.etbid.getText().toString();
              editbname(bid);
            }
        });
        binding.editauthor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String bid=binding.etbid.getText().toString();
                editauthor(bid);
            }
        });
        binding.editpubl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String bid=binding.etbid.getText().toString();
                //String bname=Book.getBname();
                editpubl(bid);
            }
        });
        binding.editlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String bid=binding.etbid.getText().toString();
                //String bname=Book.getBname();
                editlocation(bid);
            }
        });
        binding.editgenre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String bid=binding.etbid.getText().toString();
                //String bname=Book.getBname();
                editgenre(bid);
            }
        });
    }
    private void editbname(String bid) {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Edit Book Name");
        LinearLayout linearLayout=new LinearLayout(this);
        final EditText etbname= new EditText(this);

        // write the email using which you registered
        etbname.setHint("Book name");
        etbname.setMinEms(16);
        etbname.setInputType(InputType.TYPE_TEXT_VARIATION_WEB_EDIT_TEXT);
        linearLayout.addView(etbname);
        linearLayout.setPadding(10,10,10,10);
        builder.setView(linearLayout);

        // Click on Recover and a email will be sent to your registered email id
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String etbname1=etbname.getText().toString();
                Saveeditbname(bid,etbname1);
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
    private void Saveeditbname(String bid,String etbname1){

        HashMap Book = new HashMap();
        Book.put("bookName",etbname1);
        Book book = new Book(bid,etbname1);
        db = FirebaseDatabase.getInstance();
        reference = db.getReference("Books");
        reference.child(bid).updateChildren(Book);
       // reference.child(bid).child("Book Name:").setValue(etbname1);
    }
    private void editauthor(String bid) {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Edit Author");
        LinearLayout linearLayout=new LinearLayout(this);
        final EditText etauthor= new EditText(this);

        // write the email using which you registered
        etauthor.setHint("Author");
        etauthor.setMinEms(16);
        etauthor.setInputType(InputType.TYPE_TEXT_VARIATION_WEB_EDIT_TEXT);
        linearLayout.addView(etauthor);
        linearLayout.setPadding(10,10,10,10);
        builder.setView(linearLayout);

        // Click on Recover and a email will be sent to your registered email id
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String etauthor1=etauthor.getText().toString();
                Saveeditauthor(bid,etauthor1);
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
    private void Saveeditauthor(String bid,String etauthor1){

        HashMap Book = new HashMap();
        Book.put("author",etauthor1);
        Book book = new Book(etauthor1);
        db = FirebaseDatabase.getInstance();
        reference = db.getReference("Books");
        reference.child(bid).updateChildren(Book);
        // reference.child(bid).child("Book Name:").setValue(etbname1);
    }
    private void editpubl(String bid) {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Edit Publication");
        LinearLayout linearLayout=new LinearLayout(this);
        final EditText etpubl= new EditText(this);

        // write the email using which you registered
        etpubl.setHint("Publication");
        etpubl.setMinEms(16);
        etpubl.setInputType(InputType.TYPE_TEXT_VARIATION_WEB_EDIT_TEXT);
        linearLayout.addView(etpubl);
        linearLayout.setPadding(10,10,10,10);
        builder.setView(linearLayout);

        // Click on Recover and a email will be sent to your registered email id
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String etpubl1=etpubl.getText().toString();
                Saveeditpubl(bid,etpubl1);
                // +.;
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
    private void Saveeditpubl(String bid,String etpubl1){

        HashMap Book = new HashMap();
        Book.put("publication",etpubl1);
        // Book book = new Book(bid,etpubl1);
        db = FirebaseDatabase.getInstance();
        reference = db.getReference("Books");
        // reference.child(bid).updateChildren(Book);
        reference.child(bid).child("publication:").setValue(etpubl1);
    }
    private void editlocation(String bid) {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Edit Publication");
        LinearLayout linearLayout=new LinearLayout(this);
        final EditText etlocation= new EditText(this);

        // write the email using which you registered
        etlocation.setHint("Publication");
        etlocation.setMinEms(16);
        etlocation.setInputType(InputType.TYPE_TEXT_VARIATION_WEB_EDIT_TEXT);
        linearLayout.addView(etlocation);
        linearLayout.setPadding(10,10,10,10);
        builder.setView(linearLayout);

        // Click on Recover and a email will be sent to your registered email id
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String etlocation1=etlocation.getText().toString();
                Saveeditlocation(bid,etlocation1);
                // +.;
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
    private void Saveeditlocation(String bid,String etlocation1){

        HashMap Book = new HashMap();
        Book.put("location",etlocation1);
        // Book book = new Book(bid,etpubl1);
        db = FirebaseDatabase.getInstance();
        reference = db.getReference("Books");
        // reference.child(bid).updateChildren(Book);
        reference.child(bid).child("location:").setValue(etlocation1);
    }
    private void editgenre(String bid) {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Edit Publication");
        LinearLayout linearLayout=new LinearLayout(this);
        final EditText etgenre= new EditText(this);

        // write the email using which you registered
        etgenre.setHint("Genre");
        etgenre.setMinEms(16);
        etgenre.setInputType(InputType.TYPE_TEXT_VARIATION_WEB_EDIT_TEXT);
        linearLayout.addView(etgenre);
        linearLayout.setPadding(10,10,10,10);
        builder.setView(linearLayout);

        // Click on Recover and a email will be sent to your registered email id
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String etgenre1=etgenre.getText().toString();
                Saveeditgenre(bid,etgenre1);
                // +.;
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
    private void Saveeditgenre(String bid,String etgenre1){

        HashMap Book = new HashMap();
        Book.put("genre",etgenre1);
        // Book book = new Book(bid,etpubl1);
        db = FirebaseDatabase.getInstance();
        reference = db.getReference("Books");
        // reference.child(bid).updateChildren(Book);
        reference.child(bid).child("genre:").setValue(etgenre1);
    }
    private void readData(String bid) {

        reference = FirebaseDatabase.getInstance().getReference("Books");
        reference.child(bid).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                if (task.isSuccessful()){

                    if (task.getResult().exists()){

                        Toast.makeText(Readbook.this,"Successfully Read",Toast.LENGTH_SHORT).show();
                        DataSnapshot dataSnapshot = task.getResult();
                        String Bookid = String.valueOf(dataSnapshot.child("bookId").getValue());
                        String Bname = String.valueOf(dataSnapshot.child("bookName").getValue());
                        String author = String.valueOf(dataSnapshot.child("author").getValue());
                        String isbn = String.valueOf(dataSnapshot.child("isbn").getValue());
                        String NumCopy = String.valueOf(dataSnapshot.child("numCopies").getValue());
                        String genre = String.valueOf(dataSnapshot.child("genre").getValue());
                        String location = String.valueOf(dataSnapshot.child("location").getValue());
                        String publication = String.valueOf(dataSnapshot.child("publication").getValue());
                        binding.tvbid.setText(Bookid);
                        binding.tvbname.setText(Bname);
                        binding.tvauthor.setText(author);
                        binding.tvisbn.setText(isbn);
                        binding.tvnumcopy.setText(NumCopy);
                        binding.tvgenre.setText(genre);
                        binding.tvlocation.setText(location);
                        binding.tvpubl.setText(publication);
                    }else {
                        Toast.makeText(Readbook.this,"Book Doesn't Exist",Toast.LENGTH_SHORT).show();
                    }

                }else {

                    Toast.makeText(Readbook.this,"Failed to read",Toast.LENGTH_SHORT).show();
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