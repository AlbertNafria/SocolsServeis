/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.albertnafria.eac2.b2;

/**
 *
 * @author Albert Nafría
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MainClient {
    public static void main(String[] args) {
        String host = "127.0.0.1";
        int port = 8888;
        PrintWriter out;
        try {
            //IMPLEMENTA
            Socket socket = new Socket(host, port);
            //Llegeix del servidor
            Scanner in = new Scanner(socket.getInputStream());
            //escriu al servidor
            out = new PrintWriter(socket.getOutputStream(), true);
            //llegeix del teclat
            Scanner scanner = new Scanner(System.in);
            System.out.println("Escriu la paraula a traduïr");

            while (in.hasNext()) {
                String msg = in.nextLine();
                String msg_partit[] = msg.split("//");
                for (int i=0; i<msg_partit.length; i++) {
                    System.out.println(msg_partit[i]);
                }
                System.out.println("Escriu la paraula a traduïr");
                if (!msg.contains("CLOSE:")) {
                    out.println(scanner.nextLine());
                } else {
                    break;
                }
            }
            in.close();
            out.close();
        } catch (UnknownHostException ex) {
            Logger.getLogger(MainClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
}