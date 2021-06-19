package com.example.personaltrainer.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.personaltrainer.AuthListeners;
import com.example.personaltrainer.Models.AuthenticationModel;
import com.example.personaltrainer.Models.FireBaseModel;
import com.example.personaltrainer.Models.Model;
import com.example.personaltrainer.Models.User;
import com.example.personaltrainer.R;
import com.example.personaltrainer.RedirectHelper;

public class SignInFrag extends Fragment{

    private static final int MIN_PASS_LEN = 8;
    private TextView signUpBtn;
    private Button signInBtn;
    private EditText password;
    private EditText email;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);
        // Inflate the layout for this fragment
        signUpBtn  = view.findViewById(R.id.sign_in_btn);
        signInBtn = view.findViewById(R.id.sign_in_signin_btn);
        password = view.findViewById(R.id.sign_in_password);
        email = view.findViewById(R.id.sign_in_email);


        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignInButtonWasClicked();
            }
        });
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_signInFrag_to_SIgnUpFrag);

            }
        });
        return view;
    }

    private void SignInButtonWasClicked() {

        if(ValidateSignInInput())
        {
            AuthenticationModel.getInstance().registerUser(email.getText().toString(), password.getText().toString(), new AuthListeners.RegisterListener() {
                @Override
                public void onRegisterUserComplete(String msg, String userID) {
                    if(msg.equals(AuthListeners.REGISTER_SUCCESS))
                    {
                      Model.instance.getCurrentUser(userID, new Model.UserLoaded() {
                          @Override
                          public void onCurrentUserLoaded(User user) {
                              redirectRegisteredUser(user);
                          }
                      });
                    }
                    else
                    {
                        handleErrorSigning();
                    }
                }
            }) ;
        }
    }

    private boolean ValidateSignInInput() {
        boolean isUserInfoValid = true;

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

        return isUserInfoValid;
    }


    public void handleErrorSigning() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.auth_signin_error_msg).setTitle(R.string.auth_signin_error_title);
        AlertDialog dialog = builder.create();

        dialog.show();
    }
    public void redirectRegisteredUser(User user)
    {
        RedirectHelper.redirectRegisteredUser(user, this.getView());
    }

}