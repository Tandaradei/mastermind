/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Menu.ServerPlayMenu;
import java.io.BufferedReader;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
/**
 *
 * @author laurin.agostini
 */
public class ServerController implements Runnable {
    String scope = "123456789abcdef";
    int sequenceLength = 5; 

    String playerName;
    String sequence;
    
    Socket socket;
    int port = 50004;
    ServerPlayMenu menu;
    
    BufferedReader in;
     Writer out;
    
    public ServerController(int port){
        this.port = port;
    }
    
    public void run(){
        System.out.println("host() called");
        try{
            System.out.println("Try to host a server");
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server hosted. Waiting for client");
            socket = serverSocket.accept();
            System.out.println("Client connected");
            menu.clientConnected(true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new OutputStreamWriter(socket.getOutputStream());
            while (true) {
                // Eingabestrom lesen
                String line = in.readLine();
                if (line == null){
                    menu.clientConnected(false);
                }

                // Passende Methode aufrufen
                String response = "Not a valid command";
                String input[] = line.split(" ", 2);
                String command = (input.length > 0) ? input[0] : null;
                String argument = (input.length > 1) ? input[1] : null;

                response = (command.equals("NEWGAME")) ? newgame(argument) : response;
                response = (command.equals("CHECK")) ? checkKey(sequence, argument) : response;

                // Ausgabestrom schreiben
                out.write(String.format("%s%n", response));
                out.flush();
            }
        }
        catch(IOException ex){
            System.out.println(ex.getMessage()); 
        }
    }
    
    public String sendMessage(String message){
        String response = "";
        try{
            out.write(String.format("%s%n", message));
            out.flush();
            response = in.readLine();
        }catch(IOException ex){
            System.out.println(ex.getMessage());
            return "";
        }
        return response;
    }
    
    public void setMenu(ServerPlayMenu menu){
        this.menu = menu;
    }
    
    public String getMyAddress() {
        String address = "";
        try(final DatagramSocket socket = new DatagramSocket()) {
            socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
            address = socket.getLocalAddress().getHostAddress();
            socket.close();
        } catch(Exception exp){}
        return address;
    }
    
    public int getPort(){
        return port;
    }
    
    public String newgame(String argument) {
        playerName = argument;
        sequence = "";

        for (int i = 0; i < sequenceLength; i++) {
            sequence += scope.charAt((int) (Math.random() * 15));
        }

        return "SETUP " + sequenceLength + " " + sequence;
    }
    
    
    public String checkKey(String original, String toTest){
        String result = "";
        for(int i = 0; i < original.length(); i++){
            if(original.charAt(i) == toTest.charAt(i)){
                String newMyKey = original.substring(0, i) + 'x' + original.substring(i+1);
                String newToTest = toTest.substring(0, i) + 'x' + toTest.substring(i+1);
                original = newMyKey;
                toTest = newToTest;
                result += 'B';
            }
        }
        for(int i = 0; i < original.length(); i++){
            if(original.charAt(i) != 'x'){
                for(int u = 0; u < toTest.length(); u++){
                    if(original.charAt(i) == toTest.charAt(u)){
                        String newMyKey = original.substring(0, i) + 'x' + original.substring(i+1);
                        String newToTest = toTest.substring(0, u) + 'x' + toTest.substring(u+1);
                        original = newMyKey;
                        toTest = newToTest;
                        result += 'W';
                        break;
                    }
                }
            }
        }
        if(result.length() > 0){
            return result;
        }
        return "0";
    }
    
}
