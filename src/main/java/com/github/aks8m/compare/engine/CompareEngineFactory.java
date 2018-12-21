package com.github.aks8m.compare.engine;

import org.openhealthtools.mdht.uml.cda.ClinicalDocument;
import org.openhealthtools.mdht.uml.cda.util.CDAUtil;
import org.openhealthtools.mdht.uml.cda.util.ValidationResult;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class CompareEngineFactory {

    public static CompareEngine CreateMDHTCompareEngine(String sourceFilePath, String targetFilePath){


        ClinicalDocument source = null;
        ClinicalDocument target = null;

        try {

            source = CDAUtil.load(new FileInputStream(sourceFilePath), (ValidationResult) null);
            target = CDAUtil.load(new FileInputStream(targetFilePath), (ValidationResult) null);


        }catch (FileNotFoundException fnfE){
            fnfE.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }

//        return null;


        return new MDHTComparisonEngine(source,target);
    }

}
