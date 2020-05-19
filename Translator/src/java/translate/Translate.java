/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package translate;

/**
 *
 * @author jusuf
 */

import java.io.IOException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

@Path("/TranslateService/{word}/{lang1}/{lang2}")
public class Translate {
    @GET
    public String translate(@PathParam("word") String w, @PathParam("lang1") String l1, @PathParam("lang2") String l2) throws ParserConfigurationException, SAXException, IOException {
        String finalWord = "";
        
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setIgnoringElementContentWhitespace(true);
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse("./words.xml");
        
        Element root = doc.getDocumentElement();
        NodeList words = root.getElementsByTagName("word");
        NodeList languages1 = root.getElementsByTagName(l1);
        NodeList languages2 = root.getElementsByTagName(l2);
        
        for(int i = 0; i < words.getLength(); i++) {
            String language1 = languages1.item(i).getTextContent();
            if (language1.equals(w)) {
                String language2 = languages2.item(i).getTextContent();
                finalWord = language2;
            }
        }
        if (finalWord.equals("")) {
            finalWord = "Trazena rijec ne postoji u rijecniku!";
        }
        return finalWord;
    }
    
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        translate.Translate translateClient = new translate.Translate();
        System.out.println(translateClient.translate("Okul", "tr", "en"));
    }
}
