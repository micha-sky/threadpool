package com.env.threadpool;

import java.io.Serializable;
import java.util.Date;


public class Message implements Serializable {
    private long executionTime;
    private String text;

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(long executionTime) {
        this.executionTime = executionTime;
    }

    private Date creationTime;

    public Message(String text, long executionTime){
        this.executionTime = executionTime;
        this.text = text;
        this.creationTime = new Date();
    }

    @Override
    public String toString(){
        return String.format("%s, %s", text, executionTime);
    }

}

