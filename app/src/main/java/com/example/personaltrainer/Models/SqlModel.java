package com.example.personaltrainer.Models;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class SqlModel {
    public void addUser(User user)
    {
        class MyAsyncTask extends AsyncTask
        {
            @Override
            protected Object doInBackground(Object[] objects) {
                AppLocalDb.db.userDao().insertAll(user);
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
            }
        }

        new MyAsyncTask().execute();

    }
    public LiveData<List<User>> getAllTrainers()
    {
        return AppLocalDb.db.userDao().getAllByType(User.TYPE_TRAINER);

    }
}
