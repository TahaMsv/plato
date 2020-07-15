package com.ApSpring.plato.profile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ApSpring.plato.R;

public class accountRecyclerViewAdapter extends RecyclerView.Adapter<accountRecyclerViewAdapter.MyViewHolder> {

    private String[] recyclerViewAccountTitles;
    private String[] recyclerViewAccountDescriptions;

    private Context context;

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
