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
 * @author Jon Klinaku
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
        ReadAndWrite RaW=new ReadAndWrite();
        String[][] a = RaW.readAirplaneData("Dataset_LiederStolletz2016.xls", 0);
        int[][][] tables=RaW.readRestrictionData("Dataset_LiederStolletz2016.xls", 0) ;
        for(int i =0;i<a.length;i++){
            for(int j=0;j<a[0].length;j++){
                System.out.print(a[i][j]+" ");
            }
            System.out.println();
        }
          for(int i =0;i<tables[0].length;i++){
            for(int j=0;j<tables[0][0].length;j++){
                System.out.print(tables[1][i][j]+" ");
            }
            System.out.println();
        }
        Constraints.tables=tables;
        AirplaneList tempList= new AirplaneList(a.length, a);
        Airplane[] airplanes2= new Airplane[tempList.size()/2];
        Airplane[] airplanes1= new Airplane[tempList.size()-airplanes2.length];
        for(int i=0;i<tempList.size();i++){
                if(i%2==0){
                   airplanes1[i/2]=tempList.getAirplane(i);
                }
                else{
                   airplanes2[i/2]=tempList.getAirplane(i); 
                }
            }
        
        AirplaneLists best =new AirplaneLists(airplanes1.clone(),airplanes2.clone(),tables);
        
        best.update();
        
//        Airplane[] airplane123 = best.getAirplanes();
//             // FIFO.fifo(airplane);
//                for (int j = 0; j < a.length; j++) {
//                    a[j] = airplane123[j].getAirplane();
//                  }
//        
//        new ReadAndWrite().writeFile(a, "Joni.xls", wb);
//            
//        
//        
//        System.out.println(best.fitness());
        
        boolean processing=true;
        
        while(processing){
            AirplaneLists currentSolution= new AirplaneLists(airplanes1.clone(),airplanes2.clone(),tables);
            
            double temp = 10000;
        double coolingRate = 0.003;
        while(temp > 1)
        {
            AirplaneLists newSolution = new AirplaneLists(currentSolution.airplanes1.clone(),currentSolution.airplanes2.clone(),tables);
            int Pos1 = (int) (newSolution.size()/2*Math.random());
            int Pos2 = (int) (Pos1+(Math.random()*9-4));
            if(Pos2<0)
                Pos2=0;
            if(Pos2>=newSolution.size()/2)
                Pos2=newSolution.size()/2-1;
            if(Pos1==Pos2)
                continue;
            boolean airplaneLane1;
            if(Math.random()<0.5)
                airplaneLane1=true;
            else
                airplaneLane1=false;
            boolean airplaneLane2;
            if(Math.random()<0.5)
                airplaneLane2=true;
            else
                airplaneLane2=false;
            airplaneLane1=true;
            airplaneLane2=true;
            Airplane Airplaneswap1 = newSolution.getAirplane(Pos1,airplaneLane1);
            Airplane Airplaneswap2 = newSolution.getAirplane(Pos2,airplaneLane2);
            
            
            newSolution.setAirplane(Pos2, Airplaneswap1,airplaneLane2);
            newSolution.setAirplane(Pos1, Airplaneswap2,airplaneLane1);
            
            newSolution.update();
            int neighbourEnergy = newSolution.fitness();
            currentSolution.update();
            int currentEnergy = currentSolution.fitness();
            best.update();
            int fitness =best.fitness();
            
            
            
//              Airplane[] airplane123 = currentSolution.getAirplanes();
//             // FIFO.fifo(airplane);
//                for (int j = 0; j < a.length; j++) {
//                    a[j] = airplane123[j].getAirplane();
//                  }
//        
//        new ReadAndWrite().writeFile(a, "Joni.xls", wb);
//            
//        
//        
//        System.out.println(currentSolution.fitness());
            
//            for(int k=0;k<best.size();k++){
//                
//                
//                System.out.println(newSolution.getAirplane(k).getTime()+" "+newSolution.getAirplane(k).getOTime()+" "+k+" "+currentSolution.getAirplane(k).getTime()+" "+currentSolution.getAirplane(k).getOTime());
//            }
            
            
//            System.out.println(currentEnergy-neighbourEnergy);
            
            
            if (acceptanceProbability(currentEnergy, neighbourEnergy, temp) > Math.random()) {
                
                currentSolution = new AirplaneLists(newSolution.airplanes1.clone(),newSolution.airplanes2.clone(),tables);
                currentSolution.update();
            }
                if (currentSolution.fitness() < fitness) {
                    
                System.out.println(fitness);
                processing=true;
                best = new AirplaneLists(currentSolution.airplanes1.clone(),currentSolution.airplanes2.clone(),tables);
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
