package com.develiark.car_android;

import java.io.IOException;
import java.net.Socket;


public class MainSocket implements Runnable {
    public static final String HOST = MainActivity.ip;
    public static final int PORT = Integer.valueOf(MainActivity.port);
    public Thread senderThread;
    public ServerSender sender;

    Socket socket;

    @Override
    public void run() {
        try {
            socket = new Socket(HOST, PORT);
        } catch (IOException e) {
           // MainActivity.handler.sendEmptyMessage(SERVER_ERROR);
            e.printStackTrace();
        }
    }

    public void sendMessage(String msg) {
        try {
            sender = new ServerSender(socket);
            sender.message = msg;
            senderThread = new Thread(sender);
            senderThread.start();
            senderThread.join();
        } catch (InterruptedException | IOException e) {
           // MainActivity.handler.sendEmptyMessage(SERVER_ERROR);
            e.printStackTrace();
        }
    }

}