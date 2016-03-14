package com.env.sockets;

import com.env.threadpool.CommonQueue;
import java.io.IOException;
import java.net.ServerSocket;

/**
 * @author Alex Michalsky michalsky.alex@gmail.com
 */
public class Listener extends Thread {
    private int portNumber;
    private volatile CommonQueue tasks;

    public Listener(CommonQueue tasks, int portNumber){
        this.portNumber = portNumber;
        this.tasks = tasks;
    }

    public void run(){
        try{
            ServerSocket serverSocket = new ServerSocket(portNumber);
            while (!isInterrupted()){
                new Receiver(tasks, serverSocket.accept()).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
