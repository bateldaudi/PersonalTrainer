package com.example.personaltrainer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class SIgnUpFrag extends Fragment {

    private TextView signInBtn;
    private TextView signUpBtn;
    private EditText fullName;
    private EditText email;
    private EditText password;

    private final int MIN_PASS_LEN = 8;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_s_ign_up, container, false);

        signInBtn = view.findViewById(R.id.sign_in_btn);
        signUpBtn = view.findViewById(R.id.sign_up_sign_up_btn);
        fullName = view.findViewById(R.id.sign_up_full_name);
        email = view.findViewById(R.id.sign_up_email);
        password = view.findViewById(R.id.sign_up_password);

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).popBackStack(R.id.signInFrag,false);
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HandleSignUpClicked(v);
            }

        });
        return  view;

    }

    /**
     *
     * @param v
     */
    private void HandleSignUpClicked(View v) {
        boolean isUserInfoValid = true;

        // Validate all inputs
        if (fullName.getText().toString().length() < 1)
        {
            isUserInfoValid = false;
            fullName.setError("Name can't be empty");
        }
        if (password.getText().toString().length() < MIN_PASS_LEN)
        {
            isUserInfoValid = false;
            password.setError(String.format("Password must be at least %d chars long", MIN_PASS_LEN));
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches())
        {
            isUserInfoValid = false;
            // check if email exitst
            email.setError("Please enter a valid Email address");
        }

        if(isUserInfoValid)
        {
            // save user

        }
    }
}