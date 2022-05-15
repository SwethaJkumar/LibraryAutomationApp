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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class OverdueDetails extends AppCompatActivity {

    FirebaseDatabase db;
    DatabaseReference reference;
    ListView l;
    ArrayAdapter <String> adapter;
    Borrow borrow;
    ArrayList<String> list;
    public final String ps="pending";
    public final int fine=100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overdue_details);


        reference = FirebaseDatabase.getInstance().getReference("Borrow");
        //Query query = reference.orderByChild(category).equalTo(rno);
        // ref.child("cbenu4cse19354").child("123");
        //  ref.child(rno).child(bid);
        //list = new ArrayList<>();
        //adapter = new ArrayAdapter<String>(this,R.layout.borrow_info,R.id.borrowInfo,list);
        reference.addValueEventListener(new ValueEventListener() {
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
                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df1 = new SimpleDateFormat("dd-MMM-yyyy");
                    String formattedDate1 = df1.format(c.getTime());

                    Date d1 = null;
                    try {
                        d1 = df1.parse(formattedDate1);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    Date d2 = null;
                    try {
                        d2 = df1.parse(Ddate);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    //String Bname = String.valueOf(snapshot.child("Bookname").getValue());
                    // list.add(borrow.getBname() + " "+borrow.getBid());
                    if(d2.before(d1)){
                        HashMap Borrow = new HashMap();
                        Borrow.put("Rollno",Rno);
                        Borrow.put("BookId",Bookid);
                        Borrow.put("DueDate",Ddate);
                        Borrow.put("PaymentStatus",ps);
                        Borrow.put("Fine",fine);

                        Overdue overdue = new Overdue(Rno,Bookid,Ddate,ps,fine);
                        db = FirebaseDatabase.getInstance();
                        reference = db.getReference("Overdue");
                        String key = reference.push().getKey();
                        reference.child(key).updateChildren(Borrow);
                       // list.add(" Roll no: "+Rno+ "\n " +" Book Id: "+Bookid + "\n "+" Book Name: "+Bname+ "\n "+" Issue Date: "+Idate+"\n "+" Due Date: "+Ddate);
                    }

                }
               // l.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }
}