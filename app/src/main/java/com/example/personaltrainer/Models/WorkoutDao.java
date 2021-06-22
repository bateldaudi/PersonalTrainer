package com.example.personaltrainer.Models;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.personaltrainer.Adapters.WorkOutListAdapter;

import java.util.List;

@Dao
public interface WorkoutDao {

    @Query("select * from Workout WHERE traineeID = (:traineeID)")
    LiveData<List<Workout>> getAllWorkoutsByTranieeID(String traineeID);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Workout... workouts);

    @Delete
    void delete(Workout workout);
}
