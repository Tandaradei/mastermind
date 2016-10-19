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
public class BaseGUI extends JFrame{
    MainMenu mainMenu;
    BaseController controller;
    JComponent currentComponent;
    
    
    public BaseGUI(BaseController baseController, int sizeX, int sizeY) {
        super("Mastemind");
        controller = baseController;
        this.setSize(sizeX, sizeY);
        mainMenu = new MainMenu(this, null);
    }
    
    public void init(){
        this.setResizable(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        currentComponent = mainMenu.getContent("");
        this.add(currentComponent);
        
        this.setVisible(true);
    }
    
    public void draw(){
        
    }
    
    public void redraw(){
        this.remove(currentComponent);
        currentComponent = mainMenu.getContent("");
        this.add(currentComponent);
        
        this.revalidate();
    }
    
    
}
