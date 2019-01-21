package com.github.aks8m.service;

import com.github.aks8m.model.Analysis;
import com.github.aks8m.repository.XodusRepository;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.StringReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Stack;
import java.util.UUID;

/**
 * 2019-01-20
 * aks8m - https://github.com/aks8m
 */
public class AnalysisService {

    private final XodusRepository xodusRepository;
    private final ArrayList<Long> parentIDs = new ArrayList<>();
    private boolean isNewParent = false;

    public AnalysisService() {
        xodusRepository = new XodusRepository();
    }

    public Analysis performAnalysis(String name, String xmlData){
        final LocalDateTime localDateTime = LocalDateTime.now();
        final String id = UUID.randomUUID().toString();
        final Analysis analysis = new Analysis(name, id, localDateTime);
        this.xodusRepository.beginTransactions(analysis);

        try {
            SAXParserFactory.newInstance().newSAXParser()
                    .parse(new InputSource(new StringReader(xmlData)),
                            new DefaultHandler(){
                                @Override
                                public void startDocument() throws SAXException {
                                    super.startDocument();
                                }

                                @Override
                                public void endDocument() throws SAXException {
                                    super.endDocument();
                                }

                                @Override
                                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                                   super.startElement(uri,localName,qName,attributes);

                                   xodusRepository.addChild(qName);
                                   for(int i = 0; i < attributes.getLength(); i++){
                                       xodusRepository.addProperty(attributes.getQName(i), attributes.getValue(i));
                                   }
                                   parentIDs.add(xodusRepository.getCurrentEntityID());
                                }

                                @Override
                                public void endElement(String uri, String localName, String qName) throws SAXException {
                                    super.endElement(uri, localName, qName);
                                    //detect if start == end, then new parent, but need to remember the current parent
                                }

                                @Override
                                public void characters(char[] ch, int start, int length) throws SAXException {
                                    StringBuilder stringBuilder = new StringBuilder();
                                    stringBuilder.append(new String(ch, start, length));

                                    if(!stringBuilder.toString().contains("\n")){//Has valid value
                                        xodusRepository.addProperty("value", stringBuilder.toString());
                                    }
                                }

                            });

        }catch (SAXException | ParserConfigurationException | IOException exception){
            exception.printStackTrace();
        }

        this.xodusRepository.endTransactions();
        return analysis;
    }

}
