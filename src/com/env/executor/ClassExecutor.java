package com.env.executor;


import java.io.IOException;
import java.io.OutputStream;

public class ClassExecutor {
    public static void runtimeExecute(String path){
        try {
            Process process = Runtime.getRuntime().exec("java -jar " + path);
            OutputStream stream = process.getOutputStream();
            System.out.print(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void processBuilderExecute(String path){
        ProcessBuilder processBuilder = new ProcessBuilder("java", "-jar", path);
        try {
            processBuilder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
