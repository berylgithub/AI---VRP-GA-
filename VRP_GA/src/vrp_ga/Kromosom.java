/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vrp_ga;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

/**
 *
 * @author Yorozuya
 */
public class Kromosom {
    ArrayList<Node> arrNode;
    double totalDistance, fitness, currentDemand, rechargeCount;
    Node firstNode;
    
    public Kromosom() {
        arrNode=new ArrayList<>();
        currentDemand=100;
        rechargeCount=0;
        totalDistance=0;
    }

    public Kromosom(Node firstNode) {
        this.firstNode = firstNode;
        currentDemand=100;
        rechargeCount=0;
        totalDistance=0;
    }

    
    public ArrayList<Node> getArrNode() {
        return arrNode;
    }

    public void setArrNode(ArrayList<Node> arrNode) {
        this.arrNode = arrNode;
    }

    public double getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(double totalDistance) {
        this.totalDistance = totalDistance;
    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public double getCurrentDemand() {
        return currentDemand;
    }

    public void setCurrentDemand(double currentDemand) {
        this.currentDemand = currentDemand;
    }
    
    //main methods
    
    //must be run
    public void setFirstNode(){
        this.firstNode=this.arrNode.get(0);
        this.arrNode.remove(0);
//        ArrayList tempArrNode=new ArrayList();
//        for(int i=1; i<arrNode.size(); i++){
//            tempArrNode.add(arrNode.get(i));
//        }
        //WTF IS WRONG WITH ARRAYLIST.REMOVE() METHOD????
//        for(Iterator<Node> iterator = arrNode.iterator(); iterator.hasNext();){
//            if(firstNode.equals(iterator.next())){
//                iterator.remove();
//            }
//        }
//        this.arrNode=tempArrNode;
    }
    
    public void calFitness(){
        this.fitness=1/(totalDistance+0.00001);
    }
    
    public double calDistNode(Node aNode, Node bNode){
        double tempDistance=0;
        tempDistance=Math.sqrt(Math.pow(aNode.getX()-bNode.getX(), 2)+Math.pow(aNode.getY()-bNode.getY(), 2));
        return tempDistance;
    }
    
    public boolean deductDemand(double demand){
        boolean recharge=false;
        if(currentDemand-demand>0){
            currentDemand=currentDemand-demand;
            recharge=false;
        }
        else if(currentDemand-demand<=0){
            double tempDemand=Math.sqrt(Math.pow(currentDemand-demand, 2));
            currentDemand=0;
            rechargeDemand();
            currentDemand=currentDemand-tempDemand;
            recharge=true;
        }
        return recharge;
    }
    
    public void rechargeDemand(){
        currentDemand=100;
        rechargeCountInc();
    }
    
    public void rechargeCountInc(){
        rechargeCount++;
    }
    
    public void calTotalDistance(){
        double tempDist=0, tempTotalDist=0;
        for(int i=0; i<arrNode.size(); i++){
            if((i+1)>=arrNode.size()){
                break;
            }
            else{
                boolean tempBool=false;
                tempDist=calDistNode(arrNode.get(i), arrNode.get(i+1));
                tempBool=deductDemand(arrNode.get(i).getDemand());
                if(tempBool==true){
                    tempTotalDist=tempTotalDist+(2*calDistNode(firstNode, arrNode.get(i)));
                }
                tempTotalDist=tempTotalDist+tempDist;
            }
        }
        tempTotalDist=tempTotalDist+calDistNode(firstNode, arrNode.get(0))+calDistNode(firstNode, arrNode.get(arrNode.size()-1));
        totalDistance=tempTotalDist;
    }
    
    public void createGen(){
        Collections.shuffle(arrNode);
    }
    
    public void testPrint(){
        for(int i=0; i<arrNode.size(); i++){
             System.out.println(arrNode.get(i).getIndex()+" "+arrNode.get(i).getX()+" "+arrNode.get(i).getY()+" "+arrNode.get(i).getDemand());
        }
    }
    
}
