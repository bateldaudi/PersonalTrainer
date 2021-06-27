package com.example.personaltrainer.Models;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class SqlModel {
    public void addUser(User user)
    {
        class MyAsyncTask extends AsyncTask
        {
            @Override
            protected Object doInBackground(Object[] objects) {
                AppLocalDb.db.userDao().insertAll(user);
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
            }
        }

        new MyAsyncTask().execute();

    }

    public LiveData<List<User>> getAllTrainers()
    {
        return AppLocalDb.db.userDao().getAllByType(User.TYPE_TRAINER);
    }

    public LiveData<List<User>> getAllClientOfTrainer(String trainerID)
    {
        return AppLocalDb.db.userDao().getAllClientsOfTrainer(trainerID);
    }

    // workouts
    public void addWorkout(Workout workout)
    {
        class MyAsyncTask extends AsyncTask
        {
            @Override
            protected Object doInBackground(Object[] objects) {
                AppLocalDb.db.workoutDao().insertAll(workout);
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
            }
        }

        new MyAsyncTask().execute();

    }

    public LiveData<List<Workout>> getAllWorkoutsOfTrainee(String traineeID)
    {
        return AppLocalDb.db.workoutDao().getAllWorkoutsByTranieeID(traineeID);
    }

    // workout items
    public void addWorkoutItem(WorkoutItem workoutItem)
    {
        class MyAsyncTask extends AsyncTask
        {
            @Override
            protected Object doInBackground(Object[] objects) {
                AppLocalDb.db.workoutItemDao().insertAll(workoutItem);
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
            }
        }

        new MyAsyncTask().execute();

    }
    public LiveData<List<WorkoutItem>> getAllWorkoutItems(String workoutId)
    {
        return AppLocalDb.db.workoutItemDao().getAllItemsByWorkoutID(workoutId);
    }

}
