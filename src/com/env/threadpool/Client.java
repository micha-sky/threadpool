package com.env.threadpool;
import java.util.Random;

/**
 * Created by alex on 5/22/15.
 */
public class Client extends Thread{
    private volatile CommonQueue<Message> tasks;

    public Client(CommonQueue<Message> tasks){
        this.tasks = tasks;
    }


    private void generateMessage(long executionTime){
        Message newMessage = new Message("<New request>", executionTime);
        tasks.push(newMessage);
        System.out.format("New task produced at: %s %s", newMessage.getCreationTime(), System.lineSeparator());
    }
    @Override
    public void run(){
        System.out.println("Starting client thread");
        Random sleepTime = new Random();
        while (!isInterrupted()){
            try{
                sleep(sleepTime.nextInt(500));
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            generateMessage(6000);
        }
        System.out.println("<Client> stopped");
    }
}
