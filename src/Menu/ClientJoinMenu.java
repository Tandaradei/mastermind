/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menu;


import Base.BaseGUI;
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
import java.util.regex.Pattern;

/**
 *
 * @author laurin.agostini
 */
public class ClientJoinMenu extends Menu{
    
    static private final String IPV4_REGEX = "(([0-1]?[0-9]{1,2}\\.)|(2[0-4][0-9]\\.)|(25[0-5]\\.)){3}(([0-1]?[0-9]{1,2})|(2[0-4][0-9])|(25[0-5]))";
    static private Pattern IPV4_PATTERN = Pattern.compile(IPV4_REGEX);
    
    public ClientJoinMenu(BaseGUI gui, Menu last){
        super(gui, last);
        name = "Join";
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
        JLabel ipLabel = new JLabel("IP-adress");
        c.gridx = 0;
        c.gridy = 1;
        panel.add(ipLabel, c);
        
        JTextField ipText = new JTextField(15);
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
        
        JLabel errorText = new JLabel();
        errorText.setForeground(Color.red);
        c.gridx = 0;
        c.gridy = 3;
        panel.add(errorText, c);
        
        //Join button
        JButton joinButton = new JButton("Join");
        joinButton.setBackground(java.awt.Color.GRAY);
        joinButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                if(isValidIPV4(ipText.getText())){
                    errorText.setText("");
                    System.out.println("Join server at " + ipText.getText() + ":" + portText.getText());
                }
                else{
                    errorText.setText("No valid IP-adress");
                    System.out.println("No valid IP-adress");
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
