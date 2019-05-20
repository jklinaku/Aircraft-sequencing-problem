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
public class AircraftSequencingProblem3 {

    static int number = 1;
    static int[] maxLand = new int[100], allLand = new int[100], maxTake = new int[100], allTake = new int[100], maxPosCE = new int[100], maxPosCL = new int[100], maxPosCS = new int[100];

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
    public int job(int jump, double temp, double coolingRate, int startR) throws Exception {
//        double temp = 10000;
//        double coolingRate = 0.003;

        Workbook wb = new HSSFWorkbook();
        //for (int k = 0; k < 12; k++) {
        ReadAndWrite RaW = new ReadAndWrite();
        String[][] a = RaW.readAirplaneData("Dataset_LiederStolletz2016.xls", 1, startR);
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
        AirplaneLists best = new AirplaneLists(a.length, a);
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

        best.update();
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

        // while (processing) {
        AirplaneLists currentSolution = new AirplaneLists(best.getAirplanesMatrix());
//            int jump=4;
//            double temp = 40;
//            double coolingRate = 0.00001;

        while (temp > 1) {
            AirplaneLists newSolution = new AirplaneLists(currentSolution.getAirplanesMatrix());
            int Pos1 = (int) (newSolution.size() / 2 * Math.random());
            int Pos2 = (int) (Pos1 + (Math.random() * (jump * 2 + 1) - jump));
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
                //continue;
            }
            int neighbourEnergy = newSolution.fitness();
            currentSolution.update();
            int currentEnergy = currentSolution.fitness();
            best.update();
            int fitness = best.fitness();

            if (acceptanceProbability(currentEnergy, neighbourEnergy, temp) > Math.random()) {

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
//                    if(airplane[j].getAirplaneClass()<=3){
//                    allLand[number-1]+=airplane[j].getOTime()-airplane[j].getTime();
//                    if(maxLand[number-1]<airplane[j].getOTime()-airplane[j].getTime()){
//                        maxLand[number-1]=airplane[j].getOTime()-airplane[j].getTime();
//                        System.out.println(maxLand[number-1]);
//                    }
//                    }

                }

            }

            temp *= 1 - coolingRate;
        }
        // }
        Airplane[] airplane = best.getAirplanes();
        for (int j = 0; j < a.length; j++) {
            // a[j] = airplane[j].getAirplane();
            if (airplane[j].getAirplaneClass() <= 3) {
                allLand[number - 1] += airplane[j].getOTime() - airplane[j].getTime();
                if (maxLand[number - 1] < airplane[j].getOTime() - airplane[j].getTime()) {
                    maxLand[number - 1] = airplane[j].getOTime() - airplane[j].getTime();
                    //System.out.println(maxLand[number-1]);
                }
            } else {
                allTake[number - 1] += airplane[j].getOTime() - airplane[j].getTime();
                if (maxTake[number - 1] < airplane[j].getOTime() - airplane[j].getTime()) {
                    maxTake[number - 1] = airplane[j].getOTime() - airplane[j].getTime();
                    //System.out.println(maxLand[number-1]);
                }
            }

        }
        new ReadAndWrite().writeFile(a, "Lista" + number + ".xls", wb);
       // Airplane temporal;
        for (int i = 0; i < a.length-1; i++) {
            for (int j = 0; j < a.length-i-1; j++) {
                if (airplane[j].getOTime() > airplane[j+1].getOTime()) 
                { 
                    // swap arr[j+1] and arr[i] 
                    Airplane temporal = airplane[j]; 
                    airplane[j] = airplane[j+1]; 
                    airplane[j+1] = temporal; 
                } 
            }}
        
            for (int j = 0; j < airplane.length; j++) {
                //System.out.println(airplane[j].getOTime());
            }
        
        for (int j = 0; j < a.length; j++) {
            // a[j] = airplane[j].getAirplane();
            int shift =(int)Double.parseDouble(airplane[j].getAirplane()[0])-j-1;
            //System.out.println(shift);
            if (shift>0) {
                maxPosCS[number - 1] += shift;
                if (maxPosCE[number - 1] <shift) {
                    maxPosCE[number - 1] = shift;
                    //System.out.println(maxLand[number-1]);
                    System.out.println(maxPosCE[number-1]);
                }
                

        }
            else {
                maxPosCS[number - 1] += Math.abs(shift);
                if (maxPosCL[number - 1] < (Math.abs(shift))) {
                    maxPosCL[number - 1] = (Math.abs(shift));
                    System.out.println("last"+maxPosCL[number-1]);
                    //System.out.println(maxLand[number-1]);
                }
            }
        }
        number++;
        return best.fitness();
//        Population p = new Population(airplane);
//        int[] d = p.fragments();
//        p.populationCreator(d);
//        Subset[] s = p.getSubset();
//        for (int i = 0; i < s.length; i++) {
//            System.out.println("Subset: " + i);
//            s[i].printSubset();
//        }
    }

    public static void main(String[] args) throws Exception {

        int[] solutionsFitness = new int[10];
        int[] jump = {4};
        double[] temp = {100};
        double[] coolingRate = {0.00001};
        int starts = 4;
        Workbook wb = new HSSFWorkbook();
//        double[] temp = {10, 40, 10, 10, 10};
//        double[] coolingRate = {0.1, 0.01};
//System.out.print(new AircraftSequencingProblem3().job(jump[0],temp[0], coolingRate[0]));
//System.exit(1);
        for (int fin = 0; fin < 10; fin++) {
            String[][][] a = new String[6][(coolingRate.length * jump.length) + 2 * jump.length][temp.length + 2];
            for (int i = 0; i < jump.length; i++) {
                for (int cool = 0; cool < coolingRate.length; cool++) {
                    for (int k = 0; k < temp.length; k++) {

                        for (int l = 0; l < 1; l++) {
                            // System.out.print('s');
                            int temp123 = 0;
                            long totalTime;
                            long timeSecond;
                            //long timeMili;
                            totalTime = System.nanoTime();
                            temp123 = new AircraftSequencingProblem3().job(jump[i], temp[k], coolingRate[cool], starts + 45 * fin);
                            totalTime = (System.nanoTime() - totalTime);
                            timeSecond = Math.round(totalTime / 1000000000.0);
                            //timeMili=(totalTime%1000000000)/1000000;
                            int Sum = temp123;
                            int max = temp123;
                            int min = temp123;
                            int average = 0;
                            long minTime = timeSecond;
                            long MaxTime = timeSecond;
                            long sumTime = timeSecond;
                            long avgTime = 0;

                            for (int z = 1; z < 10; z++) {
                                totalTime = System.nanoTime();
                                temp123 = new AircraftSequencingProblem3().job(jump[i], temp[k], coolingRate[cool], starts + 45 * fin);
                                Sum += temp123;
                                totalTime = (System.nanoTime() - totalTime);
                                timeSecond = Math.round(totalTime / 1000000000.0);
                                sumTime += timeSecond;
                                if (temp123 > max) {
                                    max = temp123;
                                }
                                if (temp123 < min) {
                                    min = temp123;
                                }
                                if (timeSecond > MaxTime) {
                                    MaxTime = timeSecond;
                                }
                                if (timeSecond < minTime) {
                                    minTime = timeSecond;
                                }
                                //timeMili=(totalTime%1000000000)/1000000;
                                // System.out.print(timeSecond);
                            }
                            average = Sum / 10;
                            avgTime = sumTime / 10;

                            a[0][(i * coolingRate.length + 2 * i)][0] = "jump=" + i;
                            a[0][(i * coolingRate.length + 2 * i + 1)][0] = "Mesatarja e fitnesit";
                            a[0][(i * coolingRate.length + 2 * i)][1] = "coolR\\temp";
                            a[0][(i * coolingRate.length + 2 * i)][k + 2] = temp[k] + "";
                            a[0][(cool + 1) + (i * coolingRate.length + 2 * i)][1] = coolingRate[cool] + "";
                            a[0][(cool + 1) + (i * coolingRate.length + 2 * i)][k + 2] = average + "";

                            a[1][(i * coolingRate.length + 2 * i)][0] = "jump=" + i;
                            a[1][(i * coolingRate.length + 2 * i + 1)][0] = "Mesatarja e kohes";
                            a[1][(i * coolingRate.length + 2 * i)][1] = "coolR\\temp";
                            a[1][(i * coolingRate.length + 2 * i)][k + 2] = temp[k] + "";
                            a[1][(cool + 1) + (i * coolingRate.length + 2 * i)][1] = coolingRate[cool] + "";
                            a[1][(cool + 1) + (i * coolingRate.length + 2 * i)][k + 2] = avgTime + "";

                            a[2][(i * coolingRate.length + 2 * i)][0] = "jump=" + i;
                            a[2][(i * coolingRate.length + 2 * i + 1)][0] = "Min e fitnesit";
                            a[2][(i * coolingRate.length + 2 * i)][1] = "coolR\\temp";
                            a[2][(i * coolingRate.length + 2 * i)][k + 2] = temp[k] + "";
                            a[2][(cool + 1) + (i * coolingRate.length + 2 * i)][1] = coolingRate[cool] + "";
                            a[2][(cool + 1) + (i * coolingRate.length + 2 * i)][k + 2] = min + "";

                            a[3][(i * coolingRate.length + 2 * i)][0] = "jump=" + i;
                            a[3][(i * coolingRate.length + 2 * i + 1)][0] = "min e kohes";
                            a[3][(i * coolingRate.length + 2 * i)][1] = "coolR\\temp";
                            a[3][(i * coolingRate.length + 2 * i)][k + 2] = temp[k] + "";
                            a[3][(cool + 1) + (i * coolingRate.length + 2 * i)][1] = coolingRate[cool] + "";
                            a[3][(cool + 1) + (i * coolingRate.length + 2 * i)][k + 2] = minTime + "";

                            a[4][(i * coolingRate.length + 2 * i)][0] = "jump=" + i;
                            a[4][(i * coolingRate.length + 2 * i + 1)][0] = "Max e fitnesit";
                            a[4][(i * coolingRate.length + 2 * i)][1] = "coolR\\temp";
                            a[4][(i * coolingRate.length + 2 * i)][k + 2] = temp[k] + "";
                            a[4][(cool + 1) + (i * coolingRate.length + 2 * i)][1] = coolingRate[cool] + "";
                            a[4][(cool + 1) + (i * coolingRate.length + 2 * i)][k + 2] = max + "";

                            a[5][(i * coolingRate.length + 2 * i)][0] = "jump=" + i;
                            a[5][(i * coolingRate.length + 2 * i + 1)][0] = "max e kohes";
                            a[5][(i * coolingRate.length + 2 * i)][1] = "coolR\\temp";
                            a[5][(i * coolingRate.length + 2 * i)][k + 2] = temp[k] + "";
                            a[5][(cool + 1) + (i * coolingRate.length + 2 * i)][1] = coolingRate[cool] + "";
                            a[5][(cool + 1) + (i * coolingRate.length + 2 * i)][k + 2] = MaxTime + "";
                            System.out.print(Sum / 10 + "hi ");
                        }
                        //System.out.println();
                    }
                    System.out.println();
                    //  System.out.println();
                }
                System.out.println();
                System.out.println();
            }
            for (int i = 0; i < a[0].length; i++) {
                for (int j = 0; j < a[0][0].length; j++) {
                    System.out.print(a[0][i][j] + " ");
                }
                System.out.println("");
            }

            wb = new HSSFWorkbook();
            new ReadAndWrite().writeFile(a, "Analizat" + number + ".xls", wb);
        }
        wb = new HSSFWorkbook();
        String[][] analizat = new String[101][7];
        analizat[0][0] = "maxLand";
        analizat[0][1] = "allLand";
        analizat[0][2] = "maxTake";
        analizat[0][3] = "allTake";
        analizat[0][4] = "Pozicioni me i hershem";
        analizat[0][5] = "Pozicioni me i vonshem";
        analizat[0][6] = "Pozicioni shuma";
        for (int i = 1; i < 101; i++) {

            analizat[i][0] = maxLand[i - 1] + "";
            analizat[i][1] = allLand[i - 1] + "";
            analizat[i][2] = maxTake[i - 1] + "";
            analizat[i][3] = allTake[i - 1] + "";
            analizat[i][4] = maxPosCE[i-1]+"";
            analizat[i][5] = maxPosCL[i-1]+"";
            analizat[i][6] = maxPosCS[i-1]+"";

        }

        new ReadAndWrite().writeFile(analizat, "Analizat e take offs.xls", wb);
        analizat = new String[11][7];
        analizat[0][0] = "maxLand";
        analizat[0][1] = "allLand";
        analizat[0][2] = "maxTake";
        analizat[0][3] = "allTake";
        analizat[0][4] = "Pozicioni me i hershem";
        analizat[0][5] = "Pozicioni me i vonshem";
        analizat[0][6] = "Pozicioni shuma";
        for (int i = 0; i < 10; i++) {
            int sum1 = 0;
            int sum2 = 0;
            int sum3 = 0;
            int sum4 = 0;
            int sum5 = 0;
            int sum6 = 0;
            int sum7 = 0;
            for (int j = i * 10; j < i * 10 + 10; j++) {
                sum1 += maxLand[j];
                sum2 += allLand[j];
                sum3 += maxTake[j];
                sum4 += allTake[j];
                sum5 += maxPosCE[j];
                sum6 += maxPosCL[j];
                sum7 += maxPosCS[j];
            }
            analizat[i + 1][0] = sum1 + "";
            analizat[i + 1][1] = sum2 + "";
            analizat[i + 1][2] = sum3 + "";
            analizat[i + 1][3] = sum4 + "";
            analizat[i+1][4] = sum5+"";
            analizat[i+1][5] = sum6+"";
            analizat[i+1][6] = sum7+"";
        }
        new ReadAndWrite().writeFile(analizat, "Analizat e take offs.xls", wb);
    }

}
