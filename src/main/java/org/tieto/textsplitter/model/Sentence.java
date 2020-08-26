package org.tieto.textsplitter.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

/***
 * domain model describes sentence
 * - all splitted words from sentence
 */
@NoArgsConstructor
public class Sentence implements  Comparable {

    @Getter
    @Setter
    List<String> words = new ArrayList<String>();

    @Getter
    @Setter
    int sentenceNo;

    public void sortWords() {
        words.sort(String.CASE_INSENSITIVE_ORDER);
    }

    @Override
    public int compareTo(Object o) {
        return Integer.compare(this.sentenceNo, ((Sentence)o).getSentenceNo());
    }
}
