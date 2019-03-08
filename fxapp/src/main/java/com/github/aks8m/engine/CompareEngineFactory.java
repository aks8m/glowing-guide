package com.github.aks8m.engine;

import javafx.scene.control.TreeItem;

import java.io.File;
import java.util.HashMap;

public class CompareEngineFactory {

    public static CompareEngine CreateSAXParserFileCompareEngine(File sourceFilePath, File targetFilePath, HashMap<TreeItem<String>, TreeItem<String>> nodemap) {

        return new SAXParserFileCompareEngine(sourceFilePath,targetFilePath, nodemap);
    }


}
