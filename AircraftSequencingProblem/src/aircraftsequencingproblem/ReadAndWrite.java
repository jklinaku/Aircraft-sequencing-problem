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
 * @author Sead Mejzini
 */
public class ReadAndWrite {

    public String[][] readFile(String filepath, int n) throws IOException {
        String[][] answer = new String[60][7];
        FileInputStream fis = new FileInputStream(new File(filepath));
        Workbook wb = new HSSFWorkbook(fis);
        Sheet sheet = wb.getSheetAt(n);
        int i = 0, j = 0;
        for (Row row : sheet) {
            for (Cell cell : row) {
                answer[i][j] = cell.toString();
                j++;
            }
            j = 0;
            i++;
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

}
