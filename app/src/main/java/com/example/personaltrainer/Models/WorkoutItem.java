package com.example.personaltrainer.Models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.FieldValue;

import java.util.HashMap;
import java.util.Map;

@Entity
public class WorkoutItem {
    @PrimaryKey
    @NonNull
    private String id;
    private String workoutID;
    private String name;
    private String desc;
    private int reps;
    private int sets;
    private Long lastUpdated;

    public WorkoutItem() {

    }
    public HashMap<String, Object> toMap() {
        HashMap<String, Object> workoutItemMap = new HashMap<>();
        workoutItemMap.put("id", this.id);
        workoutItemMap.put("workoutID", this.workoutID);
        workoutItemMap.put("name", this.name);
        workoutItemMap.put("lastUpdated", FieldValue.serverTimestamp());
        workoutItemMap.put("sets", this.sets);
        workoutItemMap.put("reps", this.reps);

        return  workoutItemMap;
    }

    public WorkoutItem(String id, String workoutID, String name, String desc, int reps, int sets) {
        this.id = id;
        this.workoutID = workoutID;
        this.name = name;
        this.desc = desc;
        this.reps = reps;
        this.sets = sets;
        this.lastUpdated = new java.sql.Timestamp(0).getTime();
    }

    public WorkoutItem(Map<String, Object> workoutItem, String workoutID) {
        this.workoutID = (String) workoutItem.get("workoutID");
        this.name = (String) workoutItem.get("name");
        this.desc = (String) workoutItem.get("desc");

        this.reps =((Long)workoutItem.get("reps")).intValue();
        this.sets =((Long)workoutItem.get("sets")).intValue();

        this.id = workoutID;
        Timestamp ts = (Timestamp)workoutItem.get("lastUpdated");
        this.lastUpdated = ts.getSeconds();
    }

    public Long getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Long lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWorkoutID() {
        return workoutID;
    }

    public void setWorkoutID(String workoutID) {
        this.workoutID = workoutID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }
}
