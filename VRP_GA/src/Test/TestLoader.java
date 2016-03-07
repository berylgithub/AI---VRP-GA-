/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import vrp_ga.FileLoader;

/**
 *
 * @author Yorozuya
 */
public class TestLoader {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        FileLoader fL=new FileLoader();
        try {
            fL.loadNodeInfo("Node_info.txt");
            fL.testPrint();
        } catch (IOException ex) {
            Logger.getLogger(TestLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
