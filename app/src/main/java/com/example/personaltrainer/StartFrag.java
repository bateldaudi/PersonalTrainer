package com.example.personaltrainer;

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

public class StartFrag extends Fragment {

    private final String DEFAULT_STRING_VALUE = "";

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);

        String userPassword = sharedPref.getString(getString(R.string.sp_user_pass), DEFAULT_STRING_VALUE);
        String userEmail = sharedPref.getString(getString(R.string.sp_user_email), DEFAULT_STRING_VALUE);
        String userType = sharedPref.getString(getString(R.string.sp_user_type), DEFAULT_STRING_VALUE);

        // Check if the user already has an account details saved
        if(!userEmail.equals(DEFAULT_STRING_VALUE) &&
                !userPassword.equals(DEFAULT_STRING_VALUE)){
            // check if trainer or trainee
            if(userType.equals(R.string.user_type_trainer)) {
                // Start trainer view
                Navigation.findNavController(view).navigate(R.id.action_global_trainerStartFrag);
            }
            else {
                // Start trainee view
                Navigation.findNavController(view).navigate(R.id.action_global_traineeStartFrag);
            }
        }
        else {
            // Go to Sign in view
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