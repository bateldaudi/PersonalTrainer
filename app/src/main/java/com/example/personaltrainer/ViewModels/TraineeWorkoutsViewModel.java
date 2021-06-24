package com.example.personaltrainer.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.personaltrainer.Models.Model;
import com.example.personaltrainer.Models.User;
import com.example.personaltrainer.Models.Workout;

import java.util.List;

public class TraineeWorkoutsViewModel extends ViewModel {

    private LiveData<List<Workout>> data;

    public TraineeWorkoutsViewModel(String traineeID)
    {
        data = Model.instance.getAllWorkoutsOfTrainee(traineeID);
    }

    public LiveData<List<Workout>> getData()
    {
        return this.data;
    }
}
