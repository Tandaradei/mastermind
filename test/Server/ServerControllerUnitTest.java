/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import Server.ServerController.*;

/**
 *
 * @author laurin.agostini
 */
public class ServerControllerUnitTest {
    ServerController serverController;
    public ServerControllerUnitTest() {
        this.serverController = new ServerController();
    }

    @Test
    public void testCheckKey(){
        assert (this.serverController.checkKey("1234", "3324").equals("BWW"));
        assert (this.serverController.checkKey("", "").equals("0"));
        assert (this.serverController.checkKey("1232", "4444").equals("0"));
        assert (this.serverController.checkKey("2423", "2423").equals("BBBB"));
    }
}
