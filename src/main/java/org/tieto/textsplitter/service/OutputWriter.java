package org.tieto.textsplitter.service;

import org.tieto.textsplitter.model.TextData;

import java.io.IOException;

public interface OutputWriter {
    void writeOutput(TextData data) throws IOException;
}
