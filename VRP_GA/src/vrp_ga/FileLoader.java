/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vrp_ga;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Yorozuya
 */
public class FileLoader {
    ArrayList<Node> arrNode=new ArrayList();

    public FileLoader() {
    }
    
    public void loadNodeInfo(String url) throws FileNotFoundException, IOException{
        BufferedReader br = new BufferedReader(new java.io.FileReader(url));
        String line;
        String[] splitLine;
        ArrayList<Node> tempArrNode=new ArrayList<>();
        while ((line = br.readLine()) != null) {
            splitLine=line.split(" ");
            Node tempNode = new Node();
            tempNode.setIndex(splitLine[0]);
            tempNode.setX(Double.valueOf(splitLine[1]));
            tempNode.setY(Double.valueOf(splitLine[2]));
            tempNode.setDemand(Double.valueOf(splitLine[3]));
            tempArrNode.add(tempNode);
        }
        arrNode=tempArrNode;
    }
    
    public void testPrint(){
        for(int i=0; i<arrNode.size(); i++){
            System.out.println(arrNode.get(i).getIndex()+" "+arrNode.get(i).getX()+" "+arrNode.get(i).getY()+" "+arrNode.get(i).getDemand());
        }
    }
}
