package tokenization;

import java.io.*;
import java.util.List;

/**
 * @author Yang Song
 */
public class WordFrequencyAnalyzer {

    private final static String DATA_PATH = "/Users/jeremy/GoogleDrive/PSU/thesis/itunes_data/itunes_uk_data/";

    public static void main(String[] args) {
        EnglishReviewTokenizer tokenizer = new EnglishReviewTokenizer();

        try {
            BufferedReader abusedCommentBufferedReader = new BufferedReader(new FileReader(DATA_PATH +
                    "abused_app_comment.txt"));
            BufferedWriter abusedCommentBufferedWriter = new BufferedWriter(new FileWriter(DATA_PATH +
                    "abused_app_comment_word.txt"));
            String line;
            while ((line = abusedCommentBufferedReader.readLine()) != null) {
                List<String> words = tokenizer.tokenize(line.substring(0, line.length() - 2));
                writeWordsToFile(abusedCommentBufferedWriter, words);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void writeWordsToFile(BufferedWriter br, List<String> words) throws IOException {
        for (String word : words) {
            br.write(word);
            br.write("\n");
        }
    }
}
