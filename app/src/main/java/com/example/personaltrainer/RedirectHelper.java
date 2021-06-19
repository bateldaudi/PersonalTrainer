package com.example.personaltrainer;

import android.os.Bundle;
import android.view.View;

import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.example.personaltrainer.Models.User;

public class RedirectHelper {

    public static void redirectRegisteredUser(User user, View view)
    {
        // check if trainer or trainee
        if(user.getType() == User.TYPE_TRAINER) {

            NavDirections action  = NavGraphDirections.actionGlobalTrainerStartFrag(user.getId(), user.getName());
            // Start trainer view
            Navigation.findNavController(view).navigate(action);
        }
        else {
            // Start trainee view
            NavDirections action  = NavGraphDirections.actionGlobalTraineeStartFrag(user.getId());
            // Start trainer view
            Navigation.findNavController(view).navigate(action);
        }
    }
}
