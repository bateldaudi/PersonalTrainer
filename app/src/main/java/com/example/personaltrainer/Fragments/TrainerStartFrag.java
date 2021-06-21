package com.example.personaltrainer.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.personaltrainer.Adapters.UsersLayoutAdapter;
import com.example.personaltrainer.Models.User;
import com.example.personaltrainer.R;
import com.example.personaltrainer.ViewModels.ClientModelFactory;
import com.example.personaltrainer.ViewModels.ClientsOfTrainerListViewModel;
import com.example.personaltrainer.ViewModels.TrainerListViewModel;

import java.util.List;
import java.util.Vector;

public class TrainerStartFrag extends Fragment  implements UsersLayoutAdapter.OnItemClicked{
    private RecyclerView clientsRV;
    private ClientsOfTrainerListViewModel clientsVM;
    private TextView trainerNameTV;
    private List<User> clients;
    private  UsersLayoutAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_trainer_start, container, false);
        clientsRV = view.findViewById(R.id.trainer_start_clients_rv);
        trainerNameTV = view.findViewById(R.id.trainer_start_trainer_name);

        String trainerID =TrainerStartFragArgs.fromBundle(getArguments()).getTrainerID();
        String trainerName =TrainerStartFragArgs.fromBundle(getArguments()).getTrainerName();

        trainerNameTV.setText("Welcome " +  trainerName + ",");



        //get trainer id from somewhere
       clientsVM
               = new ViewModelProvider(this, new ClientModelFactory(trainerID)).get(ClientsOfTrainerListViewModel.class);

        clientsRV.setHasFixedSize(true);

        GridLayoutManager layoutManager  = new GridLayoutManager(getContext(), 3);
        clientsRV.setLayoutManager(layoutManager);

        List<User> sl = new Vector<>();

        // specify an adapter

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        clientsVM.getData().observe(getViewLifecycleOwner(), trainersData ->{
            if(clients == null) {
                clients = clientsVM.getData().getValue();
                adapter = new UsersLayoutAdapter(clients, this );
                clientsRV.setAdapter(adapter);
            }

            else {
                clients.clear();
                clients.addAll(trainersData) ;
            }


            adapter.notifyDataSetChanged();
        });
    }

    @Override
    public void onItemClicked(int position) {

    }
}