package com.example.library_automation.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.library_automation.BookBorrow;
import com.example.library_automation.BookSearch;
import com.example.library_automation.Notification;
import com.example.library_automation.Opac;
import com.example.library_automation.Payment;
import com.example.library_automation.R;
import com.example.library_automation.ReadResearchPaper;
import com.example.library_automation.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {
 TextView t;
 CardView c1,c2,c3,c4,c5,c6;
    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        t = (TextView) root.findViewById(R.id.t1);
        t.setText("E-Library");

        TextPaint paint = t.getPaint();
        c1=(CardView) root.findViewById(R.id.cardView1);
        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), BookSearch.class);
                startActivity(intent);
            }
        });
        c3=(CardView) root.findViewById(R.id.cardView3);
        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Payment.class);
                startActivity(intent);
            }
        });
        c2=(CardView) root.findViewById(R.id.cardView2);
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), BookBorrow.class);
                startActivity(intent);
            }
        });
        c4=(CardView) root.findViewById(R.id.cardView4);
        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Opac.class);
                startActivity(intent);
            }
        });
        c5=(CardView) root.findViewById(R.id.cardView5);
        c5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ReadResearchPaper.class);
                startActivity(intent);
            }
        });
        c6=(CardView) root.findViewById(R.id.cardView6);
        c6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Notification.class);
                startActivity(intent);
            }
        });
       /* float width = paint.measureText("E-Library");
        double angleInRadians = Math.toRadians(45);
        double length = t.getPaint().getTextSize();

        double endX = Math.sin(angleInRadians) * length;
        double endY = Math.cos(angleInRadians) * length;
        Shader textShader = new LinearGradient(0, 0, width, t.getTextSize(),
                new int[]{
                        Color.parseColor("#d4c3f4"),
                        Color.parseColor("#aecdda"),
                        Color.parseColor("#afdeb1"),
                        Color.parseColor("#c8df9e"),
                        Color.parseColor("#ecb58c"),
                        Color.parseColor("#f2a380"),
                }, null, Shader.TileMode.CLAMP);
        t.getPaint().setShader(textShader);*/
        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}