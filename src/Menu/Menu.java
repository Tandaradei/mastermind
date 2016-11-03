/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menu;

import Base.GUI;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
/**
 *
 * @author laurin.agostini
 */
public abstract class Menu {
    String name = "<not defined>";
    Menu last = null;
    Menu next = null;
    Menu me = null;
    GUI gui;
    
    public Menu(GUI gui, Menu last){
        this.gui = gui;
        this.last = last;
        me = this;
    }
    
    public JComponent getContent(String path){
        if(next != null){
            return next.getContent(path + name + "/");
        }
        else{
            JPanel panel = new JPanel();
            panel.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            
            c.fill = GridBagConstraints.VERTICAL;
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
            else{
                JButton exitButton = new JButton("Exit");
                exitButton.setBackground(java.awt.Color.GRAY);
                exitButton.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent ae){
                        onExit();
                    }
                });

                panel.add(exitButton, c);
            }
            
            JLabel label = new JLabel(path + name);
            c.gridx = 1;
            c.gridy = 0;
            panel.add(label, c);
            
            c.gridx = 0;
            c.gridy = 1;
            c.gridwidth = 2;
            panel.add(this.draw(), c);
            
            return panel;
        }
    }
    
    public abstract JComponent draw();
    
    public String getName(){
        return name;
    }
    
    public void removeNext(){
        next = null;
        gui.redraw();
    }
    
    private void onExit(){
        gui.close();
    }
}
