package com.company;

import javax.json.Json;
import java.io.*;
import java.net.*;


public class Peer {

    public static void main(String[] args) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter username && port for this peer");
        String arr[] = read.readLine().split(" ");
        ServerThread myServerThread = new ServerThread(arr[1]);
        myServerThread.start();
        new Peer().UpdateListenToPeers(read, arr[0], myServerThread);
    }

    public void UpdateListenToPeers(BufferedReader BF, String UserName, ServerThread server) throws IOException {
        System.out.println("> Enter (Seprated) HostName:Port#");
        System.out.println("Peers To recieve Messages From (S To Skip)");
        String Input = BF.readLine();
        String[] NewValues = Input.split(" ");
        if (!Input.equals("S")) {
            for (int i = 0; i < NewValues.length; i++) {
                String[] address = NewValues[i].split(":");
                Socket sock = null;
                try {
                    sock = new Socket(address[0], Integer.parseInt(address[1]));
                    new PeerThread(sock).start();
                }
                catch (Exception e)
                {

                }
                if (sock != null) sock.close();
                else System.out.println("Invalid Formated Input (Skip And Retry)");
            }
            Communicate(BF, server, UserName);
        }

    }

    public void Communicate(BufferedReader reader, ServerThread serverThread, String UserName) throws IOException {
        System.out.println("You Can Communicate Now (E to Exit , C to Change)");
        boolean flag = true;
        while (flag) {
            String Msg = reader.readLine();         // Read msg from peer
            if (Msg.equals("E")) {
                flag = false;
                continue;
            } else if (Msg.equals("C")) {
                UpdateListenToPeers(reader, UserName, serverThread);
            } else {
                StringWriter writer = new StringWriter();
                Json.createWriter(writer).writeObject(
                        Json.createObjectBuilder().add("username", UserName).add("message", Msg).build()
                );
                serverThread.sendMessage(writer.toString());
            }

        }
        System.exit(0);
    }
}