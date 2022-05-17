package com.example.library_automation;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class profile extends AppCompatActivity {
    TextView t1,t2;
    ListView l;
    ArrayAdapter <String> adapter;
    Borrow borrow;
    Button vb;
    ArrayList<String> list;
    public ProgressDialog loginprogress;
    private FirebaseAuth mAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference,databaseReference1;
    private int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Intent intent =getIntent();
        l = findViewById(R.id.ublist);
        String str=intent.getStringExtra("username");
        int index = str.indexOf('@');
        str = str.substring(0,index);
        str = str.replaceAll("\\.","");
        str=str.substring(str.length() - 8 );
        Button vb=(Button)findViewById(R.id.btnviewborrow);

        TextView borrow=(TextView)findViewById(R.id.posts);
        //String c = Long.toString(count);
        borrow.setText("0");
        String str1=str;
        TextView uname=(TextView)findViewById(R.id.about);
        uname.setText(str);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        vb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readData(str1);

            }
        });
        mAuth = FirebaseAuth.getInstance();
       // databaseReference=FirebaseDatabase.getInstance().getReference("Users");
     //   databaseReference.orderByChild("First Name:");

        // initialising all views through id defined above
        t1=findViewById(R.id.editcontact);
       t1.setOnClickListener(view -> {
               // EditContact();
                AlertDialog.Builder builder=new AlertDialog.Builder(this);
                builder.setTitle("Enter your Contact No");
                LinearLayout linearLayout=new LinearLayout(this);
                final EditText cno= new EditText(this);

                // write the email using which you registered
                cno.setHint("Contact No");
                cno.setMinEms(16);
                cno.setInputType(InputType.TYPE_TEXT_VARIATION_WEB_EDIT_TEXT);
                linearLayout.addView(cno);
                linearLayout.setPadding(10,10,10,10);
                builder.setView(linearLayout);

                // Click on Recover and a email will be sent to your registered email id
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String cno1=cno.getText().toString().trim();
                        Saveeditcontact(str1,cno1);
                        //borrow_books(rno1);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();
                //databaseReference.child("Users").child(userId).child("username").setValue(name);

        });
        t2=findViewById(R.id.reset);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        loginprogress=new ProgressDialog(this);

        // click on forget password text
        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRecoverPasswordDialog();
            }
        });
       // databaseReference = FirebaseDatabase.getInstance().getReference("Borrow").child(str);
        databaseReference = FirebaseDatabase.getInstance().getReference("Borrow");

        //databaseReference1 =FirebaseDatabase.getInstance().getReference().child(str);
        Query query = databaseReference.orderByChild("Rollno").equalTo(str);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
               /* if (snapshot.exists()){
                   count=(int) snapshot.getChildrenCount();
                   borrow.setText(Integer.toString(count));
                }*/
                for (DataSnapshot data : snapshot.getChildren()) {
                    if (data.child("Rollno").getValue(String.class).equals(str1)) {
                        count++;
                        //Log.d("TAG", "Count1: " + count);
                    }
                }
                //Log.d("TAG", "Count2: " + count);
                borrow.setText(Integer.toString(count));
               /* else
                {
                    borrow.setText("0");
                }*/
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void Saveeditcontact(String str1, String cno1) {
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        // reference.child(bid).updateChildren(Book);
        databaseReference.child(str1).child("Contact:").setValue(cno1);
    }

    private void readData(String rno) {
        // reference = FirebaseDatabase.getInstance().getReference("Borrow");
        //reference.child(rno);
        databaseReference = FirebaseDatabase.getInstance().getReference("Borrow");
        Query query = databaseReference.orderByChild("Rollno").equalTo(rno);
        // ref.child("cbenu4cse19354").child("123");
        //  ref.child(rno).child(bid);
        list = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this,R.layout.borrow_info,R.id.borrowInfo,list);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren())
                {
                    //borrow = ds.getValue(Borrow.class);
                    //String Bookid = String.valueOf(snapshot.child("BookId").getValue());
                    String Bname=ds.child("Bookname").getValue(String.class);
                    String Bookid =ds.child("BookId").getValue(String.class);
                    String Idate=ds.child("Issuedate").getValue(String.class);
                    String Ddate=ds.child("Duedate").getValue(String.class);
                    //String Bname = String.valueOf(snapshot.child("Bookname").getValue());
                    // list.add(borrow.getBname() + " "+borrow.getBid());
                    list.add("  Book Id: "+Bookid + "\n "+" Book Name: "+Bname+ "\n "+" Issue Date: "+Idate+"\n "+" Due Date: "+Ddate);
                }
                l.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

/*
        //String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        Query query = rootRef.child("Borrow").orderByChild("Rollno").equalTo(str1);
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    long count = ds.getChildrenCount();
                    Log.d("TAG", "Count: " + count);
                }
                //TextView borrow=(TextView)findViewById(R.id.posts);
                //String c = Long.toString(count);
                //borrow.setText("0");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
               // Log.d(TAG, databaseError.getMessage());
            }
        };
        query.addListenerForSingleValueEvent(valueEventListener);
        */

    }
    ProgressDialog loadingBar;
    private void showRecoverPasswordDialog() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Reset Password");
        LinearLayout linearLayout=new LinearLayout(this);
        final EditText emailet= new EditText(this);

        // write the email using which you registered
        emailet.setHint("Email");
        emailet.setMinEms(16);
        emailet.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        linearLayout.addView(emailet);
        linearLayout.setPadding(10,10,10,10);
        builder.setView(linearLayout);

        // Click on Recover and a email will be sent to your registered email id
        builder.setPositiveButton("Reset", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String email=emailet.getText().toString().trim();
                beginRecovery(email);
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
    private void beginRecovery(String email) {
        loadingBar = new ProgressDialog(this);
        loadingBar.setMessage("Sending Email....");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();

        // calling sendPasswordResetEmail
        // open your email and write the new
        // password and then you can login
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                loadingBar.dismiss();
                if (task.isSuccessful()) {
                    // if isSuccessful then done message will be shown
                    // and you can change the password
                    Toast.makeText(profile.this, "Done sent", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(profile.this, "Error Occured", Toast.LENGTH_LONG).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                loadingBar.dismiss();
                Toast.makeText(profile.this, "Error Failed", Toast.LENGTH_LONG).show();
            }
        });
    }
       /*private void EditContact(){
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setTitle("Edit Contact");
            LinearLayout linearLayout=new LinearLayout(this);
            final EditText editcontact= new EditText(this);

            // write the email using which you registered
            editcontact.setHint("Enter new Contact no");
            editcontact.setMinEms(16);
            //editcontact.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
            linearLayout.addView(editcontact);
            linearLayout.setPadding(10,10,10,10);
            builder.setView(linearLayout);

            // Click on Recover and a email will be sent to your registered email id
            builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String newcontact=editcontact.getText().toString().trim();
                  //  databaseReference.("Users").child("Contact:").setValue(editcontact);
                    firebase.database().ref('Users/' + user.uid).set()
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.create().show();
        }*/
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