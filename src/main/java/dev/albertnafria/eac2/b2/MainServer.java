/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.albertnafria.eac2.b2;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class MainServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            ServerSocket server  = new ServerSocket(8888);
            int i=0;
            while(true) {
                System.out.println("Esperant client...");
                Socket socket = server.accept();
                System.out.println("Client connectat"+i);
                i++;
                new ThreadClient(socket).start();
            }
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
}