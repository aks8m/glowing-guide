package com.github.aks8m.engine;

import com.github.aks8m.report.result.ResultTreeItem;
import org.openhealthtools.mdht.uml.cda.ClinicalDocument;
import org.openhealthtools.mdht.uml.cda.util.CDAUtil;
import org.openhealthtools.mdht.uml.cda.util.ValidationResult;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class CompareEngineFactory {

    public static CompareEngine CreateMDHTCompareEngine(File sourceFilePath, File targetFilePath, ResultTreeItem sourceRoot, ResultTreeItem targetRoot){


        ClinicalDocument sourceDocument = null;
        ClinicalDocument targetDocument = null;

        try {

            sourceDocument = CDAUtil.load(new FileInputStream(sourceFilePath), (ValidationResult) null);
            targetDocument = CDAUtil.load(new FileInputStream(targetFilePath), (ValidationResult) null);

        }catch (FileNotFoundException fnfE){
            fnfE.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }

        return new MDHTComparisonEngine(sourceDocument,targetDocument, sourceRoot, targetRoot);
    }

    public static CompareEngine CreateSAXParserCompareEngine(File sourceFilePath, File targetFilePath, ResultTreeItem sourceRoot, ResultTreeItem targetRoot) {

        return new SAXParserCompareEngine(sourceFilePath,targetFilePath, sourceRoot, targetRoot);
    }

}
