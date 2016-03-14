package com.env.checker;

import com.env.threadpool.CommonQueue;
import com.env.threadpool.ServerPool;


public class Checker extends Thread {
    private volatile CommonQueue tasks;
    private volatile ServerPool pool;

    public Checker(CommonQueue tasks, ServerPool pool){
        this.tasks = tasks;
        this.pool = pool;
    }


    public void run(){
        int count = 0;
        while (!isInterrupted()){
            try {
                sleep(2000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

            {

               if (tasks.needMoreThreads()){
                    System.out.println("<Checker> Message is too old, adding a new server");
                    pool.addServer();
                }else if(count >= 10){
                   System.out.println("<Checker> Too many threads, removing one server");
                   pool.removeServer();
                   count = 0;
               }else{
                   count++;
                   System.out.println("<Checker> Counting to ten  " + count);
               }
            }
        }
    }
}
