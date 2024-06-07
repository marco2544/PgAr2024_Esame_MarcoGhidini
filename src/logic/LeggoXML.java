package logic;

import org.w3c.dom.*;
import utility.Personaggio;
import utility.player.Giocatore;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;

import static costant.Costanti.N_PERSONAGGI;

public class LeggoXML {
    public static ArrayList<Personaggio> leggoPersonaggi(){
        ArrayList<Personaggio> p=new ArrayList<Personaggio>();
        try {
            File inputFile = new File("src/surceFile/listaCarte.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            // Read "personaggi"
            NodeList personaggiList = doc.getElementsByTagName("personaggio");
            for (int i = 0; i < N_PERSONAGGI; i++) {
                Node personaggioNode = personaggiList.item(i);
                if (personaggioNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element personaggioElement = (Element) personaggioNode;
                    String nome = personaggioElement.getElementsByTagName("nome").item(0).getTextContent();
                    String descrizione = personaggioElement.getElementsByTagName("descrizione").item(0).getTextContent();
                    int ps = Integer.parseInt(personaggioElement.getAttribute("pf"));
                    p.add(new Personaggio(ps, nome, descrizione));

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return p;
    }
    public static ArrayList<String> leggoruoli(){
        ArrayList<String> r=new ArrayList<String>();
        try {
            File inputFile = new File("src/surceFile/listaCarte.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            // Read "ruoli"
            NodeList ruoliList = doc.getElementsByTagName("ruolo");
            for (int i = 0; i < ruoliList.getLength(); i++) {
                Node ruoloNode = ruoliList.item(i);
                if (ruoloNode.getNodeType() == Node.ELEMENT_NODE) {
                    String ruolo = ruoloNode.getTextContent();
                    r.add(ruolo);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }
}
