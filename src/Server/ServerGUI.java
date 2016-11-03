/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import javax.swing.*;
import java.awt.*;
import Base.GUI;
import Menu.ServerPlayMenu;

/**
 *
 * @author laurin.agostini
 */
public class ServerGUI extends JFrame implements GUI{
    ServerPlayMenu serverPlayMenu;
    JComponent currentComponent;
    
    public ServerGUI(int sizeX, int sizeY, ServerController server) {
        super("Server");
        serverPlayMenu = new ServerPlayMenu(this, null, server);
        this.setSize(sizeX, sizeY);
    }
    
    public void init(){
        this.setResizable(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        currentComponent = serverPlayMenu.getContent("");
        this.add(currentComponent);
        
        this.setVisible(true);
    }
    
    public void redraw(){
        this.remove(currentComponent);
        currentComponent = serverPlayMenu.getContent("");
        this.add(currentComponent);
        
        this.revalidate();
    }
    
    public void close(){
        this.setVisible(false);
        this.dispose();
    }
}
