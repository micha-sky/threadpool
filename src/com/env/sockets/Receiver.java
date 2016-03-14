package com.env.sockets;

import com.env.threadpool.CommonQueue;
import com.env.threadpool.Message;

import java.io.*;
import java.net.Socket;


public class Receiver extends Thread {
    private Socket socket;
    private volatile CommonQueue tasks;


    public Receiver(CommonQueue tasks, Socket socket) {
        this.socket = socket;
        this.tasks = tasks;
    }

    public void run() {
        try {
            System.out.println("Connected to " + socket.getInetAddress().getHostName());
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

            Message message = (Message) input.readObject();
            if (message != null) {
                tasks.push(message);
            }

            try {
                input.close();
                output.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (EOFException eof) {
            System.out.println("Client disconnected");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
