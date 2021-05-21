package com.example.personaltrainer;

public class AuthListeners{
    public interface CreatUserListner {
        public void onCreateUserCompleted(String title);
    }
    public interface RegisterListener {
        public void onRegisterUserComplete(String title);
    }
}
