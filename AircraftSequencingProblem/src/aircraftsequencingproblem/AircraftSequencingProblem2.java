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
 * @author Jon
 */
public class AircraftSequencingProblem2 {

    private static double acceptanceProbability(int energy, int newEnergy, double temperature) {
        if (newEnergy < energy) {
            return 1.0;
        }
        return Math.exp((energy - newEnergy) / temperature);
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
        ReadAndWrite RaW = new ReadAndWrite();
        String[][] a = RaW.readAirplaneData("Dataset_LiederStolletz2016.xls", 0);
        int[][][] tables = RaW.readRestrictionData("Dataset_LiederStolletz2016.xls", 0);
//        for (int i = 0; i < a.length; i++) {
//            for (int j = 0; j < a[0].length; j++) {
//                System.out.print(a[i][j] + " ");
//            }
//            System.out.println();
//        }
//        for (int i = 0; i < tables[0].length; i++) {
//            for (int j = 0; j < tables[0][0].length; j++) {
//                System.out.print(tables[1][i][j] + " ");
//            }
//            System.out.println();
//        }
        Constraints.tables = tables;
        AirplaneList tempList = new AirplaneList(a.length, a);
        
//        Airplane[] airplanes2= new Airplane[tempList.size()/2];
//        Airplane[] airplanes1= new Airplane[tempList.size()-airplanes2.length];
//        for(int i=0;i<tempList.size();i++){
//                if(i%2==0){
//                   airplanes1[i/2]=tempList.getAirplane(i);
//                }
//                else{
//                   airplanes2[i/2]=tempList.getAirplane(i); 
//                }
//            }
//        
//        AirplaneLists best =new AirplaneLists(airplanes1.clone(),airplanes2.clone());

//        best.update();
//        for (int i = 0; i < best.size(); i++) {
//            boolean temp4;
//            if (i % 2 == 1) {
//                temp4 = true;
//            } else {
//                temp4 = false;
//            }
//            System.out.println(best.getAirplane(i / 2, !temp4).getAirplane()[0]);
//        }
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

        boolean processing = true;
String[][] b=a;
        // while (processing) {
        
//            int jump=4;
//            double temp = 40;
//            double coolingRate = 0.00001;
AirplaneLists best = new AirplaneLists(b.length, b.clone());
        int[] solutionsFitness = new int[10];
        int[] jump = {2, 3, 4};
        double[] temp = {1000, 40, 100, 1000, 10000};
        double[] coolingRate = {0.0001, 0.001, 0.0001, 0.00001,0.000001};
        for (int i = 0; i < jump.length; i++) {
            for (int cool = 0; cool < coolingRate.length; cool++) {
                for (int k = 0; k < temp.length; k++) {
                    
                    for (int l = 0; l < 10; l++) {
                        // best = new AirplaneLists(b.length, b.clone());
                   // best.update();
                        AirplaneLists currentSolution = new AirplaneLists(best.getAirplanesMatrix());
                        while (temp[k] > 1) {
                            AirplaneLists newSolution = new AirplaneLists(currentSolution.getAirplanesMatrix());
                            int Pos1 = (int) (newSolution.size() / 2 * Math.random());
                            int Pos2 = (int) (Pos1 + (Math.random() * (jump[i] * 2 + 1) - jump[i]));
                            if (Pos2 < 0) {
                                Pos2 = 0;
                            }
                            if (Pos2 >= newSolution.size() / 2) {
                                Pos2 = newSolution.size() / 2 - 1;
                            }
                            if (Pos1 == Pos2) {
                                continue;
                            }
                            boolean airplaneLane1;
                            if (Math.random() < 0.5) {
                                airplaneLane1 = true;
                            } else {
                                airplaneLane1 = false;
                            }
                            boolean airplaneLane2;
                            if (Math.random() < 0.5) {
                                airplaneLane2 = true;
                            } else {
                                airplaneLane2 = false;
                            }

                            Airplane Airplaneswap1 = newSolution.getAirplane(Pos1, airplaneLane1);
                            Airplane Airplaneswap2 = newSolution.getAirplane(Pos2, airplaneLane2);

                            newSolution.setAirplane(Pos2, Airplaneswap1, airplaneLane2);
                            newSolution.setAirplane(Pos1, Airplaneswap2, airplaneLane1);

                            if (!newSolution.update()) {
                                newSolution.setAirplane(Pos2, Airplaneswap2, airplaneLane2);
                                newSolution.setAirplane(Pos1, Airplaneswap1, airplaneLane1);
                                //System.out.println("revert");
                                continue;
                            }
                            int neighbourEnergy = newSolution.fitness();
                            currentSolution.update();
                            int currentEnergy = currentSolution.fitness();
                            best.update();
                            int fitness = best.fitness();

                            if (acceptanceProbability(currentEnergy, neighbourEnergy, temp[k]) > Math.random()) {

                                currentSolution = new AirplaneLists(newSolution.getAirplanesMatrix());
                                currentSolution.update();
                            }
                            if (currentSolution.fitness() < fitness) {

                                // System.out.println(fitness);
                                processing = true;
                                best = new AirplaneLists(currentSolution.getAirplanesMatrix());
                                Airplane[] airplane = best.getAirplanes();
                                // FIFO.fifo(airplane);
                                for (int j = 0; j < a.length; j++) {
                                    a[j] = airplane[j].getAirplane();
                                }

                                new ReadAndWrite().writeFile(a, "Joni.xls", wb);
                            }

                            temp[k] *= 1 - coolingRate[cool];
                        }
                        solutionsFitness[l] = best.fitness();
                        // System.out.print(temp[l]+"   ");
                    }
                    int temp123 = 0;
                    for (int z = 0; z < 10; z++) {
                        temp123 += solutionsFitness[z];
                    }
                    System.out.print(temp123 / 10 + "hi ");
                }
                 System.out.println();
            }
            System.out.println();
            System.out.println();
        }
        // }

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
