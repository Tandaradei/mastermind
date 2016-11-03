/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Base;

import javax.swing.*;
import java.awt.*;
import Menu.MainMenu;

/**
 *
 * @author laurin.agostini
 */
public class MainGUI extends JFrame implements GUI{
    MainMenu mainMenu;
    JComponent currentComponent;
    
    public MainGUI(int sizeX, int sizeY) {
        super("Mastemind");
        mainMenu = new MainMenu(this, null);
        this.setSize(sizeX, sizeY);
    }
    
    public void init(){
        this.setResizable(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        currentComponent = mainMenu.getContent("");
        this.add(currentComponent);
        
        this.setVisible(true);
    }
    
    public void redraw(){
        this.remove(currentComponent);
        currentComponent = mainMenu.getContent("");
        this.add(currentComponent);
        
        this.revalidate();
    }
    
    public void close(){
        this.setVisible(false);
        this.dispose();
    }
    
    
}
