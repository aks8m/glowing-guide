package com.github.aks8m.compare;

import org.openhealthtools.mdht.uml.cda.ClinicalDocument;

public class CompareFactory {

    public static CompareService CreateMDHTCompareService(ClinicalDocument source, ClinicalDocument target){
        return new MDHTCompareService(source,target);
    }

}
