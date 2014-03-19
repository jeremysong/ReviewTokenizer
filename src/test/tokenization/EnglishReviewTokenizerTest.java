import org.junit.Test;

/**
 * @author Yang Song
 */
public class EnglishReviewTokenizerTest {

    @Test
    public void testTokenizer() {
        String englishSentence = "I&#39;ve been waiting for this for awhile!  Thanks for giving us this time saving " +
                "tool. Easier access to info is great. Great app";
        EnglishReviewTokenizer tokenizer = new EnglishReviewTokenizer();
        TokenCounter counter = new TokenCounter();
        System.out.println(counter.getTokens(englishSentence));
        System.out.println(tokenizer.tokenize(englishSentence));
    }
}
