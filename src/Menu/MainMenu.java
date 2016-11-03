/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menu;

import Base.BaseGUI;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 *
 * @author laurin.agostini
 */
public class MainMenu extends Menu{
    
    ImageIcon playButtonIcon = new ImageIcon("images/play.png");
    ImageIcon hostButtonIcon = new ImageIcon("images/host.png");
    
    public MainMenu(BaseGUI gui, Menu last){
        super(gui, last);
        name = "Main";
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
        
        playButtonIcon.setImage(playButtonIcon.getImage().getScaledInstance(32,32, java.awt.Image.SCALE_SMOOTH));
        
        JButton playButton = new JButton("Join a server", playButtonIcon);
        playButton.setBackground(java.awt.Color.GRAY);
        playButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                next = new ClientJoinMenu(gui, me);
                gui.redraw();
            }
        });
        panel.add(playButton, c);
        
        hostButtonIcon.setImage(hostButtonIcon.getImage().getScaledInstance(32,32, java.awt.Image.SCALE_SMOOTH));
        
        JButton hostButton = new JButton("Host a server", hostButtonIcon);
        hostButton.setBackground(java.awt.Color.GRAY);
        hostButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                next = new ServerHostMenu(gui, me);
                gui.redraw();
            }
        });
        c.gridx = 0;
        c.gridy = 1;
        panel.add(hostButton, c);
        
        return panel;
    }
    
}
