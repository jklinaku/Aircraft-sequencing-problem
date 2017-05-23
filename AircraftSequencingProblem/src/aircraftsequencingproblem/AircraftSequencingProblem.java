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
        String[][] a = new ReadAndWrite().readFile("ara.xls", 0);
        Airplane[] airplane = new Airplane[a.length];
        for (int i = 0; i < airplane.length; i++) {
            airplane[i] = new Airplane(a[i]);
        }
        Modify m = new Modify(airplane);
        f.fifo(airplane);
        System.out.println(m.generateIndex());
        System.out.println(0 + ":   " + f.penalty(airplane));;
        Population p = new Population(airplane);
        p.populationCreator();
        Subset[] s = p.getSubset();
        for (int i = 0; i < s.length; i++) {
            Airplane[] temp = s[i].getAirplane();
            System.out.println("Subset" + (i + 1));
            for (int j = 0; j < temp.length; j++) {
                String[] tempp = temp[j].getAirplane();
                for (int k = 0; k < tempp.length; k++) {
                    System.out.print(tempp[k] + "\t\t");
                }
            }
        }
        for (int j = 0; j < a.length; j++) {
            a[j] = airplane[j].getAirplane();
        }
        new ReadAndWrite().writeFile(a, "Joni.xls", wb);

        //wb.close();
    }
}
//        String file = "ara.xls";
//        try {
//    POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(file));
//    HSSFWorkbook wb = new HSSFWorkbook(fs);
//    HSSFSheet sheet = wb.getSheetAt(0);
//    HSSFRow row;
//    HSSFCell cell;
//
//    int rows; // No of rows
//    rows = sheet.getPhysicalNumberOfRows();
//
//    int cols = 0; // No of columns
//    int tmp = 0;
//
//    // This trick ensures that we get the data properly even if it doesn't start from first few rows
//    for(int i = 0; i < 10 || i < rows; i++) {
//        row = sheet.getRow(i);
//        if(row != null) {
//            tmp = sheet.getRow(i).getPhysicalNumberOfCells();
//            if(tmp > cols) cols = tmp;
//        }
//    }
//
//    for(int r = 0; r < rows; r++) {
//        row = sheet.getRow(r);
//        if(row != null) {
//            for(int c = 0; c < cols; c++) {
//                cell = row.getCell((short)c);
//                if(cell != null) {
//                    // Your code here
//                    System.out.print(cell + "\t\t");
//                }
//            }
//        }
//        System.out.println();
//    }
//} catch(Exception ioe) {
//    ioe.printStackTrace();
//}
