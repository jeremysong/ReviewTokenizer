package tokenization;

import com.sun.istack.internal.NotNull;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Calculates the average number of tokens
 */
public class ReviewCommentLengthAnalyzer {

    public static void main(String[] args) {

        final String DATA_PATH = "/Users/jeremy/GoogleDrive/PSU/thesis/itunes_data/itunes_cn_data/";

        TokenCounter counter = new TokenCounter();


        List<Integer> abusedCommentLengthList = new ArrayList<>();
        List<Integer> benignCommentLengthList = new ArrayList<>();

        try {
            BufferedReader abusedCommentBufferedReader = new BufferedReader(new FileReader(DATA_PATH +
                    "abused_app_comment.txt"));
            String line;
            while ((line = abusedCommentBufferedReader.readLine()) != null) {
                abusedCommentLengthList.add(counter.getNumTokens(line));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedReader benignCommentBufferedReader = new BufferedReader(new FileReader(DATA_PATH +
                    "benign_app_comment.txt"));
            String line;
            while ((line = benignCommentBufferedReader.readLine()) != null) {
                benignCommentLengthList.add(counter.getNumTokens(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Abused Comment average length: " + getAvgOfList(abusedCommentLengthList));
        System.out.println("Benign Comment average length: " + getAvgOfList(benignCommentLengthList));


    }

    private static float getAvgOfList(@NotNull List<Integer> list) {
        long sum = 0;
        for (int item : list) {
            sum += item;
        }

        return sum / (float)list.size();
    }
}
