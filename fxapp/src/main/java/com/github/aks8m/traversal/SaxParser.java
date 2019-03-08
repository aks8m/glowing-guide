package com.github.aks8m.traversal;

import com.github.aks8m.tree.AttributeNode;
import com.github.aks8m.tree.ElementNode;
import com.github.aks8m.tree.XMLElement;
import javafx.scene.control.TreeItem;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class SaxParser extends DefaultHandler {

    TreeItem<String> root = null;
    TreeItem<String> currentNode = null;


    public TreeItem<String> parse(String filePath) throws ParserConfigurationException, SAXException, IOException {

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

        parser.parse(filePath, this);

        return root;
    }

    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {

        ElementNode addNode = new ElementNode(qName);
        if (currentNode == null) {
            root = addNode;
        } else {
            currentNode.getChildren().add(addNode);
        }
        currentNode = addNode;

        for (int i = 0; i < attributes.getLength(); i++) {
            AttributeNode addAttributeNode = new AttributeNode(attributes.getQName(i),attributes.getValue(i));
            currentNode.getChildren().add(addAttributeNode);
        }

    }

    public void characters(char ch[], int start, int length) throws SAXException {
        String addString = new String(ch, start, length).replace("\n", "").replace("\t", "").trim();
        if (addString != null && !addString.equals("")) {
            currentNode.setValue(currentNode.getValue() + ": " + addString);
            ((XMLElement) currentNode).setElementValue(addString);
        }

    }



    public void endElement(String uri, String localName,
                           String qName) throws SAXException {
        try {
            currentNode = (ElementNode) currentNode.getParent();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
