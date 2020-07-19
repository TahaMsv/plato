package com.ApSpring.plato;

import android.util.Log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;

public class NetworkHandlerThread extends Thread {
    private DataOutputStream dos;
    private DataInputStream dis;
    private InputStream is;
    private ObjectInputStream ois;
    Socket socket;
    private String serverMessage = "";
    private ArrayList<String> friendList = new ArrayList<>();
    private static boolean sendMode;
    private static int counter = 0;

    @Override
    public void run() {
        super.run();
        try {
            socket = new Socket("192.168.1.4", 3000);
            dos = new DataOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
            dis = new DataInputStream(socket.getInputStream());
            sendMode = true;
            while (true) {
                serverMessage = dis.readUTF();
                if (ois.readObject() instanceof ArrayList)
                    friendList = (ArrayList<String>) ois.readObject();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getServerMessage() {

        if (!this.serverMessage.equals("")) {

            return this.serverMessage;
        } else {

            return "";
        }
    }

    public ArrayList<String> getServerList() {
        return friendList;
    }

    public void sendModeMessage(String mode) {
        final String finalMessage = mode;
        if (sendMode) {
            Thread senderThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        dos.writeUTF(finalMessage);
                        sendMode = false;
                        counter++;
                        Log.i("mode : ", "" + counter);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            senderThread.start();
        }
    }

    public void sendMessage(String message) {
        final String finalMessage = message;

        Thread senderThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dos.writeUTF(finalMessage);
                    dos.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        senderThread.start();

    }
    public void finish(){
        try {
            dis.close();

            dos.close();
            ois.close();
            socket.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

}


