/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package send.sms.az.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.model.UploadedFile;
import send.sms.az.model.AddressBook;
import send.sms.az.model.Entry;

public class ReadExcelFileToList {

    private static final Logger logger = Logger.getLogger(ReadExcelFileToList.class.getName());

    public static List<Map<String, Entry>> readExcelData(UploadedFile fileName) {
        List<Map<String, Entry>> mapList = new ArrayList<>();
//  logger.info("--------------- starting workbook");
        try {
            //Create the input stream from the xlsx/xls file
            InputStream fis = fileName.getInputstream();
//            InputStream fis = new FileInputStream("D:/test.xls");

            //Create Workbook instance for xlsx/xls file input stream
            Workbook workbook = null;
//            logger.info("--------------- getting workbook");
            if (fileName.getFileName().toLowerCase().endsWith("xlsx")) {
//                logger.info("--------------- " + fileName.getFileName().toLowerCase());
                workbook = new XSSFWorkbook(fis);
            } else if (fileName.getFileName().toLowerCase().endsWith("xls")) {
                workbook = new HSSFWorkbook(fis);
            }

            //Get the number of sheets in the xlsx file
            int numberOfSheets = workbook.getNumberOfSheets();

            //loop through each of the sheets
            for (int i = 0; i < numberOfSheets; i++) {

                //Get the nth sheet from the workbook
                Sheet sheet = workbook.getSheetAt(i);

                //every sheet has rows, iterate over them
                Iterator<Row> rowIterator = sheet.iterator();
                int rowPos = 0;
                while (rowIterator.hasNext()) {
                    ++rowPos;
                    Map<String, Entry> map = new HashMap<>();//map for full row
                    //Get the row object
                    Row row = rowIterator.next();

                    //Every row has columns, get the column iterator and iterate over them
                    Iterator<Cell> cellIterator = row.cellIterator();
                    int cellPos = 0;//setirsayi
                    while (cellIterator.hasNext()) {
                        //Get the Cell object
                        ++cellPos;
                        Cell cell = cellIterator.next();

                        //check the cell type and process accordingly
                        switch (cell.getCellType()) {
                            case Cell.CELL_TYPE_STRING:
                                String value = cell.getStringCellValue().trim();
//                                System.out.println("string " + value + " " + cellPos + " " + rowPos);
                                map.put("" + (cellPos - 1), new Entry("" + rowPos, value));
                                break;
                            case Cell.CELL_TYPE_NUMERIC:
                                double numValue = cell.getNumericCellValue();
//                                System.out.println("numb " + numValue + " " + cellPos + " " + rowPos);
                                map.put("" + (cellPos - 1), new Entry("" + rowPos, "" + numValue));
                                break;
                        }
                    } //end of cell iterator
//                    System.out.println("added to list");
                    mapList.add(map);
                } //end of rows iterator

            } //end of sheets for loop

            //close file input stream
            fis.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return mapList;
    }

    public static void main(String args[]) {
        File s = new File("D:/test.xls");
        for (Map<String, Entry> arg : readExcelData(null)) {
            for (Map.Entry<String, Entry> entrySet : arg.entrySet()) {
                String key = entrySet.getKey();
                Entry value = entrySet.getValue();
                System.out.println(key + " " + value);
            }
            System.out.println("***************************");
        }
    }

}
