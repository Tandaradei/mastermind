/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menu;

import Base.GUI;
import Server.*;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author laurin.agostini
 */
public class ServerHostMenu extends Menu{
    public ServerHostMenu(GUI gui, Menu last){
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
        
        // Port
        JLabel portLabel = new JLabel("Port");
        c.gridx = 1;
        c.gridy = 0;
        panel.add(portLabel, c);

        JTextField portText = new JTextField("50004", 6);
        c.gridx = 1;
        c.gridy = 1;
        panel.add(portText, c);
        
        JCheckBox external = new JCheckBox();
        external.setText("Start in new window");
        c.gridx = 0;
        c.gridy = 3;
        panel.add(external, c);
        
        //Join button
        JButton hostButton = new JButton("Host");
        hostButton.setBackground(java.awt.Color.GRAY);
        hostButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){ 
                int port = Integer.parseInt(portText.getText());
                ServerController server = new ServerController(port);
                if(external.isSelected()){
                    ServerGUI serverGUI = new ServerGUI(800, 600, server);
                    serverGUI.init();
                    System.out.println("New server window started");
                }
                else{
                    me.next = new ServerPlayMenu(gui, me, server);
                    gui.redraw();
                }
            }
        });
        c.gridx = 1;
        c.gridy = 3;
        panel.add(hostButton, c);
        
        
        return panel;
        
    }
}
