package org.tieto.textsplitter.parser.impl;

import lombok.NoArgsConstructor;
import org.tieto.textsplitter.TextSplitterApp;
import org.tieto.textsplitter.model.Sentence;
import org.tieto.textsplitter.model.TextData;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.tieto.textsplitter.parser.TextBlockProcessor;
import org.tieto.textsplitter.parser.TextParser;
import org.tieto.textsplitter.utils.ConstantUtils;

import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.stream.Collectors;

@NoArgsConstructor
public class TextParserImpl implements TextParser {

    final static Logger log = LogManager.getLogger(TextSplitterApp.class);

    TextBlockProcessor textBlockProcessor = new org.tieto.textsplitter.parser.impl.TextBlockProcessorImpl();

    public TextData parseTextFromScanner(Scanner scan) {
        TextData textData = new TextData();
        Map<Sentence, Sentence> sentences = new TreeMap<>();

        // PARSING
        while(scan.hasNextLine()) {
            String s = scan.nextLine();
            if (s.length() > NumberUtils.INTEGER_ZERO) {
                textBlockProcessor.addLine(s);
            } else {
                log.info("Words:"+ textBlockProcessor.getLinesCount());
                sentences.putAll(textBlockProcessor.processTextLines().stream().collect(Collectors.toMap(sentence -> sentence, sentence-> sentence)));
            }
        }
        if (textBlockProcessor.getLinesCount() > NumberUtils.INTEGER_ZERO) {
            log.info("Last words:"+ textBlockProcessor.getLinesCount());
            sentences.putAll(textBlockProcessor.processTextLines().stream().collect(Collectors.toMap(sentence -> sentence, sentence-> sentence)));
        }

        log.info(String.format("Sentences: %d", sentences.size()));
        log.info(String.format("Maximum words in sentence: %d", textBlockProcessor.getMaxWordCount()));
        textData.setSentences(sentences);
        textData.setMaxWords(textBlockProcessor.getMaxWordCount());
        return  textData;
    }

}
