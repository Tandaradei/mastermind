/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import javax.swing.*;
import java.awt.*;
import Base.GUI;
import Menu.ClientPlayMenu;

/**
 *
 * @author laurin.agostini
 */
public class ClientGUI extends JFrame implements GUI{
    ClientPlayMenu clientPlayMenu;
    JComponent currentComponent;
    
    public ClientGUI(int sizeX, int sizeY, ClientController client) {
        super("Client");
        clientPlayMenu = new ClientPlayMenu(this, null, client);
        this.setSize(sizeX, sizeY);
    }
    
    public void init(){
        this.setResizable(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        currentComponent = clientPlayMenu.getContent("");
        this.add(currentComponent);
        
        this.setVisible(true);
    }
    
    public void redraw(){
        this.remove(currentComponent);
        currentComponent = clientPlayMenu.getContent("");
        this.add(currentComponent);
        
        this.revalidate();
    }
    
    public void close(){
        this.setVisible(false);
        this.dispose();
    }
}
