package com.example.personaltrainer.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.personaltrainer.Models.Model;
import com.example.personaltrainer.Models.User;

import java.util.List;

public class TrainerListViewModel extends ViewModel {
    private LiveData<List<User>> data;
    public TrainerListViewModel()
    {
        data = Model.instance.getAllTrainers();
    }

    public LiveData<List<User>> getData()
    {
        return this.data;
    }
}
