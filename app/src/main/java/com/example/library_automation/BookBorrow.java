package com.example.library_automation;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.library_automation.databinding.ActivityBookborrowBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class BookBorrow extends AppCompatActivity {
    ActivityBookborrowBinding binding;
    //DatabaseReference databaseReference;
    FirebaseDatabase db;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_bookborrow);
       /* TabHost tabhost = (TabHost) findViewById(R.id.tabhost);

        // setting up the tab host
        tabhost.setup();

        // Code for adding Tab 1 to the tabhost
        TabHost.TabSpec spec = tabhost.newTabSpec("Tab One");
        spec.setContent(R.id.tab1);

        // setting the name of the tab 1 as "Tab One"
        spec.setIndicator("Tab One");

        // adding the tab to tabhost
        tabhost.addTab(spec);

        // Code for adding Tab 2 to the tabhost
        spec = tabhost.newTabSpec("Tab Two");
        spec.setContent(R.id.tab2);

        // setting the name of the tab 1 as "Tab Two"
        spec.setIndicator("Tab Two");
        tabhost.addTab(spec);*/
        binding = ActivityBookborrowBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        binding.btnsearchbook.setOnClickListener(view -> {
            String bid = binding.txtbookid.getText().toString();
            if (!bid.isEmpty()){
                readData(bid);
            }else{
                Toast.makeText(BookBorrow.this,"PLease Enter Book Id",Toast.LENGTH_SHORT).show();
            }
            //String bname = binding.bookname.getText().toString();
            //String author = binding.author.getText().toString();
        });
        binding.btngetbook.setOnClickListener(view -> {
            String n = binding.txtnumcopy.getText().toString();

            if (!n.equals("0")){
                AlertDialog.Builder builder=new AlertDialog.Builder(this);
                builder.setTitle("Enter your Roll number");
                LinearLayout linearLayout=new LinearLayout(this);
                final EditText rno= new EditText(this);

                // write the email using which you registered
                rno.setHint("Roll No");
                rno.setMinEms(16);
                rno.setInputType(InputType.TYPE_TEXT_VARIATION_WEB_EDIT_TEXT);
                linearLayout.addView(rno);
                linearLayout.setPadding(10,10,10,10);
                builder.setView(linearLayout);

                // Click on Recover and a email will be sent to your registered email id
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String rno1=rno.getText().toString().trim();
                        borrow_books(rno1);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();

            }else{
                Toast.makeText(BookBorrow.this,"No copies left",Toast.LENGTH_SHORT).show();
            }
            //String bname = binding.bookname.getText().toString();
            //String author = binding.author.getText().toString();
        });
    }
    private void readData(String bid) {

        reference = FirebaseDatabase.getInstance().getReference("Books");
        reference.child(bid).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                if (task.isSuccessful()){

                    if (task.getResult().exists()){

                        Toast.makeText(BookBorrow.this,"Successfully Read",Toast.LENGTH_SHORT).show();
                        DataSnapshot dataSnapshot = task.getResult();
                        String Bookid = String.valueOf(dataSnapshot.child("bookId").getValue());
                        String Bname = String.valueOf(dataSnapshot.child("bookName").getValue());
                        //String author = String.valueOf(dataSnapshot.child("author").getValue());
                        //String isbn = String.valueOf(dataSnapshot.child("isbn").getValue());
                        String NumCopy = String.valueOf(dataSnapshot.child("numCopies").getValue());
                        String genre = String.valueOf(dataSnapshot.child("genre").getValue());
                        String location = String.valueOf(dataSnapshot.child("location").getValue());
                        //String publication = String.valueOf(dataSnapshot.child("publication").getValue());
                        //binding.txtbookid.setText(Bookid);
                        binding.txtbookname.setText(Bname);
                        binding.txtbookcategory.setText(genre);
                        //binding.tvisbn.setText(isbn);
                        binding.txtnumcopy.setText(NumCopy);
                        //binding.tvgenre.setText(genre);
                        binding.txtbooklocation.setText(location);
                        //binding.tvpubl.setText(publication);
                    }else {
                        Toast.makeText(BookBorrow.this,"Book Doesn't Exist",Toast.LENGTH_SHORT).show();
                    }

                }else {

                    Toast.makeText(BookBorrow.this,"Failed to read",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
   public void borrow_books(String rno)
    {

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df1 = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate1 = df1.format(c.getTime());
        Calendar c2 = Calendar.getInstance();
        c2.add(Calendar.MONTH, 1);
        String retur=df1.format(c2.getTime());
        Calendar c3 = Calendar.getInstance();
        c3.add(Calendar.DATE, 27);
        String notify=df1.format(c3.getTime());

        String bid1 = binding.txtbookid.getText().toString();
        String bname1 = binding.txtbookname.getText().toString();
       // String  = binding.txtnumcopy.getText().toString();

        HashMap Borrow = new HashMap();
        Borrow.put("Rollno",rno);
        Borrow.put("BookId",bid1);
        Borrow.put("Bookname",bname1);
        Borrow.put("Issuedate",formattedDate1);
        Borrow.put("Duedate",retur);

        Borrow borrow = new Borrow(rno,bid1,bname1,formattedDate1,retur);
        db = FirebaseDatabase.getInstance();
        reference = db.getReference("Borrow");
        reference.child(binding.txtbookid.getText().toString()).updateChildren(Borrow);
            // reference.addOnCompleteListener(new OnCompleteListener() {
           // if (task.isSuccessful()){
        update_books(bid1);
        Toast.makeText(getApplicationContext(),"Book added successful",Toast.LENGTH_SHORT).show();
        //bookname.setText("");
    }
    public void update_books(String bid)
    {
        reference = FirebaseDatabase.getInstance().getReference("Books");
        reference.child(bid).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()){
                    if (task.getResult().exists()){
                        // Toast.makeText(Readbook.this,"Successfully Read",Toast.LENGTH_SHORT).show();
                        DataSnapshot dataSnapshot = task.getResult();
                        String n = String.valueOf(dataSnapshot.child("numCopies").getValue());
                        int i=Integer.parseInt(n);
                        int n1=i-1;
                        String n2=Integer.toString(n1);
                        reference.child(bid).child("numCopies").setValue(n2);
                    }else {
                        Toast.makeText(BookBorrow.this,"Book Doesn't Exist",Toast.LENGTH_SHORT).show();
                    }
                }else {

                    Toast.makeText(BookBorrow.this,"Failed to read",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}