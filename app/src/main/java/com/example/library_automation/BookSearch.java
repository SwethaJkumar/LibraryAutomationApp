package com.example.library_automation;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BookSearch extends AppCompatActivity {
    ArrayList<Book> bookArrayList;
    ArrayList<Book> bookCopyList;
    BooksearchAdapter adapter;
    DatabaseReference databaseReference;
    ValueEventListener valueEventListener;
    RecyclerView recyclerView;
    TextView noResultsMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_search);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        bookArrayList = new ArrayList<>();
        bookCopyList = new ArrayList<>();
        adapter = new BooksearchAdapter(bookArrayList);

        noResultsMessage = findViewById(R.id.no_results);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setAdapter(adapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("Books");
        //databaseReference = FirebaseDatabase.getInstance().getReference().child("Books");
        //databaseReference=databaseReference.get();

        /**
         * To write book to server:
         * databaseReference.push().setValue(new Book("Book Name 2", "456"));
         */

        valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("TAG", "onDataChange: " + snapshot);
             //   bookArrayList.clear();
                for (DataSnapshot snap : snapshot.getChildren()) {
                    //String bookId =snapshot.child("bookId").getValue(Book.class);
                    //String bookName=snapshot.child("bookName").getValue(String.class);
                    //String genre =ds.child("BookId").getValue(String.class);
                    //String is=ds.child("Issuedate").getValue(String.class);
                    //String Ddate=ds.child("Duedate").getValue(String.class);
                    //bookArrayList.add(bookId);
                    bookArrayList.add(snap.getValue(Book.class));
                    bookCopyList.add(snap.getValue(Book.class));
                    adapter.notifyItemInserted(bookArrayList.size() - 1);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };


    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(valueEventListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        databaseReference.removeEventListener(valueEventListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);

        MenuItem searchItem = menu.findItem(R.id.searchBar);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setQueryHint("Search For Books");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Log.d("TAG", "onQueryTextSubmit: " + s);
                filterArrayList(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                Log.d("TAG", "onQueryTextSubmit: " + s);

                filterArrayList(s);
                return false;
            }
        });
        searchView.setIconified(true);

        return true;
    }

    @SuppressLint("NotifyDataSetChanged")
    void filterArrayList(String s) {
       // bookArrayList.clear();
        adapter.notifyDataSetChanged();
        for (Book i : bookCopyList) {
           if (i.getBname().toLowerCase().contains(s.toLowerCase()) || i.getBid().toLowerCase().contains(s.toLowerCase())) {
           // if (i.getBname().equals(s)) {
                bookArrayList.add(i);
                adapter.notifyItemInserted(bookArrayList.size() - 1);
            }
            if (bookArrayList.isEmpty()) {
                noResultsMessage.setVisibility(View.VISIBLE);
            } else {
                noResultsMessage.setVisibility(View.GONE);
            }
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}