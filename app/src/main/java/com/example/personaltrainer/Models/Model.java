package com.example.personaltrainer.Models;

public class Model {
    public static Model instance = new Model();
    FireBaseModel modelFirebase;
    SqlModel sqlModel;

    private Model(){
        modelFirebase = new FireBaseModel();
        sqlModel = new SqlModel();
    }
    public void addUser(User user) {
        sqlModel.addUser(user);
    }


}
