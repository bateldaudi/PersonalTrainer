package com.example.personaltrainer;

import android.graphics.ColorSpace;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.personaltrainer.Adapters.UsersLayoutAdapter;
import com.example.personaltrainer.Models.Model;
import com.example.personaltrainer.Models.User;
import com.example.personaltrainer.ViewModels.TrainerListViewModel;

import java.util.List;
import java.util.Vector;


public class ShowTrainerListDialogFrag extends DialogFragment implements UsersLayoutAdapter.OnItemClicked {

    private RecyclerView userList;
    private TrainerListViewModel trainerListViewModel;
    private List<User> trainers;
    private ProgressBar progressBar;
    private UsersLayoutAdapter adapter;

    public interface dialogClickListener {
        public void onItemClicked(String id, String name);
    }

    public ShowTrainerListDialogFrag() {
        // Empty constructor is required for DialogFragment
    }
    public static ShowTrainerListDialogFrag newInstance(String title) {
        ShowTrainerListDialogFrag frag = new ShowTrainerListDialogFrag();

        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        trainerListViewModel =
                new ViewModelProvider(this).get(TrainerListViewModel.class);

        View view =  inflater.inflate(R.layout.fragment_show_trainer_list_dialog, container, false);
        userList = view.findViewById(R.id.picture_grid_rv);
        progressBar = view.findViewById(R.id.users_dialog_progresbar);

        userList.setHasFixedSize(true);

        GridLayoutManager layoutManager  = new GridLayoutManager(getContext(), 3);
        userList.setLayoutManager(layoutManager);

        // specify an adapter
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Model.instance.trainersLoadingState.observe(getViewLifecycleOwner(),(data)->{
            switch((Model.Status)data){
                case loaded:
                {
                    progressBar.setVisibility(View.GONE);
                    break;
                }
                case loading:
                    progressBar.setVisibility(View.VISIBLE);
                    break;
                case error:
                    //TODO: display error message
            }
        });

        trainerListViewModel.getData().observe(getViewLifecycleOwner(), trainersData ->{
            if(trainers == null) {
                trainers = trainerListViewModel.getData().getValue();
                adapter = new UsersLayoutAdapter(trainers, this );
                userList.setAdapter(adapter);
            }

            else {
                trainers.clear();
                trainers.addAll(trainersData) ;
            }


            adapter.notifyDataSetChanged();
        });

    }

    @Override
    public void onItemClicked(int position) {
        //
        ( (dialogClickListener) getTargetFragment())
                .onItemClicked(trainers.get(position).getId(),
                                trainers.get(position).getName());

        // close dialog
        dismiss();

    }
}