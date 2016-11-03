/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menu;

import Client.ClientController;
import Base.GUI;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author laurin.agostini
 */
public class ClientPlayMenu extends Menu{
    
    ClientController client;
    
    public ClientPlayMenu(GUI gui, Menu last, ClientController client){
        super(gui, last);
        name = "ClientPlay";
        me = this;
        this.client = client;
    }
    
    @Override
    public JComponent draw(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.VERTICAL;
        c.gridx = 0;
        c.gridy = 0;
        
        JLabel errorText = new JLabel();
        errorText.setForeground(Color.green);
        errorText.setText("Connected to server");
        panel.add(errorText, c);
        
        JLabel responseText = new JLabel();
        responseText.setForeground(Color.black);
        responseText.setText("");
        c.gridx = 0;
        c.gridy = 2;
        panel.add(responseText, c);
        
        JButton newGame = new JButton("NEWGAME");
        newGame.setBackground(java.awt.Color.GRAY);
        newGame.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                responseText.setText(client.newGame());
            }
        });
        c.gridx = 0;
        c.gridy = 1;
        panel.add(newGame, c);
        
        
        return panel;
    }
}
