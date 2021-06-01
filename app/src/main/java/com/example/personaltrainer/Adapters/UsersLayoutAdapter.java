package com.example.personaltrainer.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.personaltrainer.Models.User;
import com.example.personaltrainer.MyApplication;
import com.example.personaltrainer.R;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Vector;

import static com.example.personaltrainer.Adapters.UsersLayoutAdapter.*;

public class UsersLayoutAdapter extends RecyclerView.Adapter<MyViewHolder> {

    public interface OnItemClicked
    {
        public void onItemClicked(int position);
    }

    private OnItemClicked itemClickedListner;

    static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView userName;
        ImageView userImage;

        public MyViewHolder(@NonNull View itemView, OnItemClicked listner) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listner.onItemClicked(getAdapterPosition());
                }
            });
            userName = itemView.findViewById(R.id.user_item_name);
            userImage = itemView.findViewById(R.id.user_item_image);

        }

    }

    List<User> mData;
    public UsersLayoutAdapter(List<User> data, OnItemClicked onItemClicked) {
        super();
        mData = data;
        this.itemClickedListner = onItemClicked;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view =((LayoutInflater) MyApplication.getAppContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.user_name_picture_item,parent,false);
      MyViewHolder myViewHolder = new MyViewHolder(view, itemClickedListner);
      return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        User user = mData.get(position);
        holder.userName.setText(user.getName());
        Picasso.get().load(user.getImageUrl()).into(holder.userImage);
    }


    @Override
    public int getItemCount() {
        if(mData == null)
            return 0;
        return  mData.size();
    }
}
