package com.ApSpring.plato;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FriendsAdapter extends RecyclerView.Adapter<FriendsAdapter.MyViewHolder> {


    List<ExampleFriend> mList;
    Context context;
    private FriendsAdapter.onItemClickListener listener;

    public FriendsAdapter(List<ExampleFriend> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }
    public interface onItemClickListener {
        void onItemClick(int position);

    }
    public void setOnItemClickListener(FriendsAdapter.onItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.show_friends_list,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        ExampleFriend currFriend=mList.get(position);
        holder.profileImage.setImageResource(currFriend.getProfileImageId());
        holder.username.setText(currFriend.getUsername());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
      TextView username;
      ImageView profileImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            username=itemView.findViewById(R.id.friendsUserName);
            profileImage=itemView.findViewById(R.id.friendsProfileImage);
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
        }
    }
}
