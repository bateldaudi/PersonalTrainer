package com.example.personaltrainer.ViewModels;

import android.app.Application;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ClientModelFactory implements ViewModelProvider.Factory {
    private String trainerID;


    public ClientModelFactory(String trainerID) {
        this.trainerID = trainerID;
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new ClientsOfTrainerListViewModel(trainerID);
    }
}