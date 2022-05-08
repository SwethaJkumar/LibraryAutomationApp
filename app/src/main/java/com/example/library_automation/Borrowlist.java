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
        /*private void collectPhoneNumbers(Map<String,Object> users) {

            ArrayList<Long> phoneNumbers = new ArrayList<>();

            //iterate through each user, ignoring their UID
            for (Map.Entry<String, Object> entry : users.entrySet()){

                //Get user map
                Map singleUser = (Map) entry.getValue();
                //Get phone field and append to list
                phoneNumbers.add((Long) singleUser.get("phone"));
            }

            System.out.println(phoneNumbers.toString());
        }*/
        // ref.child("cbenu4cse19354").child("123");
        // ref.child(rno).child(bid);
      /* list = new ArrayList<>();
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
*/
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