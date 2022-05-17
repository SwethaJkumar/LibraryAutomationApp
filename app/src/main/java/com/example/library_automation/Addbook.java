package com.example.library_automation;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.library_automation.databinding.ActivityAddbookBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Addbook extends AppCompatActivity {
    ActivityAddbookBinding binding;
    //DatabaseReference databaseReference;
    FirebaseDatabase db;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddbookBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        binding.addbook.setOnClickListener(view -> {
            String bid = binding.bookid.getText().toString();
            String bname = binding.bookname.getText().toString();
            String author = binding.author.getText().toString();
            String isbn = binding.isbn.getText().toString();
            String numcopy=binding.numcopy.getText().toString();
            String genre=binding.genre.getText().toString();
            String location=binding.location.getText().toString();
            String publication = binding.publ.getText().toString();
            addBook(bname,bid,author,isbn,numcopy,genre,location,publication);
            notification(bname,author);
        });
    }
    private void addBook(String bname,String bid,String author,String isbn,String Numcopy,String genre,String location,String publication) {
        HashMap Book = new HashMap();
        Book.put("bookName",bname);
        Book.put("bookId",bid);
        Book.put("author",author);
        Book.put("isbn",isbn);
        Book.put("numCopies",Numcopy);
        Book.put("genre",genre);
        Book.put("location",location);
        Book.put("publication",publication);
        //   String r=rollno;
        // if (!firstName.isEmpty() && !lastName.isEmpty() && !email.isEmpty() && !rollno.isEmpty()){
        Book book = new Book(bid,bname,author,isbn,Numcopy,genre,location,publication);
        db = FirebaseDatabase.getInstance();
        reference = db.getReference("Books");
        //databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        //databaseReference.child(rollno).updateChildren(User).addOnCompleteListener(new OnCompleteListener() {
        // reference.child("Users").child(user.getRollno()).setValue(user).addOnCompleteListener(task -> {
        reference.child(binding.bookid.getText().toString()).updateChildren(Book).addOnCompleteListener(task -> {
            // reference.addOnCompleteListener(new OnCompleteListener() {
            if (task.isSuccessful()){
                binding.bookid.setText("");
                binding.bookname.setText("");
                binding.author.setText("");
                binding.isbn.setText("");
                binding.numcopy.setText("");
                binding.genre.setText("");
                binding.location.setText("");
                binding.publ.setText("");
                Toast.makeText(Addbook.this,"Successfuly Updated",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(Addbook.this,"Failed to Update",Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void notification(String bname,String author) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel =
                    new NotificationChannel("n","n", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);

        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"n")
                .setContentText("Library")
                .setSmallIcon(R.drawable.ic_baseline_notifications_24)
                .setAutoCancel(true)
                .setContentText("New book is added");

        NotificationManagerCompat managerCompact = NotificationManagerCompat.from(this);
        managerCompact.notify(999,builder.build());
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);

// Creating an Editor object to edit(write to the file)
        SharedPreferences.Editor myEdit = sharedPreferences.edit();

// Storing the key and its value as the data fetched from edittext
        myEdit.putString("bname", bname);
        myEdit.putString("author", author);

// Once the changes have been made,
// we need to commit to apply those changes made,
// otherwise, it will throw an error
        myEdit.commit();
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