/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vrp_ga;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Yorozuya
 */
public class Populasi {
    ArrayList<Node> arrNode = new ArrayList<>();
    ArrayList<Kromosom> arrKrom = new ArrayList<>();
    double probCross, probMutate;
    int totalPopulation, curGeneration;

    public Populasi() {
    }

    public Populasi(double probCross, double probMutate, int totalPopulation) {
        this.probCross = probCross;
        this.probMutate = probMutate;
        this.totalPopulation = totalPopulation;
    }

    public Populasi(double probCross, double probMutate, int totalPopulation, int generation) {
        this.probCross = probCross;
        this.probMutate = probMutate;
        this.totalPopulation = totalPopulation;
        this.curGeneration = generation;
    }
    
    public ArrayList<Kromosom> getArrKrom() {
        return arrKrom;
    }

    public void setArrKrom(ArrayList<Kromosom> arrKrom) {
        this.arrKrom = arrKrom;
    }

    public ArrayList<Node> getArrNode() {
        return arrNode;
    }

    public void setArrNode(ArrayList<Node> arrNode) {
        this.arrNode = arrNode;
    }
    
    public double getProbCross() {
        return probCross;
    }

    public void setProbCross(double probCross) {
        this.probCross = probCross;
    }

    public double getProbMutate() {
        return probMutate;
    }

    public void setProbMutate(double probMutate) {
        this.probMutate = probMutate;
    }

    public int getGeneration() {
        return curGeneration;
    }

    public void setGeneration(int generation) {
        this.curGeneration = generation;
    }

    
    public int getTotalPopulation() {
        return totalPopulation;
    }

    public void setTotalPopulation(int totalPopulation) {
        this.totalPopulation = totalPopulation;
    }
    
    public void setPopulation(ArrayList<Kromosom> arrKrom){
        this.arrKrom=arrKrom;
    }
    
    public void createPopulation(){
        for(int i=0; i<totalPopulation; i++){
            Kromosom krom=new Kromosom();
            arrKrom.add(krom);
        }
        for(int i=0; i<arrKrom.size(); i++){
            arrKrom.get(i).setArrNode(arrNode);
            arrKrom.get(i).setFirstNode();
            arrKrom.get(i).createGen();
            arrKrom.get(i).calTotalDistance();
            arrKrom.get(i).calFitness();
        }
    }
    
    //method buat crossover antar kromosom A dan kromosom B
    public Kromosom crossOverKromosom(Kromosom aKrom, Kromosom bKrom){
        Kromosom tempKrom=new Kromosom();
        int lowerSection=aKrom.getArrNode().size()/2;
        int upperSection=bKrom.getArrNode().size();
        for(int i=0; i<lowerSection; i++){
            tempKrom.arrNode.add(aKrom.getArrNode().get(i));
            
        }
        for(int i=lowerSection; i<upperSection; i++){
            tempKrom.arrNode.add(bKrom.getArrNode().get(i));
        }
        tempKrom.calTotalDistance();
        tempKrom.calFitness();
        return tempKrom;
    }
    
    //method buat mutasi dalam 1 kromosom
    public Kromosom mutateKromosom(Kromosom krom){
        Kromosom tempKrom = krom, mutatedKrom = new Kromosom();
        Node tempNodeA=null, tempNodeB=null;
        Random rand = new Random();
        int randomNumA = rand.nextInt(((30) - 0) + 1) + 0;
        int randomNumB = rand.nextInt(((30) - 0) + 1) + 0;
        tempNodeA=tempKrom.getArrNode().get(randomNumA);
        tempNodeB=tempKrom.getArrNode().get(randomNumB);
        tempKrom.getArrNode().set(randomNumA, tempNodeB);
        tempKrom.getArrNode().set(randomNumB, tempNodeA);
        tempKrom.calTotalDistance();
        tempKrom.calFitness();
        if(tempKrom.getFitness()>krom.getFitness()){
            mutatedKrom=tempKrom;
        }
        else if(tempKrom.getFitness()<=krom.getFitness()){
            mutatedKrom=krom;
        }
        return mutatedKrom;
    }
    
    public void crossOverAll(){
        ArrayList<Kromosom> tempArrKrom=new ArrayList<>();
        int currentMax=arrKrom.size();
        for(int i=0; i<currentMax; i++){
            for(int j=0; j<currentMax; j++){
                Random rand = new Random();
                double randDouble=rand.nextDouble();
                if(randDouble<probCross){
                    if(!arrKrom.get(i).equals(arrKrom.get(j))){
                        Kromosom tempKrom=crossOverKromosom(arrKrom.get(i), arrKrom.get(j));
                        tempArrKrom.add(tempKrom);
                    }
                }
            }
        }
        arrKrom.addAll(tempArrKrom);
    }
    
    public void mutateAll(){
        ArrayList<Kromosom> tempArrKrom=new ArrayList<>();
        int currentMax=arrKrom.size();
        for(int i=0; i<currentMax; i++){
            Random rand = new Random();
            double randDouble=rand.nextDouble();
            if(randDouble<probMutate){
                Kromosom tempKrom = mutateKromosom(arrKrom.get(i));
                if(!tempKrom.equals(arrKrom.get(i))){
                    tempArrKrom.add(tempKrom);
                }
            }
        }
        arrKrom.addAll(tempArrKrom);
    }
    
    public void testPrint(){
        System.out.println("==Population gen-0 Kromosoms==");
        for(int i=0; i<arrKrom.size(); i++){
            for(int j=0; j<arrKrom.get(i).getArrNode().size(); j++){
                System.out.print(arrKrom.get(i).getArrNode().get(j).getIndex()+"-");
            }
            System.out.println("\tTotalDistance : "+arrKrom.get(i).getTotalDistance()+"\tFitness : "+arrKrom.get(i).getFitness());
        }
    }
}
