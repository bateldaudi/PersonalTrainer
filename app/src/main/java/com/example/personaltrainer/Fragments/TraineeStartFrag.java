package com.example.personaltrainer.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.personaltrainer.Adapters.WorkOutListAdapter;
import com.example.personaltrainer.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.HashMap;
import java.util.Map;
import java.util.Vector;


public class TraineeStartFrag extends Fragment {

    private RecyclerView workouts;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_trainee_start, container, false);
        workouts = view.findViewById(R.id.trainee_start_workouts_rv);
        workouts.setHasFixedSize(true);

        LinearLayoutManager layoutManager  =  new LinearLayoutManager(getContext());
        workouts.setLayoutManager(layoutManager);

        Map<String, Object> data = new HashMap<>();



        // todo remove
        FirebaseFirestore.getInstance().collection("users").add(data).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                //
                Log.e("dsad","DAsd");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("dsad","DAsd");
            }
        });
        Vector<String> sl = new Vector<>();
        sl.add("SA");
        sl.add("SsasA");
        sl.add("sasasas");
        sl.add("sssssss");
        sl.add("SA");
        sl.add("SsasA");
        sl.add("sasasas");
        sl.add("sssssss"); sl.add("SA");
        sl.add("SsasA");
        sl.add("sasasas");
        sl.add("sssssss"); sl.add("SA");
        sl.add("SsasA");
        sl.add("sasasas");
        sl.add("sssssss"); sl.add("SA");
        sl.add("SsasA");
        sl.add("sasasas");
        sl.add("sssssss"); sl.add("SA");
        sl.add("SsasA");
        sl.add("sasasas");
        sl.add("sssssss");

        // specify an adapter
        WorkOutListAdapter adapter = new WorkOutListAdapter(sl, false);
        workouts.setAdapter(adapter);
        return view;

    }
}