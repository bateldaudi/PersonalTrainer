package com.example.personaltrainer.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.personaltrainer.Models.Workout;
import com.example.personaltrainer.MyApplication;
import com.example.personaltrainer.R;

import java.util.List;
import java.util.Vector;

public class WorkOutListAdapter extends RecyclerView.Adapter<WorkOutListAdapter.WorkOutItemViewHolder> {

    public interface ItemClickedListener
    {
        public void onItemClicked(int position);
    }
    private ItemClickedListener itemClickedListener;
    static class WorkOutItemViewHolder extends RecyclerView.ViewHolder{
        TextView workOutName;
        ImageView workOutImage;
        ImageView actionBtn;


        public WorkOutItemViewHolder(@NonNull View itemView, ItemClickedListener itemClickedListener) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickedListener.onItemClicked(getAdapterPosition());
                }
            });

            workOutName = itemView.findViewById(R.id.workout_info_name);
            workOutImage = itemView.findViewById(R.id.workout_info_image);
            actionBtn =  itemView.findViewById(R.id.workout_info_action);

        }

    }

    List<Workout> mData;

    public WorkOutListAdapter(List<Workout> data, ItemClickedListener itemClickedListener) {
        mData = data;
        this.itemClickedListener = itemClickedListener;
    }

    @NonNull
    @Override
    public WorkOutItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view =
              ((LayoutInflater) MyApplication.getAppContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                      .inflate(R.layout.work_out_info_item,parent,false);

      WorkOutItemViewHolder myViewHolder = new WorkOutItemViewHolder(view, this.itemClickedListener);
      return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull WorkOutItemViewHolder holder, int position) {
        Workout workout = mData.get(position);
        holder.workOutName.setText(workout.getDescription());
        holder.workOutImage.setImageResource(R.drawable.ic_launcher_foreground);
    }


    @Override
    public int getItemCount() {
        if(mData == null)
            return 0;
        return  mData.size();
    }
}
