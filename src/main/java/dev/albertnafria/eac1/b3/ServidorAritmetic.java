package dev.albertnafria.eac1.b3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Albert Nafría
 */
public class ServidorAritmetic {

    protected ServeiAritmetic servei;
    protected Socket socket;

    public void setServei(ServeiAritmetic servei) {
        this.servei = servei;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void executa() {
        BufferedReader inputStream;
        BufferedWriter outputStream;
        try {
            //Llegeix el missatge del client i l'analitza
            inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String missatge = inputStream.readLine();
            double resultat = analitza(missatge);

            //Escriu el resultat cap al client
            outputStream = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            outputStream.write("" + resultat);
            outputStream.newLine();
            outputStream.flush();

            //Tanca el flux
            outputStream.close();

        } catch (IOException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    //El protocol per a la operació és operador:valor1:valor2
    private double analitza(String operacio) throws IllegalArgumentException {
        //this.servei = new ServeiAritmeticImpl();
        //this.setServei(new ServeiAritmeticImpl());
        double resultat = 0.0;
        // Dividim els elements de la operació en cel·les d'un array
        String[] elements = operacio.split(":");
        String operador = elements[0];
        double valor1 = Double.parseDouble(elements[1]);
        double valor2 = Double.parseDouble(elements[2]);
        switch (operador) {
            case "+":
                resultat = servei.suma(valor1, valor2); break;
            case "-":
                resultat = servei.resta(valor1, valor2); break;
            case "*":
                resultat = servei.mult(valor1, valor2); break;
            case "/":
                resultat = servei.div(valor1, valor2); break;
        }
        return resultat;
    }

    
    public static void main(String[] args) throws Exception {
        
        ServidorAritmetic servidorAritmetic = new ServidorAritmetic();
        int port = 9999;
        System.out.println("El servidor aritmètic està executant...");
        try {
            servidorAritmetic.setServei(new ServeiAritmeticImpl());
            System.out.println("Esperant clients...");

            // Crea el socket servidor i espera la connexió del client
            ServerSocket serverSocket = new ServerSocket(port);
            servidorAritmetic.setSocket(serverSocket.accept());
            System.out.println("Client rebut");
            servidorAritmetic.executa();

            serverSocket.close();
            System.out.println("El servidor matemàtic s'ha tancat...");


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
