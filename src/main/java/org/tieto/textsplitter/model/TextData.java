package org.tieto.textsplitter.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.math.NumberUtils;
import org.tieto.textsplitter.model.Sentence;

import java.util.Map;
import java.util.TreeMap;


/**
 * Domain model for processed text
 */
@NoArgsConstructor
public class TextData {

    @Getter
    @Setter
    Map<Sentence, Sentence> sentences = new TreeMap<>();

    // max words
    @Getter
    @Setter
    int maxWords= NumberUtils.INTEGER_ZERO;

    /**
     * Add centence
     */
    public void addSentence(Sentence sentence) {
        sentences.put(sentence,sentence);
        setMaxWords(sentence.words.size());
    }

}
