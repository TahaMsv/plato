package com.ApSpring.plato.chat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ApSpring.plato.R;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MyViewHolder> {

    public static final int MSG_TYPE_LEFT = 0;
    public static final int MSG_TYPE_RIGHT = 1;
    List<Chat> mChat;
    Context context;
    String imageUrl;


    public MessageAdapter(List<Chat> mChat, Context context) {
        this.mChat = mChat;
        this.context = context;
        ///  this.imageUrl = imageUrl;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == MSG_TYPE_RIGHT) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            View view = layoutInflater.inflate(R.layout.chat_item_right, parent, false);
            return new MyViewHolder(view);
        }
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.chat_item_left, parent, false);

        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Chat chat = mChat.get(position);
        holder.showMessage.setText(chat.getMessage());

    }

    @Override
    public int getItemCount() {
        return mChat.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView showMessage;
        ImageView profileImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            showMessage = itemView.findViewById(R.id.friendsUserName);
            profileImage = itemView.findViewById(R.id.friendsProfileImage);
        }
    }

    @Override
    public int getItemViewType(int position) {
        Chat chat = mChat.get(position);
        if (chat.getSender().equals("me"))
            return MSG_TYPE_RIGHT;
        return MSG_TYPE_LEFT;
    }
}
