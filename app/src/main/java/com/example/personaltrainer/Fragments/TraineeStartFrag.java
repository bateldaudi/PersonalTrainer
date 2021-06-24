package com.example.personaltrainer.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.personaltrainer.Adapters.WorkOutListAdapter;
import com.example.personaltrainer.Models.Model;
import com.example.personaltrainer.Models.Workout;
import com.example.personaltrainer.R;
import com.example.personaltrainer.ViewModels.TraineeWorkoutFactory;
import com.example.personaltrainer.ViewModels.TraineeWorkoutsViewModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;


public class TraineeStartFrag extends Fragment {

    private RecyclerView workouts;
    private List<Workout> workoutsList;
    private TraineeWorkoutsViewModel traineeWorkoutsViewModel;
    private WorkOutListAdapter adapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_trainee_start, container, false);

        mSwipeRefreshLayout = view.findViewById(R.id.trainee_start_pull_refresh);

        String traineeID =TraineeStartFragArgs.fromBundle(getArguments()).getTraineeID();
        String traineeName =TraineeStartFragArgs.fromBundle(getArguments()).getTraineeName();

        // on refresh get all workouts
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Model.instance.getAllWorkoutsOfTrainee(traineeID);
            }
        });

        traineeWorkoutsViewModel
                = new ViewModelProvider(this, new TraineeWorkoutFactory(traineeID))
                .get(TraineeWorkoutsViewModel.class);

        workouts = view.findViewById(R.id.trainee_start_workouts_rv);
        workouts.setHasFixedSize(true);

        LinearLayoutManager layoutManager  =  new LinearLayoutManager(getContext());
        workouts.setLayoutManager(layoutManager);

        traineeWorkoutsViewModel.getData().observe(getViewLifecycleOwner(), workoutsData ->{
            if(workoutsList == null) {
                workoutsList = traineeWorkoutsViewModel.getData().getValue();
                adapter = new WorkOutListAdapter(workoutsList, false );
                workouts.setAdapter(adapter);
            }

            else {
                workoutsList.clear();
                workoutsList.addAll(workoutsData) ;
            }


            adapter.notifyDataSetChanged();
        });

        Model.instance.traineeWorkoutsLoadingState.observe(getViewLifecycleOwner(),(data)-> {
            switch ((Model.Status) data) {
                case loaded: {
                    mSwipeRefreshLayout.setRefreshing(false);
                    break;
                }
                case loading:

                case error:
                    //TODO: display error message
            }
        });

        return view;

    }
}