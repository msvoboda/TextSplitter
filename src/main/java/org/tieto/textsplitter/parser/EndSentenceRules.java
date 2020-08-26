package org.tieto.textsplitter.parser;

/**
 * Resolver for end of sentence
 */
public interface EndSentenceRules {
    /**
     * Rule for checking end of sentence
     * @param word one word
     * @return
     */
    boolean isEndOfSentence(String word);
}
