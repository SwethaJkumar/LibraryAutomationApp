package com.example.library_automation;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.library_automation.databinding.AdduserFragmentBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.concurrent.Executor;
//import com.google.firebase.firestore.auth.User;

public class AdduserFragment extends Fragment {
    private FirebaseDatabase database;
    private DatabaseReference mDatabase;
    public AdduserFragmentBinding binding;
    public User user;
    private EditText rollno,name,emailid,course,dept,contact;
    public Button add;
    private static final String USERS = "users";
    private final String TAG = "AdduserActivity";
    public String emailid1;
    public String password1="123456";
    public AdduserViewModel mViewModel;
    private FirebaseAuth mAuth;
    public ProgressDialog progressDialog;
    public static AdduserFragment newInstance() {
       return new AdduserFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,@Nullable Bundle savedInstanceState) {
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        AdduserFragmentBinding binding = AdduserFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        //root =inflater.inflate(R.layout.adduser_fragment, container, false);

        rollno = (EditText) root.findViewById(R.id.rno);
        name = (EditText) root.findViewById(R.id.name);
        emailid = (EditText) root.findViewById(R.id.email);
        course = (EditText) root.findViewById(R.id.Course);
        dept = (EditText) root.findViewById(R.id.dept);
        contact = (EditText) root.findViewById(R.id.Contact);
        Button add = (Button) root.findViewById(R.id.adduser1);
        database = FirebaseDatabase.getInstance();
        mDatabase = database.getReference(USERS);
        mAuth = FirebaseAuth.getInstance();
        mViewModel = new ViewModelProvider(this).get(AdduserViewModel.class);

        //binding = FragmentAdduserBinding.inflate(inflater, container, false);
       // View root = binding.getRoot();
        add.setOnClickListener(view -> {
            Toast.makeText(getActivity(), "hello", Toast.LENGTH_SHORT).show();
            //if(emailid.getText().toString() != null && rollno.getText().toString() != null) {
                String rollno1 = rollno.getText().toString();
                String name1 = name.getText().toString();
                String emailid1 = emailid.getText().toString();
                String course1 = course.getText().toString();
                String dept1 = dept.getText().toString();
                String contact1 = contact.getText().toString();
                //String password = passwordEditText.getText().toString();
                //  String user = new User(fname,email,profession,workplace,phone);
                //user = new User(rollno1,name1, emailid1, course1, dept1, contact1);
                //User user= new User(fname, email, profession, workplace, phone);
                addUser(rollno1,name1, emailid1, course1, dept1, contact1);
          //  }
           // else{
               // Toast.makeText(getActivity(), "Authentication failed.", Toast.LENGTH_SHORT).show();
          //  }
        });
        //@OnClick(R.id.signIn)
       /* add.setOnClickListener(v -> {
            //insert data into firebase database
           // if(emailid.getText().toString() != null ) {
                String rollno1 = rollno.getText().toString();
                String name1 = name.getText().toString();
                String emailid1 = emailid.getText().toString();
                String course1 = course.getText().toString();
                String dept1 = dept.getText().toString();
                String contact1 = contact.getText().toString();
                //String password = passwordEditText.getText().toString();
                //  String user = new User(fname,email,profession,workplace,phone);
                user = new User(rollno1,name1, emailid1, course1, dept1, contact1);
                Toast.makeText(getActivity(), "Authentication failed.", Toast.LENGTH_SHORT).show();
                //User user= new User(fname, email, profession, workplace, phone);
                addUser();
          //  }
           // else{
                //Toast.makeText(getActivity(), "Authentication failed.", Toast.LENGTH_SHORT).show();
           // }
        });
       // return root;
   // }*/
        return root;
       // return inflater.inflate(R.layout.adduser_fragment, container, false);
    }
    //@OnClick(R.id.adduser1)
       private void addUser(String rollno1,String name1,String emailid1,String course1,String dept1,String contact1) {
           /*String rollno1 = rollno.getText().toString();
           String name1 = name.getText().toString();
           String emailid1 = emailid.getText().toString();
           String course1 = course.getText().toString();
           String dept1 = dept.getText().toString();
           String contact1 = contact.getText().toString();
           String password1="123456";*/
         //  user = new User(rollno1,name1, emailid1, course1, dept1, contact1);

           mAuth.createUserWithEmailAndPassword(emailid1, password1).addOnCompleteListener((Executor) this, (OnCompleteListener<AuthResult>) task -> {
               //progressDialog.dismiss();
               if (task.isSuccessful()) {
                   // Sign in success, update UI with the signed-in user's information
                   Log.d(TAG, "createUserWithEmail:success");
                   Toast.makeText(getActivity(), "Authentication success.", Toast.LENGTH_SHORT).show();
                   //FirebaseUser user = mAuth.getCurrentUser();
                   FirebaseUser user = mAuth.getCurrentUser();
                   updateUI(user);
               } else {
                   // If sign in fails, display a message to the user.
                   Log.w(TAG, "createUserWithEmail:failure", task.getException());
                   Toast.makeText(getActivity(), "Authentication failed.", Toast.LENGTH_SHORT).show();
               }
           });
       }
    /**
     * adding user information to database and redirect to login screen
     * @param currentUser
     */
    public void updateUI(FirebaseUser currentUser) {
        String keyid = mDatabase.push().getKey();
        mDatabase.child(keyid).setValue(user); //adding user info to database
       // Intent loginIntent = new Intent(getActivity(), login.class);
        //startActivity(loginIntent);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}