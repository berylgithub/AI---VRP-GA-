/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vrp_ga;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author Yorozuya
 */
public class GADriver {
    ArrayList<Kromosom> arrKrom;
    String urlSaveFile;
    Populasi population;
    
    public GADriver() {
        arrKrom=new ArrayList<>();
        population=new Populasi();
    }

    public ArrayList<Kromosom> getArrKrom() {
        return arrKrom;
    }

    public void setArrKrom(ArrayList<Kromosom> arrKrom) {
        this.arrKrom.clear();
        this.arrKrom.addAll(arrKrom);
    }

    public Populasi getPopulation() {
        return population;
    }

    public void setPopulation(Populasi population) {
        this.population = population;
    }

    public String getUrlSaveFile() {
        return urlSaveFile;
    }

    public void setUrlSaveFile(String urlSaveFile) {
        this.urlSaveFile = urlSaveFile;
    }
    
    public void evolve(int n) throws FileNotFoundException{
        PrintWriter printer = new PrintWriter(urlSaveFile);
        for(int i=0; i<n; i++){
//            System.out.println("====Generasi ke-"+i+"====");
            printer.println("====Generasi ke-"+i+"====");
//            population.testPrint();
            population.crossOverAll();
            population.mutateAll();
            population.selectBestKroms(50);
//            System.out.println("====Hasil Crossover, Mutasi & Seleksi best Kromosom====");
//            population.testPrintWBestKrom();
            for(int j=0; j<this.population.bestKromosom.getArrNode().size(); j++){
                printer.print(this.population.bestKromosom.getArrNode().get(j).getIndex()+"-");
            }
            printer.println("\tTotalDistance : "+this.population.bestKromosom.getTotalDistance()+"\tFitness : "+this.population.bestKromosom.getFitness());
            printer.println();
//            System.out.println("");
        }
        printer.close();
    }
    

    
}
