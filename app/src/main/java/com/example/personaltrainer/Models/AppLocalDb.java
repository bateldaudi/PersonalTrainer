package com.example.personaltrainer.Models;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.personaltrainer.MyApplication;

@Database(entities = {User.class, Workout.class}, version = 4)
abstract class AppLocalDbRepository extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract WorkoutDao workoutDao();
}
public class AppLocalDb{
    static public AppLocalDbRepository db =
            Room.databaseBuilder(MyApplication.getAppContext(),
                    AppLocalDbRepository.class,
                    "dbFileName.db").
                    fallbackToDestructiveMigration()
                    .build();
}
