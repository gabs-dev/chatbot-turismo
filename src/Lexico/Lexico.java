package Lexico;

import Util.StringHandler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Lexico {

    private String query;
    private List<String> symbolTable;
    private Queue<String> queue;
    private List<String> identifiers;

    public Lexico() {
        symbolTable = new ArrayList<>();
        queue = new LinkedList<>();
        identifiers = new ArrayList<>(Arrays.asList("como", "qual", "para", "lista", "clima"));
    }

    public void checkAlphabet(String query) {
        query = StringHandler.processSpecialCharacters(query);

        ArrayList<String> words = StringHandler.splitQuery(query);

        for(int i = 0; i < words.size(); i++) {
            if (!isPartOfTheAlphabet(words.get(i)))
                words.remove(i);
        }

        this.query = StringHandler.concatenateWords(words);
    }

    public String removeStopWords(String query) {
        checkAlphabet(query);

        List<String> stopWords = stopWords();

        List<String> allWords = StringHandler.splitQuery(this.query);

        allWords.removeAll(stopWords);
        this.query = StringHandler.concatenateWords(allWords);

        return this.query;
    }

    public void addInSymbolTable() {
        List<String> words = StringHandler.splitQuery(this.query);
        words.removeAll(identifiers);
        symbolTable.addAll(words);
    }

    public void addInQueue() {
        queue.addAll(StringHandler.splitQuery(this.query));
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

    private boolean isPartOfTheAlphabet(String content) {
        String regex = "[\\p{L}\\p{N}\\p{P}\\p{Zs}@]+";

        if (content.matches(regex))
            return true;

        return false;
    }

}