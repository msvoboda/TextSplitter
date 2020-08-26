package org.tieto.textsplitter.service.impl;

import org.tieto.textsplitter.model.Sentence;
import org.tieto.textsplitter.model.TextData;
import org.tieto.textsplitter.service.OutputWriter;
import org.tieto.textsplitter.utils.XmlUtils;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class XmlWriterImpl implements OutputWriter {
    @Override
    public void writeOutput(TextData data) {
        XMLOutputFactory xof = XMLOutputFactory.newInstance();
        XMLStreamWriter xsw = null;
        try{
            xsw = xof.createXMLStreamWriter(System.out);
            xsw.writeStartDocument("UTF-8","1.0");
            xsw.writeStartElement(XmlUtils.TAG_TEXT);
            //
            writeSentences(data, xsw);
            //
            xsw.writeEndElement();
            xsw.writeEndDocument();
            xsw.flush();
        }
        catch (Exception e){
            System.err.println("Unable to write the file: " + e.getMessage());
        }
        finally{
            try{
                if (xsw != null){
                    xsw.close();
                }
            }
            catch (Exception e){
                System.err.println("Unable to close the file: " + e.getMessage());
            }
        }
    }

    /**
     * Write sentences
     * @param data TextData
     * @param xsw XMl stream
     */
    private void writeSentences(TextData data, XMLStreamWriter xsw) {
        data.getSentences().values().stream().forEach(sentence -> {
            try {
                this.writeOneSentence(sentence, xsw);
            } catch (XMLStreamException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Write only one sentence into outpput
     * @param sentence
     * @param xsw
     * @throws XMLStreamException
     */
    private void writeOneSentence(Sentence sentence, XMLStreamWriter xsw) throws XMLStreamException {
        xsw.writeStartElement(XmlUtils.TAG_SENTENCE);
        sentence.getWords().stream().forEach(word -> {
            try {
                this.writeWord(word, xsw);
            } catch (XMLStreamException e) {
                e.printStackTrace();
            }
        });
        xsw.writeEndElement();
    }

    /**
     * Write word
     * @param word
     * @param xsw
     * @throws XMLStreamException
     */
    private void writeWord(String word, XMLStreamWriter xsw) throws XMLStreamException {
        xsw.writeStartElement(XmlUtils.TAG_WORD);
        xsw.writeCharacters(word);
        xsw.writeEndElement();
    }
}
