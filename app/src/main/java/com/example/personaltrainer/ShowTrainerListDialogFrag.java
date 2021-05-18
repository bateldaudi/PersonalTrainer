package com.example.personaltrainer;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.personaltrainer.Adapters.UsersLayoutAdapter;

import java.util.Vector;


public class ShowTrainerListDialogFrag extends DialogFragment {
    private RecyclerView userList;

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
        View view =  inflater.inflate(R.layout.fragment_show_trainer_list_dialog, container, false);
        userList = view.findViewById(R.id.picture_grid_rv);
        userList.setHasFixedSize(true);

        GridLayoutManager layoutManager  = new GridLayoutManager(getContext(), 3);
        userList.setLayoutManager(layoutManager);

        Vector<String> sl = new Vector<>();
        sl.add("SA");
        sl.add("SsasA");
        sl.add("sasasas");
        sl.add("sssssss");
        sl.add("SA");
        sl.add("SsasA");
        sl.add("sasasas");
        sl.add("sssssss"); sl.add("SA");
        sl.add("SsasA");
        sl.add("sasasas");
        sl.add("sssssss"); sl.add("SA");
        sl.add("SsasA");
        sl.add("sasasas");
        sl.add("sssssss"); sl.add("SA");
        sl.add("SsasA");
        sl.add("sasasas");
        sl.add("sssssss"); sl.add("SA");
        sl.add("SsasA");
        sl.add("sasasas");
        sl.add("sssssss");

        // specify an adapter
        UsersLayoutAdapter adapter = new UsersLayoutAdapter(sl);
        userList.setAdapter(adapter);
        return view;

    }
}