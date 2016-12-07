package com.develiark.car_android;

import java.io.*;
import java.net.Socket;
import java.nio.charset.Charset;

public class ServerSender implements Runnable {
    private OutputStream mStream;
    public String message = "";

    public ServerSender(Socket communicationSocket) throws IOException {
        mStream = new BufferedOutputStream(communicationSocket.getOutputStream());
    }

    @Override
    public void run() {
        byte[] data = message.getBytes(Charset.forName("UTF-8"));

        try {
            mStream.write(data);
            mStream.flush();
        } catch (IOException e) {
           //MainActivity.handler.sendEmptyMessage(SERVER_ERROR);
            e.printStackTrace();
        }
    }
}