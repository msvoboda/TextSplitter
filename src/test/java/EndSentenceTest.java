import org.junit.Assert;
import org.junit.Test;
import org.tieto.textsplitter.parser.EndSentenceRules;
import org.tieto.textsplitter.parser.impl.EndSentenceRulesImpl;


public class EndSentenceTest {
    EndSentenceRules endSentenceResolver = new EndSentenceRulesImpl();

    @Test
    public void TestNumberInSentence() {
        Assert.assertFalse(endSentenceResolver.isEndOfSentence("1."));
    }

    @Test
    public void TestNamePrefixInSentence() {
        Assert.assertFalse(endSentenceResolver.isEndOfSentence("Mr."));
    }

    @Test
    public void testEndSentence() {
        Assert.assertTrue(endSentenceResolver.isEndOfSentence("name."));
    }

    @Test
    public void testQuerySentence() {
        Assert.assertTrue(endSentenceResolver.isEndOfSentence("name?"));
    }

    @Test
    public void testExclamationSentence() {
        Assert.assertTrue(endSentenceResolver.isEndOfSentence("name!"));
    }

    @Test
    public void testEndDotSentence() {
        Assert.assertTrue(endSentenceResolver.isEndOfSentence("."));
    }
}
