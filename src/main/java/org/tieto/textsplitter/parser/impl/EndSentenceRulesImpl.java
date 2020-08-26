package org.tieto.textsplitter.parser.impl;

import org.tieto.textsplitter.parser.EndSentenceRules;
import org.tieto.textsplitter.utils.ConstantUtils;

import java.util.HashSet;
import java.util.Set;

public class EndSentenceRulesImpl implements EndSentenceRules {

    public EndSentenceRulesImpl() {
        stringExceptions.add("Mr.");
        stringExceptions.add("Mrs.");
    }

    Set<String> stringExceptions = new HashSet<>();

    @Override
    public boolean isEndOfSentence(String word) {
        if (word.endsWith(ConstantUtils.CHAR_DOT) || word.endsWith(ConstantUtils.CHAR_Q) || word.endsWith(ConstantUtils.CHAR_EXCL)) {
            // number check
            int char_dot = word.length()-1;
            if (char_dot > 0 && Character.isDigit(word.charAt(char_dot-1))) {
                 return false;
            }
            else if (char_dot > 0 && stringExceptions.contains(word)) {
                return false;
            }
            return true;
        }

        return false;
    }
}
