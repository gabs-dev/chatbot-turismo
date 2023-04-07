package Lexico;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.text.similarity.LevenshteinDistance;

public class Lexico {

    private String query;
    private String treatedQuery;
    private List<String> symbolTable;
    private Queue<String> queue;

    public Lexico() {
        symbolTable = new ArrayList<>();
        queue = new LinkedList<>();
    }

    /**
     *
     * @param content
     * @return
     */
    public boolean isPartOfTheAlphabet(String content) {
        String regex = "[\\p{L}\\p{N}\\p{Punct}&&[^\\[\\]{}()<>«»‘’“”‘’\"'`´^~¨¬¢£¤¥¦§¨©ª«¬®¯°±²³´µ¶·¹º»¼½¾¿×÷]]";

        if (content.matches(regex))
            return true;

        return false;
    }

    public String removeStopWords(String content) {
        List<String> stopWords = stopWords();

        List<String> allWords = splitQuery(content);

        allWords.removeAll(stopWords);
        String result = allWords.stream().collect(Collectors.joining(" "));

        return result;
    }

    /**
     * Função para calcular a similaridade entre duas strings com um grau de erro.
     * Utiliza a classe LevenshteinDistance que implementa o algoritmo de distância de Levenshtein.
     * A classe está disponível na biblioteca Apache Commons Text.
     * Fontes: https://www.techiedelight.com/pt/calculate-string-similarity-java/
     * https://commons.apache.org/proper/commons-text/apidocs/org/apache/commons/text/similarity/LevenshteinDistance.html
     * @param str1
     * @param str2
     * @param error Grau de erro máximo entre as duas strings
     */
    public boolean similar(String str1, String str2, int error) {
        int distance = LevenshteinDistance.getDefaultInstance().apply(str1, str2);

        if (distance <= error)
            return true;

        return false;
    }

    public void addInSymbolTable() {
        ArrayList<String> words = splitQuery(this.treatedQuery);

        symbolTable.addAll(words);
    }

    public void addInQueue() {
        queue.addAll(splitQuery(this.treatedQuery));
    }

    private List<String> stopWords() {
        // Fonte stopWords.txt: https://github.com/stopwords-iso/stopwords-pt/blob/master/stopwords-pt.txt
        List<String> stopWords = new ArrayList<>();

        try {
            FileReader fr = new FileReader("stopWords.txt");
            BufferedReader br = new BufferedReader(fr);

            String word;

            while((word = br.readLine()) != null) {
                stopWords.add(word);
            }

            br.close();
        } catch (IOException e) {
            System.err.println("Arquivo de stopWords.txt não encontrado!");
        }

        return stopWords;
    }

    private ArrayList<String> splitQuery(String query) {
        ArrayList<String> words =
                Stream.of(query.toLowerCase().split(" "))
                        .collect(Collectors.toCollection(ArrayList<String>::new));

        return words;
    }

}