/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vrp_ga;

import java.util.ArrayList;
import java.util.Collections;
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
    Kromosom bestKromosom;

    public Populasi() {
    }

    public Populasi(double probCross, double probMutate, int totalPopulation) {
        this.probCross = probCross;
        this.probMutate = probMutate;
        this.totalPopulation = totalPopulation;
        bestKromosom=new Kromosom();
    }

    public Populasi(double probCross, double probMutate, int totalPopulation, int generation) {
        this.probCross = probCross;
        this.probMutate = probMutate;
        this.totalPopulation = totalPopulation;
        this.curGeneration = generation;
        bestKromosom=new Kromosom();
    }
    
    public ArrayList<Kromosom> getArrKrom() {
        return arrKrom;
    }

    public void setArrKrom(ArrayList<Kromosom> arrKrom) {
        this.arrKrom.addAll(arrKrom);
    }

    public ArrayList<Node> getArrNode() {
        return arrNode;
    }

    public void setArrNode(ArrayList<Node> arrNode) {
        this.arrNode.addAll(arrNode);
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
        this.arrKrom.addAll(arrKrom);
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
        Kromosom tempKrom = new Kromosom(), mutatedKrom = new Kromosom();
        tempKrom=krom;
        Node tempNodeA=null, tempNodeB=null;
        Random rand = new Random();
        int randomNumA = rand.nextInt(((30) - 0) + 1) + 0;
        int randomNumB = rand.nextInt(((30) - 0) + 1) + 0;
        tempNodeA=krom.getArrNode().get(randomNumA);
        tempNodeB=krom.getArrNode().get(randomNumB);
        tempKrom.getArrNode().set(randomNumA, tempNodeB);
        tempKrom.getArrNode().set(randomNumB, tempNodeA);
        tempKrom.calTotalDistance();
        tempKrom.calFitness();
        mutatedKrom=tempKrom;
        return mutatedKrom;
    }
    
    public void crossOverAll(){
        ArrayList<Kromosom> tempArrKrom=new ArrayList<>();
        int currentMax=arrKrom.size();
        for(int i=0; i<currentMax; i++){
            Kromosom tempKrom=new Kromosom();
            Random rand = new Random();
            double randDouble=rand.nextDouble();
            
            if(randDouble<probCross){
                int randomInt = rand.nextInt(((30) - 0) + 1) + 0;
//                System.out.println(arrKrom.get(i).getArrNode().get(0).getIndex()+" "+arrKrom.get(randomInt).getArrNode().get(0).getIndex());
                tempKrom=crossOverKromosom((Kromosom)arrKrom.get(i), (Kromosom)arrKrom.get(randomInt));
                if(tempKrom.getFitness()!=arrKrom.get(i).getFitness()||tempKrom.getFitness()!=arrKrom.get(randomInt).getFitness()){
                    tempArrKrom.add(tempKrom);
                }
                
            }
//            for(int j=0; j<currentMax; j++){
//                Random rand = new Random();
//                double randDouble=rand.nextDouble();
//                if(randDouble<probCross){
//                    Kromosom tempKrom=new Kromosom(), tempKromA=new Kromosom(), tempKromB=new Kromosom();
//                    tempKromA=arrKrom.get(i);
//                    tempKromB=arrKrom.get(j);
//                    System.out.println(tempKromA.getFirstNode().getIndex()+" "+tempKromB.getFirstNode().getIndex());
//                    tempKrom=crossOverKromosom((Kromosom)tempKromA, (Kromosom)tempKromB);
//                    tempArrKrom.add((Kromosom)tempKrom);
//                }
//            }
//            System.out.println(i);
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
                if(tempKrom.getFitness()!=arrKrom.get(i).getFitness()){
                    arrKrom.add(tempKrom);
                }
                
            }
        }
    }
    
    public void selectBestKroms(int startMinIndex){
        ArrayList<Double> arrDouble=new ArrayList<>();
        ArrayList<Kromosom> arrTempKrom=new ArrayList<>();
        for(int i=0; i<arrKrom.size(); i++){
            arrDouble.add(arrKrom.get(i).getFitness());
        }
        Collections.sort(arrDouble, Collections.reverseOrder());
        for(int i=0; i<startMinIndex; i++){
            for(int j=0; j<arrKrom.size(); j++){
                if(arrKrom.get(j).getFitness()==arrDouble.get(i)){
                    arrTempKrom.add(arrKrom.get(j));
                    if(i==0){
                        bestKromosom=arrKrom.get(j);
                    }
                }
            }
        }
        arrKrom.clear();
        arrKrom.addAll(arrTempKrom);
    }
    public void testPrint(){
        for(int i=0; i<arrKrom.size(); i++){
            System.out.print(i+" ");
            for(int j=0; j<arrKrom.get(i).getArrNode().size(); j++){
                System.out.print(arrKrom.get(i).getArrNode().get(j).getIndex()+"-");
            }
            System.out.println("\tTotalDistance : "+arrKrom.get(i).getTotalDistance()+"\tFitness : "+arrKrom.get(i).getFitness());
        }
    }
    
    public void testPrintWBestKrom(){
        for(int i=0; i<arrKrom.size(); i++){
            System.out.print(i+" ");
            for(int j=0; j<arrKrom.get(i).getArrNode().size(); j++){
                System.out.print(arrKrom.get(i).getArrNode().get(j).getIndex()+"-");
            }
            System.out.println("\tTotalDistance : "+arrKrom.get(i).getTotalDistance()+"\tFitness : "+arrKrom.get(i).getFitness());
        }
        System.out.println("Best Kromosom/Route : ");
        for(int i=0; i<bestKromosom.getArrNode().size(); i++){
            System.out.print(bestKromosom.getArrNode().get(i).getIndex()+"-");
        }
        System.out.println("\tTotalDistance : "+bestKromosom.getTotalDistance()+"\tFitness : "+bestKromosom.getFitness());
        System.out.println();
    }
}
