/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.albertnafria.eac2.b2;

/**
 * @author Albert Nafría
 */

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 *
 */
public class ThreadClient extends Thread {
    private Socket client;
    private Scanner in;
    private PrintWriter out;


    public ThreadClient(Socket client) {
        try {
            this.client = client;
            this.in = new Scanner(client.getInputStream());
            this.out = new PrintWriter(client.getOutputStream(), true);
        } catch (IOException ex) {
            Logger.getLogger(ThreadClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        String msg;
        SoftCatalaClient soft = new SoftCatalaClient();
        String traduccio;

        try {
            out.println("Conectando al Server. Finalice con EXIT!!!");
            while (in.hasNext()) {
                msg = in.nextLine();
                traduccio = soft.translate(msg);
                System.out.println("Missatge rebut: " + traduccio);

                if (msg.equalsIgnoreCase("exit")) {
                    out.println("CLOSE");
                    break;
                } else {
                    out.println("Traducció: " + traduccio);
                }
            }
            in.close();
            out.close();
            client.close();
        } catch (IOException ex) {
            Logger.getLogger(ThreadClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}