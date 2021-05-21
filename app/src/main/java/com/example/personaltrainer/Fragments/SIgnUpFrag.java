package com.example.personaltrainer.Fragments;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.Navigation;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.example.personaltrainer.AuthListeners;
import com.example.personaltrainer.Models.AuthenticationModel;
import com.example.personaltrainer.Models.User;
import com.example.personaltrainer.R;
import com.example.personaltrainer.ShowTrainerListDialogFrag;

public class SIgnUpFrag extends Fragment implements AuthListeners.CreatUserListner {

    private TextView signInBtn;
    private TextView signUpBtn;
    private EditText fullName;
    private EditText email;
    private EditText password;
    private CheckBox wantToBeTrainee;
    private CheckBox wantToBeTrainer;
    private TextView checkBoxErrorMsg;
    private int trainerIDOfTrainee;

    private final int MIN_PASS_LEN = 8;

    @Override
    public void onCreateUserCompleted(String title) {
        // alert dialog open
        // 1. Instantiate an <code><a href="/reference/android/app/AlertDialog.Builder.html">AlertDialog.Builder</a></code> with its constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

// 2. Chain together various setter methods to set the dialog characteristics
        builder.setMessage(title).setTitle(R.string.auth_error_msg);

// 3. Get the <code><a href="/reference/android/app/AlertDialog.html">AlertDialog</a></code> from <code><a href="/reference/android/app/AlertDialog.Builder.html#create()">create()</a></code>
        AlertDialog dialog = builder.create();

        dialog.show();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_s_ign_up, container, false);

        signInBtn = view.findViewById(R.id.sign_in_btn);
        signUpBtn = view.findViewById(R.id.sign_up_sign_up_btn);
        fullName = view.findViewById(R.id.sign_up_full_name);
        email = view.findViewById(R.id.sign_up_email);
        password = view.findViewById(R.id.sign_up_password);
        wantToBeTrainee = view.findViewById(R.id.sign_up_trainee_checkbox);
        wantToBeTrainer = view.findViewById(R.id.sign_up_trainer_checkbox);
        checkBoxErrorMsg = view.findViewById(R.id.sign_up_check_box_error_msg);

        checkBoxErrorMsg.setVisibility(View.INVISIBLE);

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).popBackStack(R.id.signInFrag,false);
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HandleSignUpClicked(v);
            }

        });

        // want to be a trainee
        wantToBeTrainee.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                onWantToBeTraineeCheckedChanged(buttonView, isChecked);
            }
        });

        // want to be
        wantToBeTrainer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                onWantToBeTrainerCheckedChanged(buttonView, isChecked);
            }
        });

        return  view;

    }

    private void onWantToBeTraineeCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked)
        {
            // open dialog for choosing trainers
            FragmentManager fm = getParentFragmentManager();
            ShowTrainerListDialogFrag editNameDialogFragment = ShowTrainerListDialogFrag.newInstance("Some Title");
            editNameDialogFragment.show(fm, "fragment_edit_name");

            // Disable trainer check box
            this.wantToBeTrainer.setEnabled(false);
        }
        else
        {
            // Enable trainer option check box
            this.wantToBeTrainer.setEnabled(true);
        }
    }

    private void onWantToBeTrainerCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked)
        {
            // Disable trainee option check box
            this.wantToBeTrainee.setEnabled(false);
        }
        else
        {
            // Enable trainee option check box
            this.wantToBeTrainee.setEnabled(true);
        }
    }

    /**
     *
     * @param v
     */
    private void HandleSignUpClicked(View v) {
        boolean isUserInfoValid = true;

        // Validate all inputs
        if (fullName.getText().toString().length() < 1)
        {
            isUserInfoValid = false;
            fullName.setError("Name can't be empty");
        }
        if (password.getText().toString().length() < MIN_PASS_LEN)
        {
            isUserInfoValid = false;
            password.setError(String.format("Password must be at least %d chars long", MIN_PASS_LEN));
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches())
        {
            isUserInfoValid = false;
            // check if email exitst
            email.setError("Please enter a valid Email address");
        }
        if(!wantToBeTrainee.isChecked() &&
                !wantToBeTrainer.isChecked()) {
            isUserInfoValid = false;
            checkBoxErrorMsg.setVisibility(View.VISIBLE);

        }


        if(isUserInfoValid)
        {
            // create user
            User user =
                    new User("1", fullName.getText().toString(), email.getText().toString(),wantToBeTrainer.isChecked(),wantToBeTrainee.isChecked(),"trainerIDOfTrainee" );
            // TODO CHANGE TO USE MODEL
            //FireBaseModel.getInstance().addUser(user);
            AuthenticationModel.createUser(email.getText().toString(), password.getText().toString(), this);
        }
    }
}