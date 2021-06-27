package com.example.personaltrainer.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.personaltrainer.Adapters.UsersLayoutAdapter;
import com.example.personaltrainer.Adapters.WorkOutListAdapter;
import com.example.personaltrainer.Dialogs.AddWorkoutDialog;
import com.example.personaltrainer.Models.Model;
import com.example.personaltrainer.Models.Workout;
import com.example.personaltrainer.NavGraphDirections;
import com.example.personaltrainer.R;
import com.example.personaltrainer.Dialogs.ShowTrainerListDialogFrag;
import com.example.personaltrainer.ViewModels.ClientModelFactory;
import com.example.personaltrainer.ViewModels.ClientsOfTrainerListViewModel;
import com.example.personaltrainer.ViewModels.TraineeWorkoutFactory;
import com.example.personaltrainer.ViewModels.TraineeWorkoutsViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class editTrainerWorkOuts extends Fragment implements AddWorkoutDialog.IonAddedWorkout, View.OnClickListener, WorkOutListAdapter.ItemClickedListener {
    private TextView traineeNameTV;
    private FloatingActionButton addBtn;
    private String traineeID;
    private List<Workout> workouts;
    private TraineeWorkoutsViewModel traineeWorkoutsViewModel;
    private WorkOutListAdapter adapter;
    private RecyclerView workoutList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_edit_trainer_work_outs, container, false);

        String traineeName =editTrainerWorkOutsArgs.fromBundle(getArguments()).getTraineeName();
        traineeID = editTrainerWorkOutsArgs.fromBundle(getArguments()).getTraineeID();
        workoutList = view.findViewById(R.id.edit_workouts_rv);

        traineeWorkoutsViewModel
                = new ViewModelProvider(this, new TraineeWorkoutFactory(traineeID))
                .get(TraineeWorkoutsViewModel.class);


        workoutList.setHasFixedSize(true);

        LinearLayoutManager layoutManager  =  new LinearLayoutManager(getContext());
        workoutList.setLayoutManager(layoutManager);

        traineeWorkoutsViewModel.getData().observe(getViewLifecycleOwner(), workoutsData ->{
            if(workouts == null) {
                workouts = traineeWorkoutsViewModel.getData().getValue();
                adapter = new WorkOutListAdapter(workouts, this );
                workoutList.setAdapter(adapter);
            }

            else {
                workouts.clear();
                workouts.addAll(workoutsData) ;
            }


            adapter.notifyDataSetChanged();
        });

        traineeNameTV =  view.findViewById(R.id.edit_trainee_welcome_tv);
        addBtn =  view.findViewById(R.id.edit_traine_add_fab);
        addBtn.setOnClickListener(this);

        traineeNameTV.setText("Start editing " + traineeName + "'s workouts");
        return view;
    }

    @Override
    public void onWorkoutAdded(String desc) {
        // save new workout
        Workout addedWorkout = new Workout(traineeID, desc);
        Model.instance.addWorkout(addedWorkout);
        workouts.add(workouts.size(), addedWorkout);
        adapter.notifyDataSetChanged();

        // add workout
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.edit_traine_add_fab)
        {
            FragmentManager fm = getParentFragmentManager();
            AddWorkoutDialog addWorkoutDialog = AddWorkoutDialog.newInstance("Add new workout");
            addWorkoutDialog.setTargetFragment(this, 0);
            addWorkoutDialog.show(fm, "fragment_add_workout");
        }
    }

    @Override
    public void onItemClicked(int position) {
        // open workout items view
        NavDirections action  = NavGraphDirections.actionGlobalWorkoutItemsList(traineeID);
        // Start trainer view
        Navigation.findNavController(getView()).navigate(action);
    }
}