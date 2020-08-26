package org.tieto.textsplitter.parser;

import org.tieto.textsplitter.model.Sentence;

import java.util.List;

public interface TextBlockProcessor {

    /**
     * Add line for processing
     * @param s
     */
    void addLine(String s);

    /**
     * Add new word for processing
     * @param line - string line
     */
    void addWords(String line);

    /**
     * Returns line counts, to be processed
     * @return
     */
    int getLinesCount();

    /**
     * Process text lines and returns array of sentences
     * @return
     */
    List<Sentence> processTextLines();

    /**
     * Get maximum words in one sentence
     * @return
     */
    int getMaxWordCount();
}
