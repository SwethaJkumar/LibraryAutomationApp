package com.example.library_automation;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Paymentform extends AppCompatActivity {
    Button b1;
    EditText e1, e2;
    TextView t1;
    DatabaseReference reference;
    FirebaseDatabase db;

    //final int pay=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paymentform);
        b1 = findViewById(R.id.btn_submit);
        t1 = findViewById(R.id.t3);
        e1 = findViewById(R.id.wal);
        e2 = findViewById(R.id.pname);
        String wal = e1.getText().toString();
        String pname = e2.getText().toString();
        b1.setOnClickListener(view -> readdata(wal, pname));

    }

    private void readdata(String amount, String name1) {
        reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.child("cse19338").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {

            int pay = Integer.parseInt(amount);

            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    if (task.getResult().exists()) {
                        Toast.makeText(Paymentform.this, "Successful", Toast.LENGTH_SHORT).show();
                        DataSnapshot dataSnapshot = task.getResult();
                        //  String amt = ds.child("wallet").getValue(String.class);
                        String amt = String.valueOf(dataSnapshot.child("wallet").getValue());

                        // int i=amt;
                        try {
                            int i = Integer.parseInt(amt);
                            int n1 = i - pay;
                            String n2 = Integer.toString(n1);
                            reference.child("cse19338").child("wallet").setValue(n2);
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                        }
                          /*  int n1 = i - pay;
                            String n2 = Integer.toString(n1);
                            reference.child("cse19338").child("wallet").setValue(n2);
                        }
                    else {
                        Toast.makeText(Paymentform.this,"Roll number doesn't exist",Toast.LENGTH_SHORT).show();
                    }
                }else {

                    Toast.makeText(Paymentform.this,"Failed!!",Toast.LENGTH_SHORT).show();
                }*/
                    }

                    //  Intent intent = new Intent(getApplicationContext(), wallet.class);
                    //intent.putExtra("keypay",pay);
                    // startActivity(intent);

                }
            }
        });
    }
}