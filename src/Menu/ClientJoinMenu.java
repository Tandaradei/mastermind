/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menu;


import Client.*;
import Base.GUI;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import java.util.regex.Pattern;
import java.io.IOException;

/**
 *
 * @author laurin.agostini
 */
public class ClientJoinMenu extends Menu{
    
    static private final String IPV4_REGEX = "(([0-1]?[0-9]{1,2}\\.)|(2[0-4][0-9]\\.)|(25[0-5]\\.)){3}(([0-1]?[0-9]{1,2})|(2[0-4][0-9])|(25[0-5]))";
    static private Pattern IPV4_PATTERN = Pattern.compile(IPV4_REGEX);
    
    public ClientJoinMenu(GUI gui, Menu last){
        super(gui, last);
        name = "Join";
        me = this;
    }
    
    @Override
    public JComponent draw(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        c.fill = GridBagConstraints.VERTICAL;
        c.gridx = 0;
        c.gridy = 0;
        
        // IP adress
        JLabel ipLabel = new JLabel("IP");
        c.gridx = 0;
        c.gridy = 1;
        panel.add(ipLabel, c);
        
        JTextField ipText = new JTextField("192.168.2.102", 15);
        c.gridx = 0;
        c.gridy = 2;
        panel.add(ipText, c);
        
        // Port
        JLabel portLabel = new JLabel("Port");
        c.gridx = 1;
        c.gridy = 1;
        panel.add(portLabel, c);

        JTextField portText = new JTextField("50004", 6);
        c.gridx = 1;
        c.gridy = 2;
        panel.add(portText, c);
        
        
        
        JCheckBox external = new JCheckBox();
        external.setText("Start in new window");
        c.gridx = 0;
        c.gridy = 3;
        panel.add(external, c);

        JLabel errorText = new JLabel();
        errorText.setForeground(Color.red);
        c.gridx = 0;
        c.gridy = 4;
        panel.add(errorText, c);
        //Join button
        JButton joinButton = new JButton("Join");
        joinButton.setBackground(java.awt.Color.GRAY);
        joinButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                if(isValidIPV4(ipText.getText())){
                    errorText.setText("");
                    ClientController client = new ClientController("Client04");
                    int port = Integer.parseInt(portText.getText());   
                    if(client.connect(ipText.getText(), port)){
                        if(external.isSelected()){
                            ClientGUI clientGUI = new ClientGUI(800, 600, client);
                            clientGUI.init();
                        }
                        else{
                            next = new ClientPlayMenu(gui, me, client);
                            gui.redraw();
                        }
                    }
                    else{
                        errorText.setText("Can't connect to server");
                    }
                    
                }
                else{
                    errorText.setText("No valid IP");
                    System.out.println("No valid IP");
                }
            }
        });
        c.gridx = 1;
        c.gridy = 3;
        panel.add(joinButton, c);
        
        
        return panel;
        
    }
    
    private boolean isValidIPV4(final String s) {          
        return IPV4_PATTERN.matcher(s).matches();
    }
}
