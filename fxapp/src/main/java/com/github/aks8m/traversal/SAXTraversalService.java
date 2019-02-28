package com.github.aks8m.traversal;

import com.github.aks8m.saxparser.SaxParser;
import com.github.aks8m.tree.ParserNode;
import javafx.concurrent.Task;
import javafx.concurrent.Service;

import java.io.File;

public class SAXTraversalService extends Service<ParserNode> {

    private String filePath;
    private SaxParser saxParser;

    public SAXTraversalService(SaxParser sparser, File filePath) {
        this.filePath = filePath.getPath();
        this.saxParser = sparser;

    }


    @Override
    protected Task<ParserNode> createTask() {

        return new Task<ParserNode>() {

            @Override
            protected ParserNode call() throws Exception {
                return saxParser.parse(filePath);
            }
        };
    }
}
