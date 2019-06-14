package com.zyc.imitationwechat.socket;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class ClientSocket {

    public static final String TAG = ClientSocket.class.getSimpleName();

    private Socket socket;
    private BufferedReader br = null;
    private static ClientSocket instance;
    public static ClientSocket getInstance () {
        if (instance == null) {
            synchronized (ClientSocket.class) {
                if (instance == null) {
                    instance = new ClientSocket();
                }
            }
        }
        return instance;
    }

    private void init () {
        try {
            socket = new Socket("192.168.10.35", 10000);
            //设置10秒之后即认为是超时
            socket.setSoTimeout(10000);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void read () {
        final String[] line = {null};
        new Thread(){
            @Override
            public void run() {
                super.run();
                init();
                while (socket != null && socket.isConnected()) {
                    try {
                        br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        Log.i(TAG, "run: " +  br.readLine());

                        write("Client Message!");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

    }


    public void write (String message) {
        try {
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(message.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void close () {
        try {
            if ( br!= null )br.close();
            if (socket != null ) socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
