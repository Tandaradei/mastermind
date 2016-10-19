/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menu;

import Base.BaseGUI;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author laurin.agostini
 */
public class ServerHostMenu extends Menu{
    String ipAdress = "127.0.0.1";
    String port = "50004";
    
    public ServerHostMenu(BaseGUI gui, Menu last){
        super(gui, last);
        name = "Host";
        me = this;
    }
    
    @Override
    public JComponent draw(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        
        // Back button if we can go back
        if(last != null){
            JButton backButton = new JButton("Back");
            backButton.setBackground(java.awt.Color.GRAY);
            backButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae){
                    last.removeNext();
                }
            });

            panel.add(backButton, c);
        }
        
        // IP adress
        JLabel ipLabel = new JLabel("Your IP: " + ipAdress);
        c.gridx = 0;
        c.gridy = 1;
        panel.add(ipLabel, c);
        
        // Port
        JLabel portLabel = new JLabel("Your Port: " + port);
        c.gridx = 0;
        c.gridy = 2;
        panel.add(portLabel, c);
        
        //Join button
        JButton hostButton = new JButton("Host");
        hostButton.setBackground(java.awt.Color.GRAY);
        hostButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                System.out.println("Server hosted at " + ipAdress + ":" + port);
            }
        });
        c.gridx = 0;
        c.gridy = 3;
        panel.add(hostButton, c);
        
        
        return panel;
        
    }
}
