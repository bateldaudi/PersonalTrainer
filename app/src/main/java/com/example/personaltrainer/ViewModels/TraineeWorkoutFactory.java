package com.example.personaltrainer.ViewModels;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class TraineeWorkoutFactory implements ViewModelProvider.Factory {
    private String traineeID;


    public TraineeWorkoutFactory(String traineeID) {
        this.traineeID = traineeID;
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new TraineeWorkoutsViewModel(traineeID);
    }
}
