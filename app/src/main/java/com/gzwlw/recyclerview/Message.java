package com.gzwlw.recyclerview;

import android.graphics.drawable.Drawable;

public class Message {

    private String userName;
    private String time;
    private Drawable drawable;

    public Message(String userName, String time, Drawable drawable) {
        this.userName = userName;
        this.time = time;
        this.drawable = drawable;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    @Override
    public String toString() {
        return "Message{" +
                "userName='" + userName + '\'' +
                ", time='" + time + '\'' +
                ", drawable=" + drawable +
                '}';
    }
}
