package org.tieto.textsplitter.service;

import org.tieto.textsplitter.config.Configuration;
import org.tieto.textsplitter.service.impl.CsvWriterImpl;
import org.tieto.textsplitter.service.impl.XmlWriterImpl;

public class OutputWriterFactory {

    private final static String FORMAT_CSV = "csv";
    private final static String FORMAT_XML = "xml";

    /**
     * Get output writer by output type
     * @param configuration
     * @return
     */
    public static OutputWriter getOutputWriter(Configuration configuration) {
        OutputWriter outputWriter=null;
        if (FORMAT_CSV.equals(configuration.getFormat())) {
            outputWriter = new CsvWriterImpl();
        }
        else if (FORMAT_XML.equals(configuration.getFormat())) {
            outputWriter = new XmlWriterImpl();
        }
        return outputWriter;
    }
}
