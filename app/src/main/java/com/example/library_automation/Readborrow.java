package com.example.library_automation;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.library_automation.databinding.ActivityReadborrowBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Readborrow extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    ActivityReadborrowBinding binding;
    FirebaseDatabase db;
    DatabaseReference reference;
    ListView l;
    ArrayAdapter <String> adapter;
    Borrow borrow;
    ArrayList<String> list;
    private String category;
    Spinner spinner1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReadborrowBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        l = findViewById(R.id.rblist);
        Spinner spinner1=findViewById(R.id.spinner1);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        ArrayAdapter<CharSequence>adapter=ArrayAdapter.createFromResource(this, R.array.borrowsearch, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner1.setAdapter(adapter);
        spinner1.setOnItemSelectedListener(this);

        binding.readb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String rno = binding.etrno1.getText().toString();
               // String bid = binding.etbid.getText().toString();
                if (!rno.isEmpty()){
                    readData(rno,category);
                }else{
                    Toast.makeText(Readborrow.this,"PLease Enter Roll no",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void readData(String rno,String bid) {
       // reference = FirebaseDatabase.getInstance().getReference("Borrow");
        //reference.child(rno);
        reference = FirebaseDatabase.getInstance().getReference("Borrow");
        Query query = reference.orderByChild(category).equalTo(rno);
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
                    String Rno =ds.child("Rollno").getValue(String.class);
                    String Bname=ds.child("Bookname").getValue(String.class);
                    String Bookid =ds.child("BookId").getValue(String.class);
                    String Idate=ds.child("Issuedate").getValue(String.class);
                    String Ddate=ds.child("Duedate").getValue(String.class);
                    //String Bname = String.valueOf(snapshot.child("Bookname").getValue());
                    // list.add(borrow.getBname() + " "+borrow.getBid());
                    list.add(" Roll no: "+Rno+ "\n " +" Book Id: "+Bookid + "\n "+" Book Name: "+Bname+ "\n "+" Issue Date: "+Idate+"\n "+" Due Date: "+Ddate);
                }
                l.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

      /*  reference = FirebaseDatabase.getInstance().getReference("Borrow");
        list = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this, R.layout.borrow_info, R.id.borrowInfo, list);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    borrow = ds.getValue(Borrow.class);
                    String Bookid = String.valueOf(snapshot.child("BookId").getValue());
                    String Bname = String.valueOf(snapshot.child("Bookname").getValue());
                    // list.add(borrow.getBname() + " "+borrow.getBid());
                    list.add(Bookid + " " + Bname);
                }
                l.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });*/
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        switch (position) {
            case 0:
                category="BookId";
                // Whatever you want to happen when the first item gets selected
                break;
            case 1:
                // Whatever you want to happen when the second item gets selected
                category="Bookname";
                break;
            case 2:
                // Whatever you want to happen when the thrid item gets selected
                category="Rollno";
                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub
    }

}