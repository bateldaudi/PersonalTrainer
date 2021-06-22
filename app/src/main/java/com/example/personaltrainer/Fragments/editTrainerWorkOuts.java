package com.example.personaltrainer.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.personaltrainer.R;

public class editTrainerWorkOuts extends Fragment {
    private TextView traineeNameTV;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_edit_trainer_work_outs, container, false);

        String traineeName =editTrainerWorkOutsArgs.fromBundle(getArguments()).getTraineeName();
        String traineeID =editTrainerWorkOutsArgs.fromBundle(getArguments()).getTraineeID();

        traineeNameTV =  view.findViewById(R.id.edit_trainee_welcome_tv);
        traineeNameTV.setText("Start editing " + traineeName + "'s workouts");

        return view;
    }
}