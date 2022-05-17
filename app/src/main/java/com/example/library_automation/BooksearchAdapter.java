package com.example.library_automation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BooksearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<Book> bookArrayList;

    public BooksearchAdapter(ArrayList<Book> bookArrayList) {
        this.bookArrayList = bookArrayList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BookHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_holder, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ((BookHolder) holder).bid.setText(bookArrayList.get(position).getBid());
        ((BookHolder) holder).bname.setText(bookArrayList.get(position).getBname());

    }

    @Override
    public int getItemCount() {
        return bookArrayList.size();
    }

    static class BookHolder extends RecyclerView.ViewHolder {

        TextView bname;
        TextView bid;

        public BookHolder(@NonNull View itemView) {
            super(itemView);
            bname = itemView.findViewById(R.id.book_name);
            bid = itemView.findViewById(R.id.book_id);

            itemView.setOnClickListener(view -> {
                //TODO: Handle card clicks here, (eg: Launch activities from here)
            });
        }
    }
}
