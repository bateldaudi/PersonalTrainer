package com.example.personaltrainer.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
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
import com.example.personaltrainer.Models.Model;
import com.example.personaltrainer.Models.User;
import com.example.personaltrainer.R;
import com.example.personaltrainer.RedirectHelper;
import com.example.personaltrainer.ShowTrainerListDialogFrag;

public class SIgnUpFrag extends Fragment{

    private TextView signInBtn;
    private TextView signUpBtn;
    private EditText fullName;
    private EditText email;
    private EditText password;
    private CheckBox wantToBeTrainee;
    private CheckBox wantToBeTrainer;
    private TextView checkBoxErrorMsg;
    private int trainerIDOfTrainee;
    private View layout;

    private final int MIN_PASS_LEN = 8;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_s_ign_up, container, false);
        layout = view;

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
        if(isChecked) {
            // open dialog for choosing trainers
            FragmentManager fm = getParentFragmentManager();
            ShowTrainerListDialogFrag editNameDialogFragment = ShowTrainerListDialogFrag.newInstance("Some Title");
            editNameDialogFragment.show(fm, "fragment_edit_name");

            // Disable trainer check box
            this.wantToBeTrainer.setEnabled(false);
        }
        else {
            // Enable trainer option check box
            this.wantToBeTrainer.setEnabled(true);
        }
    }

    private void onWantToBeTrainerCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked) {
            // Disable trainee option check box
            this.wantToBeTrainee.setEnabled(false);
        }
        else {
            // Enable trainee option check box
            this.wantToBeTrainee.setEnabled(true);
        }
    }

    /**
     *
     * @param v
     */
    private void HandleSignUpClicked(View v) {
        String emailValue = email.getText().toString();
        String passwordValue = password.getText().toString();

        if(validateSignUpInput())
        {
            AuthenticationModel.createUser(emailValue, passwordValue, new AuthListeners.CreatUserListner() {
                @Override
                public void onCreateUserCompleted(String msg, String userID) {
                    if(!msg.equals(AuthListeners.CREATE_USER_SUCCESS))
                    {
                        // Open Error Dialog
                        OpenErrorCreatingUserDialog(msg);
                    }
                    else
                    {
                        int userType =
                                wantToBeTrainer.isChecked() ?
                                        User.TYPE_TRAINER :
                                        User.TYPE_TRAINEE;
                        //User user =
                       User user =  new User(userID,
                                fullName.getText()
                                        .toString(),
                                userType,
                                "trainerIDOfTrainee");

                        saveUserInfoToSharedPref(emailValue, passwordValue, userType);

                        Model.instance.addUser(user);

                        RedirectHelper.redirectRegisteredUser(userType, layout);


                    }
                }
            });

        }
    }

    private boolean validateSignUpInput()
    {
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

        return isUserInfoValid;
    }

    public void OpenErrorCreatingUserDialog(String msg) {
        // alert dialog open
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(msg).setTitle(R.string.auth_error_msg);
        AlertDialog dialog = builder.create();

        dialog.show();
    }

    public void saveUserInfoToSharedPref(String email, String password, int userType)
    {
        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putString(getString(R.string.sp_user_pass),password);
        editor.putString(getString(R.string.sp_user_email),email);
        editor.putInt(getString(R.string.sp_user_type), userType);

        editor.apply();

    }
}