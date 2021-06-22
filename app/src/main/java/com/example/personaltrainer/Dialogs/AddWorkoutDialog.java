package com.example.personaltrainer.Dialogs;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.personaltrainer.R;


public class AddWorkoutDialog extends DialogFragment implements View.OnClickListener {
    @Override
    public void onClick(View v) {

    }

    public interface IonAddedWorkout{
        public void onWorkoutAdded(String desc);
    }

    private TextView workoutDesc;
    private Button addBtn;

    public AddWorkoutDialog() {
        // Empty constructor is required for DialogFragment
    }
    public static AddWorkoutDialog newInstance(String title) {
        AddWorkoutDialog frag = new AddWorkoutDialog();

        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_add_workout_dialog, container, false);

        workoutDesc = view.findViewById(R.id.add_workout_desc);
        addBtn = view.findViewById(R.id.add_workout_out_add_btn);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ( (AddWorkoutDialog.IonAddedWorkout) getTargetFragment())
                        .onWorkoutAdded(workoutDesc.getText().toString());
                // close dialog
                dismiss();
            }
        });
        return view;

    }
}