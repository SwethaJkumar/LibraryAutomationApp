package com.example.library_automation;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
       // ref.child("cbenu4cse19354").child("123");
       list = new ArrayList<>();
       adapter = new ArrayAdapter<String>(this,R.layout.borrow_info,R.id.borrowInfo,list);
       ref.addChildEventListener(new ChildEventListener() {
           @Override
           public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
               String value = snapshot.getValue(String.class);
               list.add(value);
               adapter.notifyDataSetChanged();
           }

           @Override
           public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

           }

           @Override
           public void onChildRemoved(@NonNull DataSnapshot snapshot) {

           }

           @Override
           public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

           }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {

           }
       });

       /*ref.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot snapshot) {
               for(DataSnapshot ds: snapshot.getChildren())
               {
                   borrow = ds.getValue(Borrow.class);
                   list.add(borrow.getBname() + " "+borrow.getBid());
               }
               listView.setAdapter(adapter);
           }
           @Override
           public void onCancelled(@NonNull DatabaseError error) {
           }
       });*/
    }
}