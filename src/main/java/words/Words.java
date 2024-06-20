package words;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Words {
    public static List<String> getUniqueWordsFromSentence(String sentence) {
        // Convert the sentence to lowercase to ensure case-insensitive comparison
        String lowerCaseSentence = sentence.toLowerCase();

        // Split the sentence into words, ignoring punctuation and whitespace
        String[] wordsArray = lowerCaseSentence.split("[\\W_]+");

        // Convert the array to a set to ensure uniqueness
        Set<String> uniqueWordsSet = Arrays.stream(wordsArray)
                .collect(Collectors.toSet());

        // Convert the set back to a list and return
        return uniqueWordsSet.stream().sorted().collect(Collectors.toList());
    }
}
