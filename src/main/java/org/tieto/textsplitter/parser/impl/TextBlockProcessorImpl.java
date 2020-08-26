package org.tieto.textsplitter.parser.impl;

import lombok.Getter;
import org.tieto.textsplitter.model.Sentence;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.tieto.textsplitter.parser.EndSentenceRules;
import org.tieto.textsplitter.parser.TextBlockProcessor;
import org.tieto.textsplitter.utils.ConstantUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TextBlockProcessorImpl implements TextBlockProcessor {

    final static Logger log = LogManager.getLogger(TextBlockProcessorImpl.class);

    // data input lines in one block
    ArrayList<String> textLines = new ArrayList<>();
    // list of all sentences
    ArrayList<Sentence> sentences = new ArrayList<>();
    // end of sentence resolver
    EndSentenceRules endSentenceResolver = new org.tieto.textsplitter.parser.impl.EndSentenceRulesImpl();
    // maximum words
    @Getter
    int maxWordCount=0;


    public void addLine(String s) {
        String[] tokens = s.split(ConstantUtils.REGEX_WORDS);
        Arrays.stream(tokens).forEach(this::addWords);
    }


    public void addWords(String line) {
        textLines.add(line);
    }

    public int getLinesCount() {
        return textLines.size();
    }

    public List<Sentence> processTextLines() {
        sentences.clear();
        textLines.stream().forEach(this::processWord);
        textLines.clear();

        return sentences;
    }

    Sentence sentence =  null;
    int sentenceCnt=1; // sentences counter

    /**
     * Process one word - adds word to sentence or decides end of sentence and create new one
     * @param word
     */
    private void processWord(String word) {
        if (sentence == null) {
            sentence = new Sentence();
            sentence.setSentenceNo(sentenceCnt++);
        }
        //
        if (endSentenceResolver.isEndOfSentence(word)) {
            word = word.substring(0, word.length()-1);
            if (StringUtils.isNoneEmpty(word)){
                sentence.getWords().add(word);
            }
            sentence.sortWords(); // sort words
            sentences.add(sentence);
            Integer cntWords = sentence.getWords().size();
            // set maximum words
            maxWordCount = maxWordCount < cntWords ? cntWords : maxWordCount;
            sentence = new Sentence();
            sentence.setSentenceNo(sentenceCnt++);
        }
        else if (StringUtils.isNoneEmpty(word)){
            sentence.getWords().add(word);
        }
    }
}
