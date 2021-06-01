package com.example.personaltrainer.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.personaltrainer.Adapters.UsersLayoutAdapter;
import com.example.personaltrainer.Models.User;
import com.example.personaltrainer.R;

import java.util.List;
import java.util.Vector;

public class TrainerStartFrag extends Fragment {
    private RecyclerView clientsRV;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_trainer_start, container, false);
        clientsRV = view.findViewById(R.id.trainer_start_clients_rv);
        clientsRV.setHasFixedSize(true);
/*
        GridLayoutManager layoutManager  = new GridLayoutManager(getContext(), 3);
        clientsRV.setLayoutManager(layoutManager);

        List<User> sl = new Vector<>();

        // specify an adapter
        UsersLayoutAdapter adapter = new UsersLayoutAdapter(sl);
        clientsRV.setAdapter(adapter);*/
        return view;
    }
}