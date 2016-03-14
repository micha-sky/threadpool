package com.env.classloader;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.File;
import java.util.Map;
import java.util.jar.JarFile;

/**
 * Created by alex on 7/5/15.
 */
public class Loader extends ClassLoader {

    private String pathToFile;

    public Loader( ClassLoader parent) {
        super(parent);

    }


    public Class<?> findClass(String className) throws ClassNotFoundException {
        byte[] bytes = getClassFromFile(pathToFile + className + ".class");
        return defineClass(className, bytes, 0, bytes.length);
    }

    public  Class<?> getClassFromBytes(byte[] byteStream){
        return defineClass("com.env.threadpool.task.Task", byteStream, 0, byteStream.length);
    }

    public static void loadJar(String path){
        JarFile file = null;
        try {
            file = new JarFile(path);
            Map entries = file.getManifest().getMainAttributes();
            entries.containsValue("");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static byte[] getClassFromFile(String path) {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        long length = new File(path).length();
        byte[] bytes = new byte[(int) length];

        int offset = 0;
        int numRead = 0;
        try {
            while (offset < bytes.length
                    && (numRead = inputStream.read(bytes, offset, bytes.length - offset)) >= 0) {
                offset += numRead;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }
}