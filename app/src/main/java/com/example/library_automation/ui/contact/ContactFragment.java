package com.example.library_automation.ui.contact;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.library_automation.R;
import com.example.library_automation.databinding.FragmentContactBinding;

public class ContactFragment extends Fragment {

    private ContactViewModel contactViewModel;
    private FragmentContactBinding binding;
    EditText cname,cemail,csub,cmsg;
    Button csend;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        contactViewModel =
                new ViewModelProvider(this).get(ContactViewModel.class);

        binding = FragmentContactBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        cname=(EditText) root.findViewById(R.id.cname);
        cemail=(EditText) root.findViewById(R.id.cemail);
        csub=(EditText) root.findViewById(R.id.csub);
        cmsg=(EditText) root.findViewById(R.id.cmsg);
        csend=(Button) root.findViewById(R.id.csend);

        csend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String to="elibautomation@gmail.com";
                String name=cname.getText().toString();
                String email=cemail.getText().toString();
                String subject=csub.getText().toString();
                String message=cmsg.getText().toString();


                Intent iemail = new Intent(Intent.ACTION_SEND);
                iemail.putExtra(Intent.EXTRA_EMAIL, new String[]{ to});
                iemail.putExtra(Intent.EXTRA_TEXT, name);
                iemail.putExtra(Intent.EXTRA_SUBJECT, subject);
                iemail.putExtra(Intent.EXTRA_TEXT, message);

                //need this to prompts email client only
                iemail.setType("message/rfc822");

                startActivity(Intent.createChooser(iemail, "Choose an Email client :"));
            }
        });
        /*final TextView textView = binding.textContact;
        contactViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
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
