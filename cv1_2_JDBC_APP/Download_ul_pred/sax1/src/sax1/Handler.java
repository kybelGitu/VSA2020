package sax1;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * SAX Handler, ktory najde bydlisko hladanej osoby
 */
public class Handler extends DefaultHandler {

    public String getResult() {
        return result;
    }

    public void setHladana(String hladana) {
        this.hladana = hladana;
    }

    private String result = null;
    private String hladana = null;

    String text;
    String meno;
    String byd;

    boolean inOsoba = false;

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        text = new String(ch, start, length);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (localName.equals("osoba")) {
            inOsoba = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (localName.equals("meno")) {
            meno = text;
        }
        if (localName.equals("bydlisko")) {
            byd = text;
        }
        if (localName.equals("osoba")) {
            if (meno != null && meno.equals(hladana)) {
                result = byd;
            }
            inOsoba = false;
            meno = null;
            byd = null;
        }
    }
}
