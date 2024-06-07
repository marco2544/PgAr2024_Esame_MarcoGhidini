package logic;

import org.w3c.dom.*;
import utility.Arma;
import utility.Carta;
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

    public static ArrayList<Arma> leggoArmi(){
        ArrayList<Arma> a=new ArrayList<Arma>();
        try {
            File inputFile = new File("src/surceFile/listaCarte.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            // Read "armi"
            NodeList armiList = doc.getElementsByTagName("arma");
            for (int i = 0; i < armiList.getLength(); i++) {
                Node armaNode = armiList.item(i);
                if (armaNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element armaElement = (Element) armaNode;
                    String nome = armaElement.getElementsByTagName("nome").item(0).getTextContent();
                    int distanza = Integer.parseInt(armaElement.getElementsByTagName("distanza").item(0).getTextContent());


                    NodeList copieList = armaElement.getElementsByTagName("copia");
                    for (int j = 0; j < copieList.getLength(); j++) {
                        Node copiaNode = copieList.item(j);
                        if (copiaNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element copiaElement = (Element) copiaNode;
                            String valore = copiaElement.getElementsByTagName("valore").item(0).getTextContent();
                            String seme = copiaElement.getElementsByTagName("seme").item(0).getTextContent();
                            a.add(new Arma(nome,distanza,valore,seme));
                        }
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }
    public static ArrayList<Carta> leggoCarte(){
        ArrayList<Carta> c=new ArrayList<Carta>();
        try {
            File inputFile = new File("src/surceFile/listaCarte.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList carteList = doc.getElementsByTagName("carta");
            for (int i = 0; i < carteList.getLength(); i++) {
                Node cartaNode = carteList.item(i);
                if (cartaNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element cartaElement = (Element) cartaNode;
                    String nome = cartaElement.getElementsByTagName("nome").item(0).getTextContent();
                    String descrizione = cartaElement.getElementsByTagName("descrizione").item(0).getTextContent();
                    Boolean equipaggiabile = Boolean.valueOf(cartaElement.getAttribute("equipaggiabile").toUpperCase());

                    NodeList copieList = cartaElement.getElementsByTagName("copia");
                    for (int j = 0; j < copieList.getLength(); j++) {
                        Node copiaNode = copieList.item(j);
                        if (copiaNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element copiaElement = (Element) copiaNode;
                            String valore = copiaElement.getElementsByTagName("valore").item(0).getTextContent();
                            String seme = copiaElement.getElementsByTagName("seme").item(0).getTextContent();
                            c.add(new Carta(nome,descrizione,equipaggiabile,valore,seme));
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }
}
