package com.example.personaltrainer.Models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.FieldValue;

import java.util.HashMap;
import java.util.Map;

@Entity
public class Workout {

    @PrimaryKey
    @NonNull
    private String id;
    private String traineeID;
    private String description;
    public static  int UID_SEQ = 0;

    public Long getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Long lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    private Long lastUpdated;

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getTraineeID() {
        return traineeID;
    }

    public void setTraineeID(String traineeID) {
        this.traineeID = traineeID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Workout()
    {

    }
    public HashMap<String, Object> toMap() {
        HashMap<String, Object> workoutMap = new HashMap<>();
        workoutMap.put("id", this.id);
        workoutMap.put("traineeID", this.traineeID);
        workoutMap.put("description", this.description);
        workoutMap.put("lastUpdated", FieldValue.serverTimestamp());

        return  workoutMap;
    }
    public Workout(Map<String, Object> workout, String workoutID) {
        this.traineeID = (String) workout.get("traineeID");
        this.description = (String) workout.get("description");
        Timestamp ts = (Timestamp)workout.get("lastUpdated");
        this.lastUpdated = ts.getSeconds();
        this.id = workoutID;
    }

    public Workout(String traineeID, String description) {
        this.id = String.valueOf(UID_SEQ++);
        this.traineeID = traineeID;
        this.description = description;
        this.lastUpdated = new java.sql.Timestamp(0).getTime();
    }
}
