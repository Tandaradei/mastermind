/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.net.*;
import java.io.*;

/**
 *
 * @author laurin.agostini
 */
public class ClientController{
    Socket s;
    Writer out;
    BufferedReader in;
    String playerName = "";
    
    public ClientController(String playerName){
        this.playerName = playerName;
    }
    
    public boolean connect(String ip, int port) {
        try{
            s = new Socket(ip, port);
            out = new OutputStreamWriter(s.getOutputStream());
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        }catch(IOException ex){
            System.out.println(ex.getMessage());
            return false;
        }
        return s.isConnected();
    }
    
    public String newGame(){
        return sendMessage("NEWGAME " + playerName);
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
    
    public void disconnect() {
        try{
            s.shutdownOutput();
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

}
