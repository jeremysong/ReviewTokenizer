package tokenization;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Calculates the average number of tokens for each rating
 */
public class ReviewRatingCommentLengthAnalyzer {

    public static void main(String[] args) {

        final String DATA_PATH = "/Users/jeremy/GoogleDrive/PSU/thesis/itunes_data/itunes_uk_data/";

        TokenCounter counter = new TokenCounter();

        // Key: rating; Value: list of comments
        Map<Integer, List<Integer>> abusedCommentLengthMap = buildCommentMap(DATA_PATH + "abused_app_comment.txt", counter);
        Map<Integer, List<Integer>> benignCommentLengthMap = buildCommentMap(DATA_PATH + "benign_app_comment.txt", counter);

        System.out.println("-------- Abused Apps ----------");
        for (int key : abusedCommentLengthMap.keySet()) {
            System.out.println("Rating: " + key + "; Average length: " + getAvgOfList(abusedCommentLengthMap.get(key)));
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(DATA_PATH + "abused_comment_length_" + key + "" +
                        ".txt"));
                bw.write(abusedCommentLengthMap.get(key).toString());
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("-------- Benign Apps ----------");
        for (int key : benignCommentLengthMap.keySet()) {
            System.out.println("Rating: " + key + "; Average length: " + getAvgOfList(benignCommentLengthMap.get(key)));
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(DATA_PATH + "benign_comment_length_" + key + "" +
                        ".txt"));
                bw.write(benignCommentLengthMap.get(key).toString());
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * Builds comment map. Key: rating; Value: list of numbers of tokens
     * @return comment map
     */
    private static Map<Integer, List<Integer>> buildCommentMap(String fileName, TokenCounter tokenCounter) {
        Map<Integer, List<Integer>> commentMap = new HashMap<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = br.readLine()) != null) {
                String[] items = line.split(",");
                // Remove rating from end of each line
                int rating = Integer.parseInt(items[items.length - 1]);
                if (commentMap.containsKey(rating)) {
                    // Remove the last token as it is the rating value
                    commentMap.get(rating).add(tokenCounter.getNumTokens(line) - 1);
                } else {
                    List<Integer> reviewTokenList = new ArrayList<>();
                    reviewTokenList.add(rating);
                    commentMap.put(rating, reviewTokenList);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return commentMap;
    }

    private static float getAvgOfList(List<Integer> list) {
        long sum = 0;
        for (int item : list) {
            sum += item;
        }

        return sum / (float) list.size();
    }
}
