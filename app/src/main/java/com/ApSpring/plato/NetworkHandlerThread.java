

package com.ApSpring.plato;

import android.util.Log;

import com.ApSpring.plato.friends.ExampleFriend;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class NetworkHandlerThread extends Thread {
    private DataOutputStream dos;
    private DataInputStream dis;
    Socket socket;
    private String serverMessage = "";
    private ArrayList<String> friendList = new ArrayList<>();

    private static boolean sendMode;
    private static int counter = 0;

    public NetworkHandlerThread() {}

    @Override
    public void run() {
        super.run();
        try {
            socket = new Socket("192.168.1.5", 3000);
            dos = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());
            sendMode = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String getSMessage(){
        Thread receiverMessage = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
//                    dis = new DataInputStream(socket.getInputStream());
                    serverMessage = dis.readUTF();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        receiverMessage.start();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(serverMessage+" getSMessage");
        return serverMessage;
    }





    public ArrayList<String> getServerList() {
        return friendList;
    }


    public void sendMessage(String message) {
        final String finalMessage = message;

        Thread senderThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
//                    dos = new DataOutputStream(socket.getOutputStream());
                    dos.writeUTF(finalMessage);
                    dos.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println(finalMessage+" sendMessage");
            }
        });
        senderThread.start();

    }

}

