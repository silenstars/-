package com.example.demo.entity;

public class Message {
    private String message_id;
    private String addname;
    private String data;

    // Getters and Setters
    public String getMessage_id() {
        return message_id;
    }

    public void setMessage_id(String message_id) {
        this.message_id = message_id;
    }

    public String getAddname() {
        return addname;
    }

    public void setAddname(String addname) {
        this.addname = addname;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Message{" +
                "message_id='" + message_id + '\'' +
                ", addname='" + addname + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
