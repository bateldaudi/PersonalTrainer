package com.example.personaltrainer;

public class AuthListeners{
    public static String EMPTY_ID = "NO_ID";
    public static String REGISTER_SUCCESS = "REGISTER_SUCCESS";
    public static String CREATE_USER_SUCCESS = "CREATE_USER_SUCCESS";

    public interface CreatUserListner {
        public void onCreateUserCompleted(String msg,String userId);
    }
    public interface RegisterListener {
        public void onRegisterUserComplete(String msg);
    }
}
