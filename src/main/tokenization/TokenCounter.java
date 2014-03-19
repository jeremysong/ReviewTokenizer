import com.google.common.base.Preconditions;
import com.sun.javafx.beans.annotations.NonNull;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.util.Version;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Counts how many characters in sentences.
 */
public class TokenCounter {

    private final static String PRIME_SYMBOL = "&#39;";

    /**
     * Gets number of tokens in the given sentence. It could handle multiple languages.
     * @param sentence string of sentence.
     * @return number of token
     */
    public List<String> getTokens(@NonNull String sentence) {
        Preconditions.checkNotNull(sentence, "Input sentence cannot be null.");

        String cleaned_sentences = preProcess(sentence);

        List<String> tokenResults = new ArrayList<>();
        Reader chineseReader = new StringReader(cleaned_sentences);
        StandardTokenizer tokenizer = new StandardTokenizer(Version.LUCENE_46, chineseReader);
        try {
            tokenizer.reset();
            while (tokenizer.incrementToken()) {
                tokenResults.add(tokenizer.getAttribute(CharTermAttribute.class).toString());
            }
            tokenizer.close();
            tokenizer.end();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        return tokenResults;
    }

    public int getNumTokens(@NonNull String sentences) {
        return getTokens(sentences).size();
    }

    /**
     * Remove unnecessary symbols.
     * @return clean string after pre-processing.
     */
    private String preProcess(String raw_sentence) {
        return raw_sentence.replaceAll(PRIME_SYMBOL, "'");
    }
}
