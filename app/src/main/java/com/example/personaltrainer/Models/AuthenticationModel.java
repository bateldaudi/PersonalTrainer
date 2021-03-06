package com.example.personaltrainer.Models;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.personaltrainer.AuthListeners;
import com.example.personaltrainer.Fragments.SIgnUpFrag;
import com.example.personaltrainer.MyApplication;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.Executor;

public class AuthenticationModel {
    private static AuthenticationModel authenticationModel;

    public static AuthenticationModel getInstance()
    {
        if(authenticationModel == null)
        {
            authenticationModel = new AuthenticationModel();
        }

        return authenticationModel;
    }

    private AuthenticationModel() {

    }
    public static void createUser(String email, String password, AuthListeners.CreatUserListner creatUserListner)
    {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            creatUserListner
                                    .onCreateUserCompleted(AuthListeners.CREATE_USER_SUCCESS,
                                            FirebaseAuth.getInstance().getCurrentUser().getUid());
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            creatUserListner.onCreateUserCompleted(task.getException().getMessage(), AuthListeners.EMPTY_ID);
                        }
                    }
                });
    }

    public static void registerUser(String email, String password, AuthListeners.RegisterListener registerListener)
    {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            registerListener.onRegisterUserComplete(AuthListeners.REGISTER_SUCCESS, task.getResult().getUser().getUid());
                        } else {
                            registerListener.onRegisterUserComplete(task.getException().getMessage(), "-1");
                        }
                    }
                });
    }
}
