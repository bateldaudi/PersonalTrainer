package com.example.personaltrainer.Models;

import android.graphics.Bitmap;
import android.net.Uri;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.LinkedList;
import java.util.List;

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
    interface WorkoutAdded {
        public void onWorkoutAdded(String workoutID);
    }
    interface IWorkoutItemAdded {
        public void onWorkoutItemAdded(String workoutID);
    }
    interface IGetAllClients {
        public void onClientsLoaded(List<User> users);
    }
    interface IGetAllClientsOfTrainer {
        public void onClientsLoaded(List<User> users);
    }
    interface IGetAllWorkouts {
        public void onWorkoutsLoaded(List<Workout> workouts);
    }
    interface IWorkoutItemLoaded {
        public void onItemsLoaded(List<WorkoutItem> workoutsItems);
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
    public static final String WORKOUTS_TABLE_NAME = "workouts";
    public static final String WORKOUTS_ITEMS_TABLE_NAME = "workout_item";

    public  void  addUser(User user)
    {
        FirebaseFirestore.getInstance().collection(USERS_TABLE_NAME).document(user.getId())
                .set(user.toMap()).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

            }
        });
    }

    public static  void getAllTrainers(Long lastUpdated, IGetAllClients iGetAllClients)
    {
        FirebaseFirestore.getInstance().collection(USERS_TABLE_NAME)

                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                List<User> list = new LinkedList<>();
                if(task.isSuccessful())
                {
                    for (QueryDocumentSnapshot doc: task.getResult()) {
                     list.add(new User(doc.getData(), doc.getId()));
                    }

                    iGetAllClients.onClientsLoaded(list);
                }
                else
                {

                }
            }
        });
    }

    public static  void getAllClientsOfTrainer(String trainerID,Long lastUpdated, IGetAllClientsOfTrainer iGetAllClients)
    {
        FirebaseFirestore.getInstance().collection(USERS_TABLE_NAME)
                .whereEqualTo("trainerIDOfTrainee", trainerID)
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                List<User> list = new LinkedList<>();
                if(task.isSuccessful())
                {
                    for (QueryDocumentSnapshot doc: task.getResult()) {
                        list.add(new User(doc.getData(), doc.getId()));
                    }

                    iGetAllClients.onClientsLoaded(list);
                }
                else
                {

                }
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
                    }
                } else {
                }
            }
        });
    }

    // workouts
    public  void addWorkout(Workout workout, WorkoutAdded workoutAdded )
    {
        FirebaseFirestore.getInstance().collection(WORKOUTS_TABLE_NAME)
                .add(workout.toMap()).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                workoutAdded.onWorkoutAdded(documentReference.getId());
            }
        });
    }

    public static  void getAllWorkoutsOfTrainee(String traineeID,
                                                Long lastUpdated,
                                                IGetAllWorkouts iGetAllWorkouts)
    {
        FirebaseFirestore.getInstance().collection(WORKOUTS_TABLE_NAME)
                .whereEqualTo("traineeID", traineeID)
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                List<Workout> list = new LinkedList<>();
                if(task.isSuccessful())
                {
                    for (QueryDocumentSnapshot doc: task.getResult()) {
                        list.add(new Workout(doc.getData(), doc.getId()));
                    }

                    iGetAllWorkouts.onWorkoutsLoaded(list);
                }
                else
                {

                }
            }
        });
    }

    // workouts items
    public  void addWorkoutItem(WorkoutItem workoutItem, IWorkoutItemAdded iWorkoutItemAdded )
    {
        FirebaseFirestore.getInstance().collection(WORKOUTS_ITEMS_TABLE_NAME)
                .add(workoutItem.toMap()).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                iWorkoutItemAdded.onWorkoutItemAdded(documentReference.getId());
            }
        });
    }

    public static  void getAllWorkoutItems(String workoutID,
                                                Long lastUpdated,
                                                IWorkoutItemLoaded iWorkoutItemLoaded)
    {
        FirebaseFirestore.getInstance().collection(WORKOUTS_ITEMS_TABLE_NAME)
                .whereEqualTo("workoutID", workoutID)
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                List<WorkoutItem> list = new LinkedList<>();
                if(task.isSuccessful())
                {
                    for (QueryDocumentSnapshot doc: task.getResult()) {
                        list.add(new WorkoutItem(doc.getData(), doc.getId()));
                    }

                    iWorkoutItemLoaded.onItemsLoaded(list);
                }
                else
                {

                }
            }
        });
    }
}
