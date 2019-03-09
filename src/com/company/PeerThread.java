package com.company;

import javax.json.Json;
import javax.json.JsonObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class PeerThread  extends Thread {

    private BufferedReader read ;


    public PeerThread(Socket sock) throws IOException {
        read = new BufferedReader(new InputStreamReader(sock.getInputStream()));
    }
    public void run()
    {
        boolean flag = true ;
        while(flag)
        {
            try {
                JsonObject MyJSON = Json.createReader(read).readObject();
                if (MyJSON.containsKey("username")) {
                    System.out.println("[" + MyJSON.getString("username") + "]: " + MyJSON.getString("message"));
                }
            }catch (Exception e){}
        }

    }
}
