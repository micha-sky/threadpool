package com.env.threadpool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 5/24/15.
 */
public class ServerPool extends Thread {
    private List<Server> servers = new ArrayList<Server>();
    private volatile CommonQueue tasks;
    private volatile int count = 0;
    private int maxServerCount;
    private int minServerCount = 1;

    public ServerPool(CommonQueue tasks, int count, Integer minServerCount, Integer maxServerCount) {
        this.tasks = tasks;
        this.count = count;
        this.minServerCount = (minServerCount == null) ? 1 : minServerCount;
        this.maxServerCount = (maxServerCount == null) ? Integer.MAX_VALUE : maxServerCount;
    }

    @Override
    public void run() {
        System.out.println("<Server pool> Starting thread pool with " + count + " servers");
       initialize();
    }

    private void initialize() {
        for(int i = 0; i < count; i++){
            addServer();
        }
    }

    public void addServer() {
        if (servers.size() < maxServerCount) {
            System.out.println("<Server pool> Adding a server");
            Server server = new Server(tasks);
            servers.add(server);
            server.start();
        }else{
            System.out.println("<Server pool> Can't add threads more than " + maxServerCount);
        }
    }

    public void removeServer(){
        if (servers.size() > minServerCount) {
            System.out.println("<Server pool> Removing last server");
            Server lastServer = servers.get(servers.size() - 1);
            lastServer.interrupt();
            servers.remove(lastServer);
        }else {
            System.out.println("<Server pool> Can't remove thread. Should be at least " + minServerCount);
        }

    }
}
