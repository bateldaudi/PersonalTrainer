package com.example.personaltrainer.Models;

import android.graphics.Bitmap;
import android.net.Uri;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
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
