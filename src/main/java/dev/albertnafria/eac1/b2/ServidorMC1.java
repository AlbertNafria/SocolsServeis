/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.albertnafria.eac1.b2;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class ServidorMC1 {
    
     private static int numClient=0;

    public ServidorMC1() {
        this.numClient +=1;
    }

    public int getNumClient() {
        return numClient;
    }

    public void setNumClient(int numClient) {
        this.numClient = numClient;
    }

	public static void main(String[] args) throws IOException {
		
		/* En aquesta classe Construïm un servidor multicast que llegeix dades per teclat 
                    i els envia a tots els clients que pertanyen al grup multicast, 
                    el procés acabarà quan s’introdueixi un *
		 */

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		// Es crea el socket multicast
		MulticastSocket ms = new MulticastSocket();
		
		// Se crea el puerto multicast
		
		int Port = 12345;
		
		InetAddress grup = InetAddress.getByName("225.0.0.1");
		
		// Creem el grup multicast
		String cadena="";
		          
		while(!cadena.trim().equals("*")){
			System.out.println("Anem a enviar dades al grup:");
                        
			cadena = in.readLine();
			
			// Construïm un datagrama para enviar al grup
			
			DatagramPacket paquet = new DatagramPacket(cadena.getBytes(), cadena.length(), grup, Port);
			
			ms.send(paquet);
		
		}
		
		// Es tanca el socket
		
		ms.close();
	
	
	}

}