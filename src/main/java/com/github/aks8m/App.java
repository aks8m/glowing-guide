package com.github.aks8m;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.event.WeakEventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.openhealthtools.mdht.uml.cda.ClinicalDocument;
import org.openhealthtools.mdht.uml.cda.util.CDAUtil;
import org.openhealthtools.mdht.uml.cda.util.ValidationResult;
import org.xmlunit.builder.DiffBuilder;
import org.xmlunit.builder.Input;
import org.xmlunit.diff.Diff;
import org.xmlunit.diff.Difference;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

/**
 * Main App class for running the glowing guide C-CDA comparison engine
 *
 */
public class App extends Application
{
    public static void main( String[] args )
    {
        launch(args);


    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/GlowingGuide.fxml"));
        AnchorPane root = loader.load();
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Glowing Guide");
        primaryStage.show();

//
//        ClinicalDocument sourceDoc = mdhtParsing(args[0]);
//        ClinicalDocument targetDoc = mdhtParsing(args[1]);
//        ComparerUtility comparison = new ComparerUtility(sourceDoc, targetDoc);
//        comparison.compare();
    }

    private static ClinicalDocument mdhtParsing(String ccdaFile){

        try {
            ClinicalDocument clinicalDocument = CDAUtil.load(
                    new FileInputStream(ccdaFile), (ValidationResult) null);

            System.out.println("break");

            return clinicalDocument;

        } catch (Exception e){
            e.printStackTrace();
        }

        return null;

    }

    private static void directComparison(String ccdaPathSource, String ccdaPathTarget, String comparisonOutput){

        Diff diff = DiffBuilder.compare(Input.fromFile(new File(ccdaPathSource)))
                .withTest(Input.fromFile(new File(ccdaPathTarget)))
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
                FileOutputStream fileOutputStream = new FileOutputStream(comparisonOutput);
                workbook.write(fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (IOException ioE){
                ioE.printStackTrace();
            }
        }
    }
}
