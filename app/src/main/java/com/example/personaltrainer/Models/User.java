package com.example.personaltrainer.Models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey
    @NonNull
    private String id;
    private String name;
    private String email;
    private long BirthDate;
    private long lastUpdated;
    private Boolean isTrainer;
    private Boolean isTrainee;

    public User() {
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

    public long getBirthDate() {
        return BirthDate;
    }

    public void setBirthDate(long birthDate) {
        BirthDate = birthDate;
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
}
