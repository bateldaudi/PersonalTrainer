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

    LiveData<List<User>> clientsOfTrainer  = new MutableLiveData<>();
    public LiveData<List<User>> getAllClientsOfTrainer(String trainerID) {
        trainerClientsLoadingState.setValue(Status.loading);
        if(clientsOfTrainer.getValue() == null)
        {

            modelFirebase.getAllClientsOfTrainer(trainerID, new Timestamp(0).getTime(), new FireBaseModel.IGetAllClientsOfTrainer() {
                @Override
                public void onClientsLoaded(List<User> clients) {
                    for (User client:clients) {
                        sqlModel.addUser(client);
                    }
                    trainerClientsLoadingState.setValue(Status.loaded);
                }
            });

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

    // workouts
    public void addWorkout(Workout workout) {
        modelFirebase.addWorkout(workout, new FireBaseModel.WorkoutAdded() {
            @Override
            public void onWorkoutAdded(String workoutID) {
                workout.setId(workoutID);
                sqlModel.addWorkout(workout);
            }
        });


    }

    public MutableLiveData<Status> traineeWorkoutsLoadingState =
            new MutableLiveData<>(Status.loading);

    LiveData<List<Workout>> traineeWorkouts  = new MutableLiveData<>();

    public LiveData<List<Workout>> getAllWorkoutsOfTrainee(String traineeID) {
        traineeWorkoutsLoadingState.setValue(Status.loading);

            modelFirebase.getAllWorkoutsOfTrainee(traineeID, new Timestamp(0).getTime(), new FireBaseModel.IGetAllWorkouts() {
                @Override
                public void onWorkoutsLoaded(List<Workout> workouts) {
                    for (Workout workout:workouts) {
                        sqlModel.addWorkout(workout);
                    }

                    traineeWorkoutsLoadingState.setValue(Status.loaded);

                }
            });

            traineeWorkouts = sqlModel.getAllWorkoutsOfTrainee(traineeID);
            traineeWorkouts.observeForever(data ->{
                traineeWorkoutsLoadingState.postValue(Status.loaded);
            });

        return traineeWorkouts;
    }

}
