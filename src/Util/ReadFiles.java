package Util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadFiles {

    public static List<String> readStopWordsFile() {
        // Fonte stopWords.txt: https://github.com/stopwords-iso/stopwords-pt/blob/master/stopwords-pt.txt
        List<String> stopWords = reader("stopWords");

        return stopWords;
    }
    public static List<String> readExpectedWordsFile() {
        List<String> expectedWords = reader("expectedWords");

        return expectedWords;
    }

    private static List<String> reader(String fileName) {
        List<String> words = new ArrayList<>();
        fileName += ".txt";

        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);

            String word;

            while((word = br.readLine()) != null) {
                words.add(word.toLowerCase());
            }

            br.close();
        } catch (IOException e) {
            System.err.println("Arquivo " + fileName + " não encontrado!");
        }

        return words;
    }

}
