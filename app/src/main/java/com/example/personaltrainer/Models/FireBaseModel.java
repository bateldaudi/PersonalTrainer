package com.example.personaltrainer.Models;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class FireBaseModel {
    private static FireBaseModel fireBaseModel = null;

    private FireBaseModel()
    {

    }

    public static FireBaseModel getInstance()
    {
        if(fireBaseModel == null)
        {
            fireBaseModel = new FireBaseModel();
        }

        return fireBaseModel;
    }


    public static final String USERS_TABLE_NAME = "users";
    public  void  addUser(User user)
    {
        FirebaseFirestore.getInstance().collection(USERS_TABLE_NAME)
                .add(user.toMap()).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }
}
