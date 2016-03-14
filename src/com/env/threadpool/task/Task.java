package com.env.threadpool.task;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.File;
import java.util.Map;

public class Task {
    public static void main(String[] args) {
        System.out.println("<Invoked the task>");
    }

    public static void execute(){
        System.out.println("<Invoked the task>");
    }
}
