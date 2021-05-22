package com.example.personaltrainer.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.personaltrainer.AuthListeners;
import com.example.personaltrainer.Models.AuthenticationModel;
import com.example.personaltrainer.Models.User;
import com.example.personaltrainer.R;
import com.example.personaltrainer.RedirectHelper;

public class StartFrag extends Fragment {

    private final String DEFAULT_STRING_VALUE = "";

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);

        String userPassword = sharedPref.getString(getString(R.string.sp_user_pass), DEFAULT_STRING_VALUE);
        String userEmail = sharedPref.getString(getString(R.string.sp_user_email), DEFAULT_STRING_VALUE);
        int userType = sharedPref.getInt(getString(R.string.sp_user_type), -1);

        // Check if the user already has an account details saved
        if(!userEmail.equals(DEFAULT_STRING_VALUE) &&
                !userPassword.equals(DEFAULT_STRING_VALUE)){

            // Try to register
            AuthenticationModel.registerUser(userEmail, userPassword, new AuthListeners.RegisterListener() {
                @Override
                public void onRegisterUserComplete(String msg) {
                    if(msg.equals(AuthListeners.REGISTER_SUCCESS))
                    {
                        RedirectHelper.redirectRegisteredUser(userType,view);
                    }
                    else
                    {
                        // move to sign in
                        Navigation.findNavController(view).navigate(R.id.action_startFrag_to_signInFrag);
                    }
                }
            });
        }
        else
        {
            // move to sign in
            Navigation.findNavController(view).navigate(R.id.action_startFrag_to_signInFrag);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  =
                inflater.inflate(R.layout.fragment_start, container, false);
        return view;
    }


}