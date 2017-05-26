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

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        FIFO f = new FIFO();
        Workbook wb = new HSSFWorkbook();
        //for (int k = 0; k < 12; k++) {
        String[][] a = new ReadAndWrite().readFile("ara.xls", 7);
        Airplane[] airplane = new Airplane[a.length];
        for (int i = 0; i < airplane.length; i++) {
            airplane[i] = new Airplane(a[i]);
        }
        f.fifo(airplane);
        Population p = new Population(airplane);
        int[] d = p.fragments();
        p.populationCreator(d);
        Subset[] s = p.getSubset();
        for (int i = 0; i < s.length; i++) {
            System.out.println("Subset: " + i);
            s[i].printSubset();
        }
        for (int j = 0; j < a.length; j++) {
            a[j] = airplane[j].getAirplane();
        }
        new ReadAndWrite().writeFile(a, "Joni.xls", wb);
    }
}
