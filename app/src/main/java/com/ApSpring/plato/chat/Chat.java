package com.ApSpring.plato.chat;

public class Chat {
    private String message;
    private String receiver;
    private String sender;
    String time;

    public Chat(String message, String receiver, String sender,String time) {
        this.message = message;
        this.receiver = receiver;
        this.sender = sender;
        this.time=time;
    }
    public Chat(){}

    public String getMessage() {
        return message;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getSender() {
        return sender;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }
}
