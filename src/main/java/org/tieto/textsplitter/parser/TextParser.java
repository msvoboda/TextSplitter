package org.tieto.textsplitter.parser;

import org.tieto.textsplitter.model.TextData;

import java.util.Scanner;

/**
 * TextParser prses all incoming data from Scanner
 */
public interface TextParser {
    /**
     * Parse text from input - Scanner
     * @param scan
     */
    TextData parseTextFromScanner(Scanner scan);
}
