package com.example.supersecretproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;



public class UserArrayAdapter extends RecyclerView.Adapter<UserArrayAdapter.UserViewHolder> {
    private OnItemClickListener mListener;
    private Context context;

    public UserArrayAdapter(ArrayList<User> list, Context context) {
        this.context = context;
        this.mUserArrayList = list;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
        void onAddUserClick(int position);


    }

    // constructors for the arrayAdapter
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public ArrayList<User> mUserArrayList;

    public static class UserViewHolder extends RecyclerView.ViewHolder {
//declarations
        public ImageView mImageView;
        public TextView mTextView1;
        public TextView textViewActualStatus;
        public TextView textViewPasswordAttempt;
        public ImageView addUser;
        private Context context;

        //sets on click listeners
        public UserViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);

            mImageView = itemView.findViewById(R.id.imageView);
            mTextView1 = itemView.findViewById(R.id.textViewTitle);
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

                    //on click listener to animate card when clicked, currently crashing the app

                    /*  Animation shakeCard = AnimationUtils.loadAnimation(context, R.anim.shake);
                    shakeCard.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });*/


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

//populates the card and runs some validation on the data and
    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User currentItem = mUserArrayList.get(position);

        // holder.mImageView.setImageResource(currentItem.getmImageResource());
        if (currentItem.getUserStatus().equals("Awaiting validation")) {
            holder.itemView.setBackgroundColor(context.getResources().getColor(R.color.red));

        }
        holder.mTextView1.setText(currentItem.getFullName());
        holder.textViewActualStatus.setText(currentItem.getUserStatus());
        holder.textViewPasswordAttempt.setText(currentItem.getSuperSecretPassword());

    }

    //returns the item count of the list, needed to generate teh recycler view
    @Override
    public int getItemCount() {
        return mUserArrayList.size();
    }



}













