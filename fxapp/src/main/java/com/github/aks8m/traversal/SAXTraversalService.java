package com.github.aks8m.traversal;

import javafx.concurrent.Task;
import javafx.scene.control.TreeItem;

import java.io.File;

public class SAXTraversalService extends TraversalService {

    private String filePath;
    private SaxParser saxParser;

    public SAXTraversalService(File filePath) {
        this.filePath = filePath.getPath();
        this.saxParser = new SaxParser();

    }


    @Override
    protected Task<TreeItem<String>> createTask() {

        return new Task<TreeItem<String>>() {

            @Override
            protected TreeItem<String> call() throws Exception {
                return saxParser.parse(filePath);
            }
        };
    }
}
