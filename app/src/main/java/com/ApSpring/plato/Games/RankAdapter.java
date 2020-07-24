package com.ApSpring.plato.Games;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ApSpring.plato.R;

import java.util.List;

public class RankAdapter extends RecyclerView.Adapter<RankAdapter.MyViewHolder> {

    List<Player> mTopPlayerList;
    Context context;



    public RankAdapter(List<Player> mTopPlayerList, Context context) {
        this.mTopPlayerList = mTopPlayerList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            LayoutInflater layoutInflater = LayoutInflater.from(context);
            View view = layoutInflater.inflate(R.layout.showtopplayers, parent, false);
            return new MyViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        Player player=  mTopPlayerList.get(position);
        if (player.getUsername() != null) {
            holder.showUsername.setText(player.getUsername());
            holder.showRank.setText(player.getRank());
            holder.showScore.setText(player.getScore());
        } else
            holder.showUsername.setText("x");

    }

    @Override
    public int getItemCount() {
        return mTopPlayerList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView showUsername;
        TextView showRank;
        TextView showScore;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            showUsername = itemView.findViewById(R.id.playerName);
            showRank = itemView.findViewById(R.id.rankTextView);
            showScore=itemView.findViewById(R.id.scoreTextView);
//            profileImage = itemView.findViewById(R.id.friendsProfileImage);
        }
    }

//    @Override
//    public int getItemViewType(int position) {
//        Chat chat = mTopPlayerList.get(position);
//        if (chat.getSender().equals("me"))
//            return MSG_TYPE_RIGHT;
//        return MSG_TYPE_LEFT;
//    }
}
