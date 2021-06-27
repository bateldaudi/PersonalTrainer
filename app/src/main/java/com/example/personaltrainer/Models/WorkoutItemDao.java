package com.example.personaltrainer.Models;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface WorkoutItemDao {
    @Query("select * from WorkoutItem WHERE workoutID = (:workoutID)")
    LiveData<List<WorkoutItem>> getAllItemsByWorkoutID(String workoutID);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(WorkoutItem... workoutItems);

    @Delete
    void delete(WorkoutItem workoutItem);
}
