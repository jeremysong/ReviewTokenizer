import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TokenCounterTest {

    private List<String> sentences = new ArrayList<>();

    @Before
    public void setup() {
        try {
            BufferedReader br = new BufferedReader(new FileReader
                    ("/Users/jeremy/GoogleDrive/PSU/thesis/itunes_data/itunes_uk_data/abused_app_comment.txt"));
            String line;
            int count = 5;
            while (((line = br.readLine()) != null) && count >= 0) {
                sentences.add(line.substring(0, line.length()-2));
                count--;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testEnglish() {
        TokenCounter counter = new TokenCounter();

        for (String sentence : sentences) {
            System.out.println(sentence);
            System.out.println(counter.getTokens(sentence));
            System.out.println(counter.getNumTokens(sentence));
        }
    }

}
