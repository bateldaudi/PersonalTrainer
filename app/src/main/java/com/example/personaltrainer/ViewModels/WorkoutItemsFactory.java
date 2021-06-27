package com.example.personaltrainer.ViewModels;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class WorkoutItemsFactory implements ViewModelProvider.Factory {

    private String workoutID;


    public WorkoutItemsFactory(String workoutID) {
        this.workoutID = workoutID;
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new WorkoutItemsViewModel (workoutID);
    }
}
