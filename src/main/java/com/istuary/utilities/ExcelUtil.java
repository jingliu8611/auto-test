package com.istuary.utilities;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

/**
 * Created by jliu on 22/07/15.
 */
public class ExcelUtil {

    static Sheet wrksheet;
    static Workbook wrkbook =null;
    static Hashtable dict= new Hashtable();
    static Hashtable flaggedMethod = new Hashtable();
    static Hashtable flaggedTest = new Hashtable();
    static Hashtable flaggedClass = new Hashtable();

    //Create a Constructor
    public ExcelUtil(String ExcelSheetPath) throws BiffException, IOException
    {
        //Initialize
        wrkbook = Workbook.getWorkbook(new File(ExcelSheetPath));
        //For Demo purpose the excel sheet path is hardcoded, but not recommended :)
        wrksheet = wrkbook.getSheet("Sheet1");

        columnDictionary();
    }

    //Returns the Number of Rows
    public static int rowCount()
    {
        return wrksheet.getRows();
    }

    public static String readCell(String colName, int rowNumber) {

        return readCell(getCell(colName), rowNumber);

    }

    //Returns the Cell value by taking row and Column values as argument
    public static String readCell(int column,int row)
    {
        return wrksheet.getCell(column,row).getContents();
    }

    //Create Column Dictionary to hold all the Column Names
    public static void columnDictionary()
    {
        //Iterate through all the columns in the Excel sheet and store the value in Hashtable
        for(int col=0;col < wrksheet.getColumns();col++)
        {
            dict.put(readCell(col,0), col);
        }
    }

    //Read Column Names
    public static int getCell(String colName)
    {
        try {
            int value;
            value = ((Integer) dict.get(colName)).intValue();
            return value;
        } catch (NullPointerException e) {
            return (0);

        }
    }

    public static Hashtable getFlaggedMethodsBk(String colName) {

        try{
            int methodCount = 1;
            for (int row = 0; row < rowCount(); row++) {
                if (readCell(colName, row).equals("Y")) {
                    flaggedMethod.put(methodCount, readCell("Function",row) + ";" + readCell("ExcelName", row));
                    methodCount++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return flaggedMethod;

    }

    public static Hashtable getFlaggedTests() {

        try{
            int testCnt = 1;
            flaggedTest.clear();
            for (int row = 0; row < rowCount(); row++) {
                if (readCell("Flag", row).equals("Y") && !readCell("Test", row).equals("")) {
                    flaggedTest.put(testCnt, readCell("Test", row));
                    testCnt++;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return flaggedTest;

    }

    public static Hashtable getFlaggedClasses(String testName) {
        try {
            int classCnt = 1;
            int rowCnt = rowCount();
            flaggedClass.clear();
            for (int row = 0; row < rowCnt; row++) {
                if (readCell("Test", row).equals(testName)) {
                    for (int classRow = row + 1; classRow < rowCnt; classRow++) {
                        if (!readCell("Test", classRow).equals("")) {
                            break;
                        } else if (readCell("Flag", classRow).equals("Y") && !readCell("Class", classRow).equals("")) {
                            flaggedClass.put(classCnt, readCell("Class", classRow));
                            classCnt++;
                        }
                    }
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flaggedClass;
    }

    public static Hashtable getFlaggedMethods(String testName, String className) {
        try {
            int methodCnt = 1;
            int rowCnt = rowCount();
            flaggedMethod.clear();
            for (int row = 0; row < rowCnt; row++) {
                if (readCell("Test", row).equals(testName)) {
                    for (int classRow = row + 1; classRow < rowCnt; classRow++) {
                        if (readCell("Class", classRow).equals(className)) {
                            for (int methodRow = classRow + 1; methodRow < rowCnt; methodRow++){
                                if (!readCell("Class", methodRow).equals("") || !readCell("Test", methodRow).equals("")) {
                                    break;
                                } else if (readCell("Flag", methodRow).equals("Y")) {
                                    flaggedMethod.put(methodCnt, readCell("Function", methodRow));
                                    methodCnt++;
                                }
                            }
                            break;
                        }
                    }
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flaggedMethod;
    }


}
