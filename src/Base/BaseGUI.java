/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Base;

import javax.swing.*;

/**
 *
 * @author laurin.agostini
 */
public class BaseGUI {
    JFrame frame = new JFrame("Mastermind");
    JPanel playPanel = new JPanel();
    JPanel optionsPanel = new JPanel();
    JPanel exitPanel = new JPanel();
    BaseController controller;
    ImageIcon playButtonIcon = new ImageIcon("images/play.png");
    ImageIcon hostButtonIcon = new ImageIcon("images/host.png");
    
    public BaseGUI(BaseController baseController, int sizeX, int sizeY) {
        controller = baseController;
        frame.setSize(sizeX, sizeY);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        playButtonIcon.setImage(playButtonIcon.getImage().getScaledInstance(32,32, java.awt.Image.SCALE_DEFAULT));
        
        JButton playButton = new JButton("Join a server", playButtonIcon);
        playButton.setBackground(java.awt.Color.GRAY);
        //TODO: move to BaseController
        playButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                System.out.println("Client!");
            }
        });
        playPanel.add(playButton);
        
        
        hostButtonIcon.setImage(hostButtonIcon.getImage().getScaledInstance(32,32, java.awt.Image.SCALE_DEFAULT));
        
        JButton hostButton = new JButton("Host a server", hostButtonIcon);
        hostButton.setBackground(java.awt.Color.GRAY);
        //TODO: move to BaseController
        hostButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                System.out.println("Server!");
            }
        });
        playPanel.add(hostButton);
        
        JTabbedPane tabPane = new JTabbedPane(JTabbedPane.TOP,JTabbedPane.SCROLL_TAB_LAYOUT );
        tabPane.add("Play", playPanel);
        tabPane.add("Options", optionsPanel);
        tabPane.add("Exit", exitPanel);
        
        frame.add(tabPane);
    
    }
    
    public void show(){
        frame.setVisible(true);
    }
    
    
}
