import org.junit.Assert;
import org.junit.Test;
import org.tieto.textsplitter.config.Configuration;
import org.tieto.textsplitter.model.Sentence;
import org.tieto.textsplitter.model.TextData;
import org.tieto.textsplitter.service.OutputWriter;
import org.tieto.textsplitter.service.OutputWriterFactory;
import org.tieto.textsplitter.service.impl.CsvWriterImpl;
import org.tieto.textsplitter.service.impl.XmlWriterImpl;

import java.io.IOException;

public class OutputWriterTest {

    @Test
    public void TestWriterFactory() {
        Configuration config = new Configuration();
        config.setFormat("csv");
        OutputWriter outputWriter = OutputWriterFactory.getOutputWriter(config);
        boolean instance = outputWriter instanceof CsvWriterImpl;
        Assert.assertEquals(true, instance);
        config.setFormat("xml");
        outputWriter = OutputWriterFactory.getOutputWriter(config);
        instance = outputWriter instanceof XmlWriterImpl;
        Assert.assertTrue(instance);

    }

    @Test
    public void testCsv() throws IOException {
        Configuration config = new Configuration();
        config.setFormat("csv");
        OutputWriter outputWriter = OutputWriterFactory.getOutputWriter(config);

        outputWriter.writeOutput(getMockData());
    }

    @Test
    public void testXml() throws IOException {
        Configuration config = new Configuration();
        config.setFormat("xml");
        OutputWriter outputWriter = OutputWriterFactory.getOutputWriter(config);

        outputWriter.writeOutput(getMockData());
    }

    private TextData getMockData() {
        TextData data = new TextData();
        Sentence sentence = new Sentence();
        sentence.getWords().add("Hello");
        sentence.getWords().add("word");
        sentence.setSentenceNo(1);
        data.addSentence(sentence);
        return data;
    }
}
