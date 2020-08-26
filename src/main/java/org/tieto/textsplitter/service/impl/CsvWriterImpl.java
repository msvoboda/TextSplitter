package org.tieto.textsplitter.service.impl;

import com.opencsv.CSVWriter;
import org.tieto.textsplitter.model.Sentence;
import org.tieto.textsplitter.model.TextData;
import org.apache.commons.lang3.math.NumberUtils;
import org.tieto.textsplitter.service.OutputWriter;
import org.tieto.textsplitter.utils.ConstantUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CsvWriterImpl implements OutputWriter {

    @Override
    public void writeOutput(TextData data) throws IOException {

        try (OutputStream fos = System.out) {
             OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
             try (CSVWriter csvWriter = new CSVWriter(osw,
                     CSVWriter.DEFAULT_SEPARATOR,
                     CSVWriter.NO_QUOTE_CHARACTER,
                     CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                     CSVWriter.DEFAULT_LINE_END)) {

                 writeHeader(data, csvWriter);
                 writeSentences(data, csvWriter);
             }
        }
    }

    private void writeHeader(TextData data, CSVWriter csvWriter) {
        List<String> headerRecord = new ArrayList<>();
        for (int i = 0; i <= data.getMaxWords(); i++) {
            if (i == NumberUtils.INTEGER_ZERO) {
                headerRecord.add(ConstantUtils.CHAR_EMPTY);
            } else {
                headerRecord.add(String.format("Word %d", i));
            }
        }
        String[] array = headerRecord.stream().toArray(String[]::new);
        csvWriter.writeNext(array);
    }

    private void writeSentences(TextData data, CSVWriter csvWriter) {
        data.getSentences().values().stream().forEach(sentence -> this.writeOneSentence(sentence, csvWriter));
    }

    private void writeOneSentence(Sentence sentence, CSVWriter csvWriter) {
        List<String> record = new ArrayList<>();
        record.add(String.format("Sentence %d", sentence.getSentenceNo()));
        record.addAll(sentence.getWords());
        String[] array = record.stream().toArray(String[]::new);
        csvWriter.writeNext(array);
    }
}
