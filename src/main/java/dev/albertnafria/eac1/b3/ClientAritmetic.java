/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.albertnafria.eac1.b3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 *
 * @author 
 */
public class ClientAritmetic {

    public static void main(String[] args) {
        String host = "localhost";
        int port = 9999;
        String operacio = "/:121:11";
        String resultat[]=operacio.split(":");
        
        System.out.println("L'operació demanada és una "+resultat[0]+", més concretament "+resultat[1]+resultat[0]+resultat[2]);
                
            
                
        try {

            Socket socket = new Socket(host, port);
            //Envia la operació
            try (BufferedWriter escriptor = new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream()))) {
                escriptor.write(operacio);
                escriptor.newLine();
                escriptor.flush();
                // Obté el resultat del servidor i l'imprimeix
                try (BufferedReader lector = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()))) {
                    
                    System.out.println("El resultat és: "+lector.readLine());
                }
            }
            socket.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
