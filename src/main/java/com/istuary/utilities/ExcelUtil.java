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

    public static Hashtable getFlaggedMethods(String colName) {

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

}
