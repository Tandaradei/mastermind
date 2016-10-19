/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menu;

import Base.BaseGUI;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
    BaseGUI gui;
    
    public Menu(BaseGUI gui, Menu last){
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
            
            
            JLabel label = new JLabel(path + name);
            
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy = 0;
            panel.add(label, c);

            c.gridy = 1;
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
}
