package com.ApSpring.plato.profile;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ApSpring.plato.R;
import com.ApSpring.plato.friends.FriendsAdapter;

public class accountRecyclerViewAdapter extends RecyclerView.Adapter<accountRecyclerViewAdapter.MyViewHolder> {

    private String[] recyclerViewAccountTitles;
    private String[] recyclerViewAccountDescriptions;
    Context context;

    public accountRecyclerViewAdapter(Context context, String[] accountTitles, String[] accountDescriptions){
        this.context = context;
        this.recyclerViewAccountDescriptions = accountDescriptions;
        this.recyclerViewAccountTitles = accountTitles;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.accountsettings_row, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.title.setText(recyclerViewAccountTitles[position]);
        holder.des.setText(recyclerViewAccountDescriptions[position]);

        if(position == 0){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ChangeUserName.class);
                    context.startActivity(intent);

                }
            });
        }else if (position == 1) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ChangePasswordActivity.class);
                    context.startActivity(intent);
                }
            });
        }else if (position == 2){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ChangeBioActivity.class);
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return recyclerViewAccountDescriptions.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title, des;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.account_setting_title);
            des = itemView.findViewById(R.id.account_setting_des);
        }
    }
}
