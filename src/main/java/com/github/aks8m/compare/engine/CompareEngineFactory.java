package com.github.aks8m.compare.engine;

import com.github.aks8m.report.result.ResultTreeItem;
import org.openhealthtools.mdht.uml.cda.ClinicalDocument;
import org.openhealthtools.mdht.uml.cda.util.CDAUtil;
import org.openhealthtools.mdht.uml.cda.util.ValidationResult;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class CompareEngineFactory {

    public static CompareEngine CreateMDHTCompareEngine(String sourceFilePath, String targetFilePath, ResultTreeItem sourceRoot, ResultTreeItem targetRoot){


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

}
