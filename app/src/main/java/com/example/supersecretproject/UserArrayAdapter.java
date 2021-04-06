package com.example.supersecretproject;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;



public class UserArrayAdapter extends RecyclerView.Adapter<UserArrayAdapter.UserViewHolder> {
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);

        void onAddUserClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public ArrayList<User> mUserArrayList;

    public static class UserViewHolder extends RecyclerView.ViewHolder {


        public ImageView mImageView;
        public TextView mTextView1;
        public TextView textViewActualStatus;
        public TextView textViewPasswordAttempt;
        public ImageView addUser;

        public UserViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);

            mImageView = itemView.findViewById(R.id.imageView);
            mTextView1 = itemView.findViewById(R.id.textView);
            textViewActualStatus = itemView.findViewById(R.id.textViewActualStatus2);
            textViewPasswordAttempt = itemView.findViewById(R.id.textViewPasswordAttempt);
            addUser = itemView.findViewById(R.id.imageViewAddUser);


            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (listener != null) {

                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }

                    }
                }
            });

            addUser.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onAddUserClick(position);

                        }
                    }
                }
            });
        }
    }

    public UserArrayAdapter(ArrayList<User> userArrayList) {
        mUserArrayList = userArrayList;
    }


    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
        UserViewHolder uvh = new UserViewHolder(v, mListener);
        return uvh;
    }


    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User currentItem = mUserArrayList.get(position);

        // holder.mImageView.setImageResource(currentItem.getmImageResource());
        holder.mTextView1.setText(currentItem.getFullName());
        holder.textViewActualStatus.setText(currentItem.getUserStatus());
        holder.textViewPasswordAttempt.setText(currentItem.getSuperSecretPassword());





    }

    @Override
    public int getItemCount() {
        return mUserArrayList.size();
    }



}





