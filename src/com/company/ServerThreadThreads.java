package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThreadThreads extends Thread{
    private ServerThread serverThread;
    private Socket socket;
    private PrintWriter printWriter;

    public void run()
    {
        try {
            BufferedReader BF = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.printWriter = new PrintWriter(socket.getOutputStream(),true);
            while (true)serverThread.sendMessage(BF.readLine());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public PrintWriter getPrintWriter() {
        return printWriter;
    }


    public ServerThreadThreads(Socket socket , ServerThread serverThread)
    {
        this.serverThread = serverThread;
        this.socket = socket;
    }
}
