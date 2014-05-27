package tokenization;

import com.sun.istack.internal.NotNull;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.core.StopAnalyzer;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.util.CharArraySet;
import org.apache.lucene.util.Version;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Yang Song
 */
public class EnglishReviewTokenizer {

    private final static String PRIME_SYMBOL = "&#39;";
    private final static String QUOTATION = "&#34;";

    private Analyzer reviewAnalyzer;

    public EnglishReviewTokenizer() {
        CharArraySet stopWordsSet = new CharArraySet(Version.LUCENE_46, StopAnalyzer.ENGLISH_STOP_WORDS_SET, true);
        String[] stopWords = new String[]{"i", "very", "app", "you", "my", "us", "so", "have", "get", "me", "all",
                "just", "on", "can", "when", "need", "make", "your", "would", "what", "keep", "make", "up", "help", "do",
                "much", "use", "more", "really", "one", "having", "waiting", "than", "it", "day", "make", "needs",
                "application", "give", "been", "many", "buy", "keeping", "say", "am", "try", "add", "still", "take",
                "any", "start", "find", "know", "back", "look", "never", "people",
                "lot", "out", "ever", "wait", "other", "don't", "think", "can't", "i'm", "it's", "from", "now", "go",
                "it", "some", "too", "could", "want", "how", "thing", "every", "look", "even", "u", "daily", "i've",
                "had", "far", "also", "given", "see", "does", "about", "should"};
        stopWordsSet.addAll(Arrays.asList(stopWords));
        reviewAnalyzer = new EnglishAnalyzer(Version.LUCENE_46, stopWordsSet);
    }

    public List<String> tokenize(@NotNull String rawSentence) {
        List<String> tokenResults = new ArrayList<>();

        String sentence = preProcess(rawSentence);

        try {
            TokenStream tokens = reviewAnalyzer.tokenStream(null, sentence);
            tokens.reset();
            while (tokens.incrementToken()) {
                tokenResults.add(tokens.getAttribute(CharTermAttribute.class).toString());
            }
            tokens.close();
            tokens.end();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tokenResults;
    }

    /**
     * Remove unnecessary symbols.
     *
     * @return clean string after pre-processing.
     */
    private String preProcess(String raw_sentence) {
        return raw_sentence.replaceAll(PRIME_SYMBOL, "'").replaceAll(QUOTATION, "\"");
    }
}
