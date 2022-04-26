package dev.albertnafria.eac1.b1;


import java.net.*;
import java.io.*;

public class JavaLookup {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //command line
        if (args.length > 0) {
            for (String arg : args) {
                System.out.println(lookup(arg));
            }
        } else {
            BufferedReader inputStream = new BufferedReader(new InputStreamReader(System.in));
            try {
                while (true) {
                    System.out.println("Entrar un hostname o una adreça IP -> Enter \"exit\" per sortir");
                    String host = inputStream.readLine();
                    if (host.equalsIgnoreCase("exit") || host.equalsIgnoreCase("quit")) {
                        break;
                    }
                    System.out.println(lookup(host));
                }
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    } //final del main
    
    private static String lookup(String host) {
        InetAddress node;
        //obtenir els bytes de les adreçes
        try {
            node = InetAddress.getByName(host);
            if (isHostname(host)) { //ens passa de host a ip
                return node.getHostAddress();
            } else { //ens passa de ip a host.
                return node.getHostName();
            }
        } catch (UnknownHostException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


    private static boolean isHostname(String host) {
        //Per adreces ipv6
        if(host.indexOf(":") != -1)
            return false;

        char[] ca = host.toCharArray();
         // si veiem un caracter que no és ni un dígit ni un punt
        // llavors host és probablement és un hostname
        
        for(int i=0; i<ca.length; i++){
            if(!Character.isDigit(ca[i])){
                if(ca[i] != '.')
                    return true;
            }
        }
       
        return false;
    }
    
}
        
       
        
    
