package com.example.personaltrainer.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.personaltrainer.Models.WorkoutItem;
import com.example.personaltrainer.MyApplication;
import com.example.personaltrainer.R;

import java.util.List;

public class WorkoutItemsListAdapter extends RecyclerView.Adapter<WorkoutItemsListAdapter.WorkOutItemItemViewHolder> {
    public interface ItemClickedListener
    {
        public void onItemClicked(int position);
    }
    private  ItemClickedListener itemClickedListener;

    static class WorkOutItemItemViewHolder extends RecyclerView.ViewHolder{
        TextView workOutItemName;
        TextView workOutItemDesc;
        TextView workOutItemRepsAndSets;
        ImageView workOutImage;

        public WorkOutItemItemViewHolder(@NonNull View itemView, ItemClickedListener itemClickedListener) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickedListener.onItemClicked(getAdapterPosition());
                }
            });
            workOutItemName = itemView.findViewById(R.id.workout_item_name);
            workOutItemDesc = itemView.findViewById(R.id.workout_item_desc);
            workOutItemRepsAndSets =  itemView.findViewById(R.id.workut_item_reps_sets);

            //workOutImage = itemView.findViewById(R.id.workout_info_image);
        }
    }

    List<WorkoutItem> mData;

    public WorkoutItemsListAdapter(List<WorkoutItem> data, ItemClickedListener itemClicked) {
        mData = data;
        itemClickedListener = itemClicked;
    }

    @NonNull
    @Override
    public WorkOutItemItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =
                ((LayoutInflater) MyApplication.getAppContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                        .inflate(R.layout.workout_item_details,parent,false);

        WorkOutItemItemViewHolder myViewHolder = new WorkOutItemItemViewHolder(view, itemClickedListener);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull WorkOutItemItemViewHolder holder, int position) {
        WorkoutItem workoutItem = mData.get(position);
        holder.workOutItemName.setText(workoutItem.getName());
        holder.workOutItemDesc.setText(workoutItem.getDesc());
        holder.workOutItemRepsAndSets.setText("Sets: " +workoutItem.getSets() +" reps: "+ workoutItem.getReps());

        //holder.workOutImage.setImageResource(R.drawable.ic_launcher_foreground);
    }

    @Override
    public int getItemCount() {
        if(mData == null)
            return 0;
        return  mData.size();
    }
}
