package com.ApSpring.plato.Games;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ApSpring.plato.NetworkHandlerThread;
import com.ApSpring.plato.R;

import java.util.Arrays;

public class TicTocToe extends AppCompatActivity {
    public static NetworkHandlerThread netThread;
//    public ListenForOtherUser listenForOtherUser;
    public static final int YELLOW_CODE = 0;
    public static final int RED_CODE = 1;
    public static final int NOT_PLAYED = 2;
    public static final int NO_WINNER = -1;
    public static int[] status = {NOT_PLAYED, NOT_PLAYED, NOT_PLAYED,
            NOT_PLAYED, NOT_PLAYED, NOT_PLAYED,
            NOT_PLAYED, NOT_PLAYED, NOT_PLAYED
    };
    public static int activePlayer = RED_CODE;
    public  static int winner = -1;
    RelativeLayout playGroundLayout;
    RelativeLayout messageLayout;
    public static int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}
            , {0, 4, 8}, {2, 4, 6}};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playground);

        messageLayout = findViewById(R.id.messageLayout);
        playGroundLayout = findViewById(R.id.play_ground);
        netThread = new NetworkHandlerThread();
        netThread.start();
//        listenForOtherUser = new ListenForOtherUser(playGroundLayout);
//        listenForOtherUser.start();




    }

    public void dropIn(View view) {
        ImageView imageView = (ImageView) view;
        int tag = Integer.parseInt((String) view.getTag());
        if (RankedFragment.userTurn == activePlayer) {
            if (winner != NO_WINNER || status[tag] == NOT_PLAYED) {
                if (activePlayer == YELLOW_CODE) {
                    imageView.setImageResource(R.drawable.yellow);
                    status[tag] = activePlayer;
                    netThread.sendMessage("xoRankPlaying+" + RankedFragment.userTurn + "+" + tag);
                    activePlayer = RED_CODE;
                } else {
                    imageView.setImageResource(R.drawable.red);
                    status[tag] = activePlayer;
                    netThread.sendMessage("xoRankPlaying+" + RankedFragment.userTurn + "+" + tag);
                    activePlayer = YELLOW_CODE;
                }

                imageView.setAlpha(0f);
                imageView.animate().alpha(1f).setDuration(700);
            }
        } else {
            Toast.makeText(this, "not your turn!", Toast.LENGTH_SHORT).show();
        }
        //
        winnerMSG();
        winner = checkWinner();
        if (winner != NO_WINNER || checkFill()) {
            String winnerName = (winner == RED_CODE) ? "red won" :
                    (winner == YELLOW_CODE) ? "yellow won" : "no winner";
            Toast.makeText(this, (winnerName), Toast.LENGTH_SHORT).show();
        }
    }
    public void winnerMSG(){
        winner = checkWinner();
        if(winner != NO_WINNER || checkFill()){
            String message = (winner == NO_WINNER) ? "NO winner " :
                    (winner == RED_CODE) ? "Red won" : "Yellow won";
            ((TextView) messageLayout.findViewById(R.id.winner_message)).setText(message);
            messageLayout.setVisibility(View.VISIBLE);
        }

    }

    ///no winner : -1
    ///red : RED_CODE
    // yellow : YELLOW_CODE
    public int checkWinner() {
        for (int[] positions : winningPositions) {
            if (status[positions[0]] == status[positions[1]] &&
                    status[positions[1]] == status[positions[2]] &&
                    status[positions[0]] != NOT_PLAYED) {
                return status[positions[0]];
            }
        }
        return NO_WINNER;
    }

    public boolean checkFill() {
        for (int i = 0; i < status.length; i++) {
            if (status[i] == NOT_PLAYED)
                return false;
        }
        return true;
    }

    public void reset(View view) {
        activePlayer = RED_CODE;
        Arrays.fill(status, NOT_PLAYED);
        messageLayout.setVisibility(View.GONE);
        LinearLayout playgroundLayout = findViewById(R.id.playground_layout);
        for (int i = 0; i < playgroundLayout.getChildCount(); i++) {
            LinearLayout row = (playgroundLayout.getChildAt(i) instanceof LinearLayout) ? (LinearLayout) playgroundLayout.getChildAt(i) : null;
            if (row == null) return;
            for (int j = 0; j < row.getChildCount(); j++) {
                ImageView iv = (row.getChildAt(j) instanceof ImageView) ? (ImageView) row.getChildAt(j) : null;
                if (iv == null) return;
                iv.setImageResource(0);
            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        final MenuItem resetItem = menu.add("Reset");
        resetItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        resetItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                reset(null);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }


}

//class ListenForOtherUser extends Thread{
//    private RelativeLayout playGround;
//    ListenForOtherUser(RelativeLayout playGround){
//        this.playGround = playGround;
//    }
//    @Override
//    public void run() {
//        super.run();
//        while (true){
//
//            if (TicTocToe.winner != TicTocToe.NO_WINNER || TicTocToe.status[tag] == TicTocToe.NOT_PLAYED){
//                String serverMsg = TicTocToe.netThread.getSMessage();
//                while (serverMsg.equals("")){
//                    serverMsg = TicTocToe.netThread.getSMessage();
//                }
//                String[] xoCommands = serverMsg.split("\\+"); ///0: xoCommand 1:other player turn 2: other player move
//                int otherTurn = Integer.parseInt(xoCommands[1]);
//                if (otherTurn == TicTocToe.RED_CODE){
//                    imageView.setImageResource(R.drawable.yellow);
//                    TicTocToe.status[tag] = TicTocToe.activePlayer;
//                }
//            }
//        }
//    }
//}
