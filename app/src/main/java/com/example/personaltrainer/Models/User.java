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
    private String email;
    private long lastUpdated;
    private Boolean isTrainer;
    private Boolean isTrainee;

    public String getTrainerIDOfTrainee() {
        return trainerIDOfTrainee;
    }

    public void setTrainerIDOfTrainee(String trainerIDOfTrainee) {
        this.trainerIDOfTrainee = trainerIDOfTrainee;
    }

    private String trainerIDOfTrainee;

    public User() {
    }

    public User(@NonNull String id, String name, String email, Boolean isTrainer, Boolean isTrainee,String trainerIDOfTrainee) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.isTrainer = isTrainer;
        this.isTrainee = isTrainee;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(long lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Boolean getTrainer() {
        return isTrainer;
    }

    public void setTrainer(Boolean trainer) {
        isTrainer = trainer;
    }

    public Boolean getTrainee() {
        return isTrainee;
    }

    public void setTrainee(Boolean trainee) {
        isTrainee = trainee;
    }

    public HashMap<String, Object> toMap() {
        HashMap<String, Object> userMap = new HashMap<>();
        userMap.put("name", this.id);
        userMap.put("email", this.email);
        userMap.put("isTrainer", this.isTrainer);
        userMap.put("isTrainee", this.isTrainee);
        userMap.put("trainerIDOfTrainee", this.trainerIDOfTrainee);
        userMap.put("lastUpdated", FieldValue.serverTimestamp());

        return  userMap;
    }
}
