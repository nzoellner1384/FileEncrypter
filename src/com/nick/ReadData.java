package com.nick;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public final class ReadData {

    private ReadData() { }

    public static String[] readFile(File file) {
        try {
            /**
             * Setting up the classes used
             * to parse the encrypted files
             * that are saved in XML format
             * (It was easier to store and read
             * back the data after encrypting when
             * saved in XML format)
             */
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement();
            Element elem = doc.getDocumentElement();
            NamedNodeMap nnm = elem.getAttributes();
            NodeList nodes = elem.getChildNodes();
            String[] data = new String[2 + nodes.getLength()];
            data[0] = nnm.getNamedItem("messagelength").getNodeValue();
            data[1] = nnm.getNamedItem("blocksize").getNodeValue();
            for (int i = 0; i < nodes.getLength(); i++) {
                data[i+2] = nodes.item(i).getTextContent().trim();
            }
            return data;
        } catch (Exception ex) { ex.printStackTrace(); return null; }
    }

}
