/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Base;

import java.awt.event.*;
/**
 *
 * @author laurin.agostini
 */
public class BaseController {
    BaseGUI gui;
    public BaseController(){
        gui = new BaseGUI(this, 800, 600);
        gui.init();
    }
    
}
