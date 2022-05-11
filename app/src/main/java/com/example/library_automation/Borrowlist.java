package com.example.library_automation;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Borrowlist extends AppCompatActivity {
    // creating variables for our list view.
    ListView listView;
    FirebaseDatabase database;
    DatabaseReference ref;
    ArrayList<String> list;
    ArrayAdapter <String> adapter;
    Borrow borrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrowlist);
        borrow = new Borrow();
        listView = (ListView) findViewById(R.id.idLVBorrow);

        // initializing our array list
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("Borrow");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //collectPhoneNumbers((Map<String, Object>) snapshot.getValue());
                list = new ArrayList<String>();
                // Result will be holded Here
                for (DataSnapshot dsp : snapshot.getChildren()) {
                    list.add(String.valueOf(dsp.getValue()));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        ref = FirebaseDatabase.getInstance().getReference("Borrow");
        Query query = ref.orderByChild("Rollno").equalTo("cbenu4cse19354");
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
                   list.add(Rno+ " " +Bookid + " "+Bname+ " "+Idate+" "+Ddate);
               }
               listView.setAdapter(adapter);
           }
           @Override
           public void onCancelled(@NonNull DatabaseError error) {
           }
       });
    }
}