package com.github.aks8m;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.xmlunit.builder.DiffBuilder;
import org.xmlunit.builder.Input;
import org.xmlunit.diff.Diff;
import org.xmlunit.diff.Difference;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Diff diff = DiffBuilder.compare(Input.fromFile(new File(args[0])))
                .withTest(Input.fromFile(new File(args[1])))
                .build();

        Iterator<Difference> differenceIterator = diff.getDifferences().iterator();
        int issueCount = 0;
        HSSFWorkbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("Difference Summary");

        System.out.println("---------------");
        while(differenceIterator.hasNext()){
            Difference difference = differenceIterator.next();

            if(difference.getComparison().toString().contains("Expected child '#comment' but was 'null'"))
                continue;

            issueCount++;

            System.out.println("Issue# " + issueCount);
            System.out.println(difference.getResult().toString());
            System.out.println(difference.getComparison().toString());
            System.out.println("---------------");

            Row header = sheet.createRow(0);

            header.createCell(0).setCellValue("Difference Type");
            header.createCell(1).setCellValue("Difference Summary");

            Row newRow = sheet.createRow(issueCount);
            newRow.createCell(0).setCellValue(difference.getResult().toString());
            newRow.createCell(1).setCellValue(difference.getComparison().toString());

            try {
                FileOutputStream fileOutputStream = new FileOutputStream(args[2]);
                workbook.write(fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (IOException ioE){
                ioE.printStackTrace();
            }





        }
    }
}
