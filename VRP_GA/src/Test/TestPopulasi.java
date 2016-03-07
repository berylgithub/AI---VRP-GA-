/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import vrp_ga.*;


/**
 *
 * @author Yorozuya
 */
public class TestPopulasi {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        try {
            FileLoader fL=new FileLoader();
            fL.loadNodeInfo("Node_info.txt");
            Populasi pop=new Populasi(0.8, 0.2, 50, 0);
            pop.setArrNode(fL.getArrNode());
            pop.createPopulation();
            System.out.println(pop.getArrKrom().size());
            pop.testPrint();
            //pop.testPrint();
        } catch (IOException ex) {
            Logger.getLogger(TestPopulasi.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
