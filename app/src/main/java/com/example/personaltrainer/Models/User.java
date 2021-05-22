package com.example.personaltrainer.Models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.firebase.firestore.FieldValue;

import java.util.HashMap;

@Entity
public class User {
    @PrimaryKey
    @NonNull
    private String id;
    private String name;
    private long lastUpdated;
    private int type;
    private String trainerIDOfTrainee;

    public static final int TYPE_TRAINER = 1;
    public static final int TYPE_TRAINEE = 2;

    public User() {
    }

    public User(@NonNull String id, String name,int type,String trainerIDOfTrainee) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.trainerIDOfTrainee = trainerIDOfTrainee;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public long getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(long lastUpdated) {
        this.lastUpdated = lastUpdated;
    }


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    public String getTrainerIDOfTrainee() {
        return trainerIDOfTrainee;
    }

    public void setTrainerIDOfTrainee(String trainerIDOfTrainee) {
        this.trainerIDOfTrainee = trainerIDOfTrainee;
    }

    public HashMap<String, Object> toMap() {
        HashMap<String, Object> userMap = new HashMap<>();
        userMap.put("name", this.id);
        userMap.put("type", this.type);
        userMap.put("trainerIDOfTrainee", this.trainerIDOfTrainee);
        userMap.put("lastUpdated", FieldValue.serverTimestamp());

        return  userMap;
    }
}
