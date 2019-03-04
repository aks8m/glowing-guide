package com.github.aks8m.saxparser;

import com.github.aks8m.tree.ParserNode;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class SaxParser extends DefaultHandler {

    ParserNode root = null;
    ParserNode currentNode = null;

    public ParserNode parse(String filePath) throws ParserConfigurationException, SAXException, IOException {

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

        parser.parse(filePath, this);

        return root;
    }

    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {

//        System.out.println("Start Element: " + qName);

        ParserNode addNode = new ParserNode(qName);
        if (currentNode == null) {
            root = addNode;
        } else {
            currentNode.addChild(addNode);
        }
        currentNode = addNode;

        for (int i = 0; i < attributes.getLength(); i++) {
            ParserNode addAttributeNode = new ParserNode(attributes.getQName(i),attributes.getValue(i));
            currentNode.addAttribute(addAttributeNode);
        }

    }

    public void characters(char ch[], int start, int length) throws SAXException {
        currentNode.setValue(new String(ch, start, length).replace("\n", "").replace("\t", "").trim());
    }



    public void endElement(String uri, String localName,
                           String qName) throws SAXException {
        currentNode = currentNode.getParent();
    }

}
