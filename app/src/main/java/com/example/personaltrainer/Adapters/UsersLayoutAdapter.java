package com.example.personaltrainer.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.personaltrainer.MyApplication;
import com.example.personaltrainer.R;

import java.util.Vector;

import static com.example.personaltrainer.Adapters.UsersLayoutAdapter.*;

public class UsersLayoutAdapter extends RecyclerView.Adapter<MyViewHolder> {

    static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView userName;
        ImageView userImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.user_item_name);
            userImage = itemView.findViewById(R.id.user_item_image);

        }

    }

    Vector<String> mData;
    public UsersLayoutAdapter(Vector<String> data) {
        mData = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view =((LayoutInflater) MyApplication.getAppContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.user_name_picture_item,parent,false);
      MyViewHolder myViewHolder = new MyViewHolder(view);
      return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String str = mData.elementAt(position);
        holder.userName.setText("bate;" + str);
        holder.userImage.setImageResource(R.drawable.white_logo_small);

    }


    @Override
    public int getItemCount() {
        return mData.size();
    }
}
