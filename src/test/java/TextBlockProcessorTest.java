import org.junit.Assert;
import org.junit.Test;
import org.tieto.textsplitter.model.Sentence;
import org.tieto.textsplitter.parser.TextBlockProcessor;
import org.tieto.textsplitter.parser.impl.TextBlockProcessorImpl;

import java.util.List;

public class TextBlockProcessorTest {

    @Test
    public void testEmpty() {
        TextBlockProcessor blockProcessor = new TextBlockProcessorImpl();
        blockProcessor.addLine("");
        List<Sentence> sentences = blockProcessor.processTextLines();
        Assert.assertEquals(0, sentences.size());
    }

    @Test
    public void testSimpleSentence() {
        TextBlockProcessor blockProcessor = new TextBlockProcessorImpl();
        blockProcessor.addLine("Hello world!");
        List<Sentence> sentences = blockProcessor.processTextLines();
        Assert.assertEquals(1, sentences.size());
    }

    @Test
    public void testSimpleTwoSentences() {
        TextBlockProcessor blockProcessor = new TextBlockProcessorImpl();
        blockProcessor.addLine("Hello world!");
        blockProcessor.addLine("Welcome to application?");
        List<Sentence> sentences = blockProcessor.processTextLines();
        Assert.assertEquals(2, sentences.size());

        Sentence s1 = sentences.get(0);
        Assert.assertEquals(2, s1.getWords().size());
        Sentence s2 = sentences.get(1);
        Assert.assertEquals(3, s2.getWords().size());
    }

    @Test
    public void testSimpleTwoSentences2() {
        TextBlockProcessor blockProcessor = new TextBlockProcessorImpl();
        blockProcessor.addLine("Hello world!");
        blockProcessor.addLine("Welcome to ");
        blockProcessor.addLine(" application?");
        List<Sentence> sentences = blockProcessor.processTextLines();
        Assert.assertEquals(2, sentences.size());

        Sentence s1 = sentences.get(0);
        Assert.assertEquals(2, s1.getWords().size());
        Sentence s2 = sentences.get(1);
        Assert.assertEquals(3, s2.getWords().size());
    }

    @Test
    public void testWithNumber() {
        TextBlockProcessor blockProcessor = new TextBlockProcessorImpl();
        blockProcessor.addLine("Hello world!");
        blockProcessor.addLine("Welcome to my 1. ");
        blockProcessor.addLine(" application?");
        List<Sentence> sentences = blockProcessor.processTextLines();
        Assert.assertEquals(2, sentences.size());

        Sentence s1 = sentences.get(0);
        Assert.assertEquals(2, s1.getWords().size());
        Sentence s2 = sentences.get(1);
        Assert.assertEquals(5, s2.getWords().size());
    }

    @Test
    public void testException1() {
        TextBlockProcessor blockProcessor = new TextBlockProcessorImpl();
        blockProcessor.addLine("Hello world Mr. !");
        List<Sentence> sentences = blockProcessor.processTextLines();
        Assert.assertEquals(1, sentences.size());

        Sentence s1 = sentences.get(0);
        Assert.assertEquals(3, s1.getWords().size());
    }
}
