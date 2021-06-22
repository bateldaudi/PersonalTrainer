package com.example.personaltrainer.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.personaltrainer.Dialogs.AddWorkoutDialog;
import com.example.personaltrainer.Models.Model;
import com.example.personaltrainer.Models.Workout;
import com.example.personaltrainer.R;
import com.example.personaltrainer.Dialogs.ShowTrainerListDialogFrag;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class editTrainerWorkOuts extends Fragment implements AddWorkoutDialog.IonAddedWorkout, View.OnClickListener {
    private TextView traineeNameTV;
    private FloatingActionButton addBtn;
    private String traineeID;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_edit_trainer_work_outs, container, false);

        String traineeName =editTrainerWorkOutsArgs.fromBundle(getArguments()).getTraineeName();
        traineeID =editTrainerWorkOutsArgs.fromBundle(getArguments()).getTraineeID();

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
}