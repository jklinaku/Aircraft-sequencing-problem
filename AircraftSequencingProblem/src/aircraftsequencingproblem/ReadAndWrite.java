/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aircraftsequencingproblem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author Jon Klinaku
 */
public class ReadAndWrite {

    public String[][] readAirplaneData(String filepath, int n) throws IOException {
        int rowNrStart = 4;
        int i = 0;
        String[][] answer = new String[40][3];
        //String[][] answer = new String[30][3];
        FileInputStream fis = new FileInputStream(new File(filepath));
        Workbook wb = new HSSFWorkbook(fis);
        Sheet sheet = wb.getSheetAt(n);
        Row row = sheet.getRow(rowNrStart);

        while (row != null && i<40 ) {
            for (int j = 1; j < 4; j++) {
                answer[i][j - 1] = row.getCell(j).toString();
            }
            rowNrStart++;
            row = sheet.getRow(rowNrStart);
            i++;

        }

        return answer;
    }
    public String[][] readAirplaneData(String filepath, int n,int rowNrStart) throws IOException {
        
        int i = 0;
        String[][] answer = new String[40][3];
        //String[][] answer = new String[30][3];
        FileInputStream fis = new FileInputStream(new File(filepath));
        Workbook wb = new HSSFWorkbook(fis);
        Sheet sheet = wb.getSheetAt(n);
        Row row = sheet.getRow(rowNrStart);

        while (row != null && i<40 ) {
            for (int j = 1; j < 4; j++) {
                answer[i][j - 1] = row.getCell(j).toString();
            }
            rowNrStart++;
            row = sheet.getRow(rowNrStart);
            i++;

        }

        return answer;
    }

    public int[][][] readRestrictionData(String filepath, int n) throws IOException {
        int[][][] answer = new int[2][6][6];

        FileInputStream fis = new FileInputStream(new File(filepath));
        Workbook wb = new HSSFWorkbook(fis);
        Sheet sheet = wb.getSheetAt(n);
        for (int i = 3; i < 9; i++) {
            Row row1 = sheet.getRow(i);
            Row row2 = sheet.getRow(i + 10);
            for (int j = 7; j < 13; j++) {
                answer[0][i - 3][j - 7] = (int) Double.parseDouble(row1.getCell(j).toString());
                answer[1][i - 3][j - 7] = (int) Double.parseDouble(row2.getCell(j).toString());
                //System.out.print(answer[1][i - 3][j - 7] + " ");
            }
            // System.out.println();
        }
        return answer;
    }

    public void writeFile(String[][] s, String filepath, Workbook wb) throws IOException {
        CreationHelper createHelper = wb.getCreationHelper();
        Sheet sheet = wb.createSheet();
        for (int i = 0; i < s.length; i++) {
            Row row = sheet.createRow((short) i);
            for (int j = 0; j < s[0].length; j++) {
                row.createCell(j).setCellValue(createHelper.createRichTextString(s[i][j]));
            }
        }
        try {
            FileOutputStream fileOut = new FileOutputStream(filepath);
            wb.write(fileOut);
            wb.close();
        } catch (Exception e) {
        }
    }

    public void writeFile(String[][][] s, String filepath, Workbook wb) throws IOException {
        CreationHelper createHelper = wb.getCreationHelper();

        for (int sheetNo = 0; sheetNo < s.length; sheetNo++) {
            Sheet sheet = wb.createSheet(s[sheetNo][1][0]);
            
            for (int i = 0; i < s[0].length; i++) {
                Row row = sheet.createRow((short) i);
                for (int j = 0; j < s[0][0].length; j++) {
                    row.createCell(j).setCellValue(createHelper.createRichTextString(s[sheetNo][i][j]));
                }
            }
            try {
                FileOutputStream fileOut = new FileOutputStream(filepath);
                wb.write(fileOut);
                wb.close();
            } catch (Exception e) {
            }
        }
    }

}
