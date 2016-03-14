package com.env.threadpool;


import com.env.checker.Checker;
import com.env.sockets.Listener;
import com.env.sockets.Receiver;

/**
 * Created by alex on 5/23/15.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Starting application");

        CommonQueue<Message> tasks = new CommonQueue<Message>();
        Thread client = new Client(tasks);
        //client.start();
        Thread pool = new ServerPool(tasks, 2, null, null);
        pool.start();
        Thread checker = new Checker(tasks, (ServerPool) pool);
        checker.start();

        Thread listener = new Listener(tasks, 5125);
        listener.start();
    }
}
