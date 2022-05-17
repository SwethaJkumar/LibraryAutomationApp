package com.example.library_automation.ui.faqs;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.library_automation.databinding.FragmentFaqsBinding;

public class FaqsFragment extends Fragment {

    private FaqsViewModel faqsViewModel;
    private FragmentFaqsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        faqsViewModel =
                new ViewModelProvider(this).get(FaqsViewModel.class);

        binding = FragmentFaqsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        String url = "https://www.e-libraryse.ml/faq";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
        /*final TextView textView = binding.textSlideshow;
        faqsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}