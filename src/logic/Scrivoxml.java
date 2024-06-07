package logic;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

public class Scrivoxml {

    public static void scrivoTabella(Map<String,Integer> classifica,int conta) {
        XMLOutputFactory xmlof;
        XMLStreamWriter xmlw;
        try (FileOutputStream writer = new FileOutputStream("src/surceFile/tabella.xml"))    {
            xmlof = XMLOutputFactory.newInstance();
            xmlw = xmlof.createXMLStreamWriter(writer, "UTF-8");
            try {
                xmlw.writeStartDocument("UTF-8", "1.0");
                xmlw.writeCharacters("\n");
                xmlw.writeStartElement("giocatori");
                for (int i = 0; i < GestioneGioco.getNomi().size(); i++) {
                    xmlw.writeStartElement("giocatore");
                    xmlw.writeStartElement("nome");
                    xmlw.writeCharacters(GestioneGioco.getNomi().get(i));
                    xmlw.writeEndElement();
                    xmlw.writeStartElement("sbleuri");
                    xmlw.writeCharacters(String.valueOf(classifica.get(GestioneGioco.getNomi().get(i))));
                    xmlw.writeEndElement();
                    xmlw.writeStartElement("npartite");
                    xmlw.writeCharacters(String.valueOf(conta));
                    xmlw.writeEndElement();
                    xmlw.writeStartElement("punteggio");
                    xmlw.writeCharacters(String.valueOf(classifica.get(GestioneGioco.getNomi().get(i))/conta));
                    xmlw.writeEndElement();
                    xmlw.writeEndElement();
                }
                xmlw.writeEndElement();//</routes>
                xmlw.writeEndDocument();
                xmlw.flush();
                xmlw.close();
            }
            catch (XMLStreamException e){
                System.out.println("errore");
            }
        }
        catch (XMLStreamException | IOException e)    {
            System.out.println("errore");
            System.out.println(e.getMessage());
        }
    }
}
