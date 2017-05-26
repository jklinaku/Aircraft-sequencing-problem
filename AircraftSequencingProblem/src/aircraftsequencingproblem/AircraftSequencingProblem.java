/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aircraftsequencingproblem;

import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author Sead Mejzini
 */
public class AircraftSequencingProblem {
    private static double acceptanceProbability(int energy, int newEnergy, double temperature)
    {
        if (newEnergy < energy) {
            return 1.0;
        }
        return Math.exp((energy - newEnergy)/temperature);
    }


    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    
    public static void main(String[] args) throws IOException {
//        double temp = 10000;
//        double coolingRate = 0.003;
        
        
        
        Workbook wb = new HSSFWorkbook();
        //for (int k = 0; k < 12; k++) {
        String[][] a = new ReadAndWrite().readFile("ara.xls", 7);
        
        
        AirplaneList best= new AirplaneList(a.length, a);
        boolean processing=true;
        while(processing){
            AirplaneList currentSolution= new AirplaneList(a.length, a);
            
            double temp = 10000;
        double coolingRate = 0.003;
        while(temp > 1)
        {
            AirplaneList newSolution = new AirplaneList(currentSolution.getAirplanes());
            int Pos1 = (int) (newSolution.size()*Math.random());
            int Pos2 = (int) (newSolution.size()*Math.random());
            
            Airplane Airplaneswap1 = newSolution.getAirplane(Pos1);
            Airplane Airplaneswap2 = newSolution.getAirplane(Pos2);
            
            
            newSolution.setAirplane(Pos2, Airplaneswap1);
            newSolution.setAirplane(Pos1, Airplaneswap2);
            
            newSolution.update();
            int neighbourEnergy = newSolution.fitness();
            currentSolution.update();
            int currentEnergy = currentSolution.fitness();
            best.update();
            int fitness =best.fitness();
//            for(int k=0;k<best.size();k++){
//                
//                
//                System.out.println(newSolution.getAirplane(k).getTime()+" "+newSolution.getAirplane(k).getOTime()+" "+k+" "+currentSolution.getAirplane(k).getTime()+" "+currentSolution.getAirplane(k).getOTime());
//            }
            
            
//            System.out.println(currentEnergy-neighbourEnergy);
            
            
            if (acceptanceProbability(currentEnergy, neighbourEnergy, temp) > Math.random()) {
                
                currentSolution = new AirplaneList(newSolution.getAirplanes());
                currentSolution.update();
            }
                if (currentSolution.fitness() < fitness) {
                System.out.println("best");
                processing=true;
                best = new AirplaneList(currentSolution.getAirplanes());
                Airplane[] airplane = best.getAirplanes();
             // FIFO.fifo(airplane);
                for (int j = 0; j < a.length; j++) {
                    a[j] = airplane[j].getAirplane();
                  }
        
        new ReadAndWrite().writeFile(a, "Joni.xls", wb);
            }
        
            
            temp *= 1 - coolingRate;
        }
        }
        
        
        
        
        
//        Population p = new Population(airplane);
//        int[] d = p.fragments();
//        p.populationCreator(d);
//        Subset[] s = p.getSubset();
//        for (int i = 0; i < s.length; i++) {
//            System.out.println("Subset: " + i);
//            s[i].printSubset();
//        }
        
    }
}
