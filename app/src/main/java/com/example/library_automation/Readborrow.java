package com.example.library_automation;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.library_automation.databinding.ActivityReadborrowBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Readborrow extends AppCompatActivity {
    ActivityReadborrowBinding binding;
    FirebaseDatabase db;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReadborrowBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        binding.readb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String rno = binding.etrno1.getText().toString();
                if (!rno.isEmpty()){
                    readData(rno);
                }else{
                    Toast.makeText(Readborrow.this,"PLease Enter Roll no",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void readData(String bid) {

        reference = FirebaseDatabase.getInstance().getReference("Borrow");
        reference.child(bid);
        Query query=reference.orderByChild("Rollno").equalTo(bid);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot datas: dataSnapshot.getChildren()){
                    String name=datas.child("BookId").getValue().toString();
                    binding.tvbid.setText(name);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
      //  reference.child(bid).child("123").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
           /* @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                if (task.isSuccessful()){

                    if (task.getResult().exists()){

                        Toast.makeText(Readborrow.this,"Successfully Read",Toast.LENGTH_SHORT).show();
                        DataSnapshot dataSnapshot = task.getResult();
                        String Bookid = String.valueOf(dataSnapshot.child("BookId").getValue());
                        String Bname = String.valueOf(dataSnapshot.child("BookName").getValue());
                        String rno2 = String.valueOf(dataSnapshot.child("Rollno").getValue());
                        String issue = String.valueOf(dataSnapshot.child("Issuedate").getValue());
                        String due = String.valueOf(dataSnapshot.child("Duedate").getValue());
                      //  String genre = String.valueOf(dataSnapshot.child("genre").getValue());
                       // String location = String.valueOf(dataSnapshot.child("location").getValue());
                       // String publication = String.valueOf(dataSnapshot.child("publication").getValue());
                        binding.tvbid.setText(Bookid);
                        binding.tvbname.setText(Bname);
                        binding.tvrno.setText(rno2);
                        binding.tvissue.setText(issue);
                        binding.tvdue.setText(due);
                       // binding.tvgenre.setText(genre);
                        //binding.tvlocation.setText(location);
                       // binding.tvpubl.setText(publication);
                    }else {
                        Toast.makeText(Readborrow.this,"Roll No Doesn't Exist",Toast.LENGTH_SHORT).show();
                    }

                }else {

                    Toast.makeText(Readborrow.this,"Failed to read",Toast.LENGTH_SHORT).show();
                }

            }
        });
*/
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