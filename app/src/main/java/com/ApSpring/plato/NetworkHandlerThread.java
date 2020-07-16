package com.ApSpring.plato;

import android.util.Log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class NetworkHandlerThread extends Thread {
    private DataOutputStream dos;
    private String serverMessage = "";
    @Override
    public void run() {
        super.run();
        try {
            Socket socket = new  Socket("192.168.1.34", 3000);
            dos = new DataOutputStream(socket.getOutputStream());
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            serverMessage = dis.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getServerMessage(){
        return serverMessage;
    }

    public void sendMessage(String message){
        final String finalMessage = message;

        Thread senderThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dos.writeUTF(finalMessage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        senderThread.start();

    }
}
