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
    
    public BaseGUI(int sizeX, int sizeY) {  
        frame.setSize(sizeX, sizeY);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
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
