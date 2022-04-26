/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.albertnafria.eac1.b2;

/**
 *
 * @author tomas
 */
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;

public class clientMC1 {
    
	public static void main(String[] args) throws IOException {

		ServidorMC1 client = new ServidorMC1();

		// Creem el socket multicast
		int port = 12345;
		MulticastSocket socket = new MulticastSocket(port);
		InetAddress grup = InetAddress.getByName("225.0.0.1");
		// El client que s'uneix al grup, amb el mètode de la classe MulticastSocket: joinGroup que permet
		// al socket multicast unir-se al grup de multicast
		try {
			socket.joinGroup(grup);

			System.out.println("Client " + client.getNumClient() + " afegit a la llista de difusió");
			String sortida = new String();
			// el client visualiza els paquets que reb del servidor i finalitza quan reb un *
			while (!sortida.trim().equals("*")) {
				byte[] receivedData = new byte[1024];
				DatagramPacket packet = new DatagramPacket(receivedData, receivedData.length);
				// Rep el paquet del servidor multicast
				socket.receive(packet);
				byte[] dades = new byte[packet.getLength()];
				System.arraycopy(packet.getData(), 0, dades, 0, packet.getLength());
				sortida = new String(dades);
				System.out.println(sortida);
			}
			// El client abandona el grup
			socket.leaveGroup(grup);
			// Tanquem el socket
			socket.close();
		} catch (SocketException e) {
			System.out.println(e.getMessage());
		}
	}
}