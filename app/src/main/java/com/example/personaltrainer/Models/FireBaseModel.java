package com.example.personaltrainer.Models;

import android.graphics.Bitmap;
import android.net.Uri;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

public class FireBaseModel {

    public FireBaseModel()
    {

    }
    interface PicUploadListener {
        public void onPicUploaded(String url);
    }
    interface UserFetched {
        public void onUserFetch(User user);
    }
    public void uploadImage(Bitmap bitmap, String userId, PicUploadListener picUploadListener)
    {
        StorageReference storageRef = FirebaseStorage.getInstance().getReference();

        StorageReference mountainsRef = storageRef.child("userProfileImage").child(userId);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        UploadTask uploadTask = mountainsRef.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                picUploadListener.onPicUploaded("-1");
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
               mountainsRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                   @Override
                   public void onSuccess(Uri uri) {
                       picUploadListener.onPicUploaded(uri.toString());
                   }
               });
            }
        });

    }

    public static final String USERS_TABLE_NAME = "users";

    public  void  addUser(User user)
    {
        FirebaseFirestore.getInstance().collection(USERS_TABLE_NAME).document(user.getId())
                .set(user.toMap()).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

            }
        });
    }


    public void getUser(String userID,UserFetched userFetched )
    {
        DocumentReference docRef = FirebaseFirestore.getInstance()
                .collection(USERS_TABLE_NAME).document(userID);
        docRef.get().addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                int a = 8;
            }
        });

        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        userFetched.onUserFetch(new User(document.getData(), document.getId()));
                    } else {
int d = 0 ;
                    }
                } else {
int a = 0;
                }
            }
        });
    }
}
