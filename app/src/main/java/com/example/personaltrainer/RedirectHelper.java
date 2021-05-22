package com.example.personaltrainer;

import android.view.View;

import androidx.navigation.Navigation;

import com.example.personaltrainer.Models.User;

public class RedirectHelper {

    public static void redirectRegisteredUser(int userType, View view)
    {
        // check if trainer or trainee
        if(userType == User.TYPE_TRAINER) {
            // Start trainer view
            Navigation.findNavController(view).navigate(R.id.action_global_trainerStartFrag);
        }
        else {
            // Start trainee view
            Navigation.findNavController(view).navigate(R.id.action_global_traineeStartFrag);
        }
    }
}
