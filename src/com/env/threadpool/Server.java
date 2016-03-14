package com.env.threadpool;

import com.env.classloader.Loader;
import com.env.executor.ClassExecutor;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class Server extends Thread {
    private volatile CommonQueue<Message> tasks;

    public Server(CommonQueue<Message> tasks) {
        this.tasks = tasks;
    }

    public void processTasks() {

        while (!isInterrupted()) {
            try {

                System.out.println("<Server> Waiting for a task");
                synchronized (tasks) {
                    tasks.wait();
                }

                Message message = tasks.pull();
                while (message != null) {
                    System.out.format("%s Received task: %s, created at %s %s",
                            Thread.currentThread().getName(), message.getText(),
                            message.getCreationTime(), System.lineSeparator());

                    
                   sleep(message.getExecutionTime());
                    message =  tasks.pull();
                }
            } catch (InterruptedException e) {
                System.out.println("<Server> Shutting down");
            }
        }
    }

    @Deprecated
    private void readTask(byte[] stream){
        Loader loader = new Loader(ClassLoader.getSystemClassLoader());
        Class task = loader.getClassFromBytes(stream);
        try {
            Method main = task.getMethod("main", String[].class);
            ClassExecutor.processBuilderExecute("//home/alex/Dropbox/javaPractice/threadsPool/out/production/threadsPool/ThreadPool.jar");
           ClassExecutor.runtimeExecute("/home/alex/Dropbox/javaPractice/threadsPool/out/production/threadsPool/ThreadPool.jar");
            //Loader.loadJar("/home/alex/Dropbox/javaPractice/threadsPool/out/production/threadsPool/ThreadPool.jar");
            main.invoke(null, (Object)new String[] {});
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }  catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        processTasks();
    }

}

