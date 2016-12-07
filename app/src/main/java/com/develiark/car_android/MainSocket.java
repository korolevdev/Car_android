package com.develiark.car_android;

import java.io.IOException;
import java.net.Socket;


public class MainSocket implements Runnable {
    public Thread senderThread;
    public ServerSender sender;

    Socket socket;

    @Override
    public void run() {
        try {
            System.out.println("1222");
            socket = new Socket(MainActivity.HOST, MainActivity.PORT);
            System.out.println("1211");
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