/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import java.io.IOException;
import vrp_ga.FileLoader;
import vrp_ga.GADriver;
import vrp_ga.Populasi;

/**
 *
 * @author Yorozuya
 */
public class TestGeneration {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FileLoader fL=new FileLoader();
        fL.loadNodeInfo("Node_info.txt");
        Populasi pop=new Populasi(0.8, 0.2, 50);
        pop.setArrNode(fL.getArrNode());
        pop.createPopulation();
        GADriver gaDrive=new GADriver();
        gaDrive.setPopulation(pop);
        gaDrive.setUrlSaveFile("Best Route Per Generation.txt");
        gaDrive.evolve(100);
    }
    
}
