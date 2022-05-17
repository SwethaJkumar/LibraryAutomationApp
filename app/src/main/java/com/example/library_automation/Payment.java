package com.example.library_automation;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class Payment extends AppCompatActivity {
    TextView mode;
    RadioButton id,cc,upi;
    RadioGroup rg;
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        id=findViewById(R.id.rb1);
        cc=findViewById(R.id.rb2);
        upi=findViewById(R.id.rb3);
        mode=findViewById(R.id.pm);
        rg=findViewById(R.id.rg);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId)
                {
                    case R.id.rb1:
                        Intent intent=new Intent(Payment.this,Paymentform.class);
                        startActivity(intent);
                        finish();
                        break;
                    case R.id.rb2:
                        break;
                    case R.id.rb3:
                        Intent upipay = new Intent(getApplicationContext(),upi.class);
                        startActivity(upipay);
                        break;
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