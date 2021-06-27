package com.example.personaltrainer.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.personaltrainer.Models.Model;
import com.example.personaltrainer.Models.Workout;
import com.example.personaltrainer.Models.WorkoutItem;

import java.util.List;

public class WorkoutItemsViewModel extends ViewModel {
    private LiveData<List<WorkoutItem>> data;

    public WorkoutItemsViewModel(String workoutID)
    {
        data = Model.instance.getAllWorkoutItems(workoutID);
    }

    public LiveData<List<WorkoutItem>> getData()
    {
        return this.data;
    }
}
