package com.example.personaltrainer.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.personaltrainer.Adapters.WorkOutListAdapter;
import com.example.personaltrainer.Adapters.WorkoutItemsListAdapter;
import com.example.personaltrainer.Models.Model;
import com.example.personaltrainer.Models.Workout;
import com.example.personaltrainer.Models.WorkoutItem;
import com.example.personaltrainer.R;
import com.example.personaltrainer.ViewModels.TraineeWorkoutFactory;
import com.example.personaltrainer.ViewModels.TraineeWorkoutsViewModel;
import com.example.personaltrainer.ViewModels.WorkoutItemsFactory;
import com.example.personaltrainer.ViewModels.WorkoutItemsViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class WorkoutItemsList extends Fragment {
    private RecyclerView workoutItemList;
    private String workoutID;
    private WorkoutItemsViewModel workoutsVM;
    private List<WorkoutItem> workoutItems;
    private WorkoutItemsListAdapter adapter;
    private FloatingActionButton addItem;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,

                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view =
                inflater.inflate(R.layout.fragment_workout_items_list, container, false);

        workoutItemList = view.findViewById(R.id.items_workout_rv);
        addItem = view.findViewById(R.id.items_workout_fab);

        workoutID = WorkoutItemsListArgs.fromBundle(getArguments()).getWorkoutID();

        workoutsVM
                = new ViewModelProvider(this, new WorkoutItemsFactory(workoutID))
                .get(WorkoutItemsViewModel.class);


        workoutItemList.setHasFixedSize(true);

        LinearLayoutManager layoutManager  =  new LinearLayoutManager(getContext());
        workoutItemList.setLayoutManager(layoutManager);

        workoutsVM.getData().observe(getViewLifecycleOwner(), workoutItemsData ->{
            if(workoutItems == null) {
                workoutItems = workoutsVM.getData().getValue();
                adapter = new WorkoutItemsListAdapter(workoutItems, null );
                workoutItemList.setAdapter(adapter);
            }

            else {
                workoutItems.clear();
                workoutItems.addAll(workoutItemsData) ;
            }


            adapter.notifyDataSetChanged();
        });

        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               WorkoutItem workoutItem =  new WorkoutItem("1", workoutID, "sqaut", "partail", 2, 3);
                Model.instance.addWorkoutItem(workoutItem);
            }
        });
        return view;
    }
}