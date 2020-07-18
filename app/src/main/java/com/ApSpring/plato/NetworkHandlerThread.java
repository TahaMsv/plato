package com.ApSpring.plato;

import android.util.Log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class NetworkHandlerThread extends Thread {
    private DataOutputStream dos;
    private InputStream is;
    private ObjectInputStream ois;
    Socket socket;
    private String serverMessage = "";
    private static boolean sendMode;
    private static int counter = 0;

    @Override
    public void run() {
        super.run();
        try {
            socket = new Socket("192.168.1.34", 3000);
            dos = new DataOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            sendMode = true;
            while (true) {
                serverMessage = dis.readUTF();
            }
        } catch (IOException e) {
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

    public List getServerList() throws IOException, ClassNotFoundException {
        ArrayList al = (ArrayList) ois.readObject();
        return al;
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
    public void sendMessage2(String message){
        final String finalMessage = message;


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                DataOutputStream dataOutputStream = null;
                try {
                     dataOutputStream = new DataOutputStream(socket.getOutputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    dataOutputStream.writeUTF(finalMessage);
                    dataOutputStream.flush();
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });

    }
}


