package com.example.personaltrainer.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.personaltrainer.Models.Model;
import com.example.personaltrainer.Models.User;

import java.util.List;

public class ClientsOfTrainerListViewModel extends ViewModel {

    private LiveData<List<User>> data;

    public ClientsOfTrainerListViewModel(String trainerID)
    {
        data = Model.instance.getAllClientsOfTrainer(trainerID);
    }

    public LiveData<List<User>> getData()
    {
        return this.data;
    }
}
