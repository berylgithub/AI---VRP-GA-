/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import java.io.File;
import java.io.IOException;
import vrp_ga.FileLoader;
import vrp_ga.Kromosom;

/**
 *
 * @author Yorozuya
 */
public class TestKromosom {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FileLoader fL=new FileLoader();
        fL.loadNodeInfo("Node_info.txt");
        Kromosom krom1=new Kromosom();
        krom1.setArrNode(fL.getArrNode());
        krom1.setFirstNode();
        krom1.createGen();
        krom1.calTotalDistance();
        krom1.calFitness();
        krom1.testPrint();
        System.out.println(krom1.getTotalDistance());
        System.out.println(krom1.getFitness());
        System.out.println(krom1.getArrNode().size());
        
        Kromosom krom2=new Kromosom();
        krom2.setArrNode(fL.getArrNode());
        krom2.setFirstNode();
        krom2.createGen();
        krom2.calTotalDistance();
        krom2.calFitness();
        krom2.testPrint();
        System.out.println(krom2.getTotalDistance());
        System.out.println(krom2.getFitness());
        System.out.println(krom2.getArrNode().size());
    }
    
}
