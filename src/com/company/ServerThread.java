package com.company;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashSet;
import java.util.Set;

public class ServerThread  extends Thread{
    public ServerSocket getServer() {
        return server;
    }

    public Set<ServerThreadThreads> getMyServerThread() {
        return myServerThread;
    }

    public ServerThread(String port) throws IOException {
        server = new ServerSocket(Integer.parseInt(port));

    }
    public void sendMessage(String msg){
        myServerThread.forEach(t->t.getPrintWriter().println(msg));

    }
    public void run()
    {
        while (true)
        {
            try {
                ServerThreadThreads sv = new ServerThreadThreads(server.accept(),this);
                myServerThread.add(sv);
                sv.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private ServerSocket server;
    private Set<ServerThreadThreads>myServerThread = new HashSet<>();

}
