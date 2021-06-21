package com.example.personaltrainer.Models;

import android.graphics.Bitmap;
import android.view.Display;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Model {
    public static Model instance = new Model();
    FireBaseModel modelFirebase;
    SqlModel sqlModel;

    ExecutorService executorService = Executors.newSingleThreadExecutor();
    public enum Status{
        loading,
        loaded,
        error
    }

    private Model(){
        modelFirebase = new FireBaseModel();
        sqlModel = new SqlModel();
    }

    public void addUser(User user, Bitmap userProfilePic) {
        modelFirebase.uploadImage(userProfilePic, user.getId(), new FireBaseModel.PicUploadListener() {
            @Override
            public void onPicUploaded(String url) {
                user.setImageUrl(url);
                modelFirebase.addUser(user);
                sqlModel.addUser(user);
            }
        });

    }
    public MutableLiveData<Status> trainersLoadingState =
            new MutableLiveData<>(Status.loading);

    LiveData<List<User>> trainers = AppLocalDb.db.userDao().getAllByType(User.TYPE_TRAINER);

    public LiveData<List<User>> getAllTrainers() {
       trainersLoadingState.setValue(Status.loading);

        modelFirebase.getAllTrainers(new Timestamp(0).getTime(), new FireBaseModel.IGetAllClients() {
            @Override
            public void onClientsLoaded(List<User> users) {
                for (User trainer:users) {
                    sqlModel.addUser(trainer);
                }
                trainersLoadingState.setValue(Status.loaded);
            }
        });

        return trainers;
    }
    public MutableLiveData<Status> trainerClientsLoadingState =
            new MutableLiveData<>(Status.loading);

    LiveData<List<User>> clientsOfTrainer;
    public LiveData<List<User>> getAllClientsOfTrainer(String trainerID) {
        if(clientsOfTrainer == null)
        {
            clientsOfTrainer = sqlModel.getAllClientOfTrainer(trainerID);
            clientsOfTrainer.observeForever(data ->{
                trainerClientsLoadingState.postValue(Status.loaded);
            });
        }

        return clientsOfTrainer;
    }
    User currUser = null;

    public interface UserLoaded
    {
        void onCurrentUserLoaded(User user);
    }
    public void getCurrentUser(String userID, UserLoaded userFetched) {
        modelFirebase.getUser(userID, new FireBaseModel.UserFetched() {
            @Override
            public void onUserFetch(User user) {
                userFetched.onCurrentUserLoaded(user);
                sqlModel.addUser(user);
            }
        });

    }


}
