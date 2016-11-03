/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menu;

import Server.ServerController;
import Base.GUI;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;
import javax.swing.*;

/**
 *
 * @author laurin.agostini
 */
public class ServerPlayMenu extends Menu{
    
    ServerController server;
    JLabel connectionText;
    boolean connected = false;
    Thread serverThread;
    
    public ServerPlayMenu(GUI gui, Menu last, ServerController server){
        super(gui, last);
        name = "ServerPlay";
        me = this;
        this.server = server;
        this.server.setMenu(this);
        serverThread = new Thread(server);
        serverThread.start();
        
    }
    
    @Override
    public JComponent draw(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.VERTICAL;
        c.gridx = 0;
        c.gridy = 0;
        
        connectionText = new JLabel();
        connectionText.setForeground(connected? Color.green : Color.orange);
        if(connected){
            connectionText.setText("Client connected");
        }
        else{
            connectionText.setText("Waiting for client at " + server.getMyAddress() + ":" + server.getPort());
        }
        panel.add(connectionText, c);
        
        JLabel responseText = new JLabel();
        responseText.setForeground(Color.black);
        responseText.setText("");
        c.gridx = 0;
        c.gridy = 2;
        panel.add(responseText, c);
        
        
        return panel;
    }
    
    public void clientConnected(boolean connected){
        this.connected = connected;
        gui.redraw();
    }
    
    private void onExit(){
        server.sendMessage(null);
        gui.close();
    }
}
